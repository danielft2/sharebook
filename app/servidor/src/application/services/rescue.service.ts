import { ConflictException, Injectable, NotFoundException } from '@nestjs/common';
import { RescueRepository } from 'src/infraestructure/repositories/rescue.repository';
import { SupabaseService } from './supabase.service';
import { Rescue } from 'src/domain/entities/rescue.entity';
import { BookRepository } from 'src/infraestructure/repositories/book.repository';
import { UserRepository } from 'src/infraestructure/repositories/user.repository';
import { BookStateRepository } from 'src/infraestructure/repositories/book-state.repository';
import { BookGendersService } from './book-gender.service';

@Injectable()
export class RescueService {
    constructor(
        private readonly rescueRepository: RescueRepository,
        private readonly supabaseService: SupabaseService,
        private readonly bookRepository: BookRepository,
        private readonly userRepository: UserRepository,
        private readonly bookStateRepository: BookStateRepository,
        private readonly bookGenderService: BookGendersService,
    ){}

    async create(rescue: Rescue) {
        const rescueFinded = await this.rescueRepository.findByBookAndUserId(rescue.idBook, rescue.idRescueUser);
        if (rescueFinded) throw new ConflictException();
        return this.rescueRepository.create(rescue);
    }

    async update(rescue: Rescue) {
        const rescueFinded = await this.rescueRepository.findByBookAndUserId(rescue.idBook, rescue.idRescueUser);
        if (!rescueFinded.id) throw new NotFoundException();

        return this.rescueRepository.update(rescue);
    }

    async findIfUserHasRequestedBook(book_id: string, user_id: string) {
        const rescueFromUser = await this.rescueRepository.findByUser(user_id);
    
        const requestedByUser = rescueFromUser.some(
          (rescue) => rescue.livro_id === book_id,
        );
    
        return requestedByUser;
      }

    async findRescueById(id: string, req: any) {
        const rescue = await this.rescueRepository.findById(id);
        const isRescueFromUserLogged = rescue.usuario_solicitante_id === req.id;
        let userRescueData = {};
        let extertalUserRescueData = {};
        
        if (isRescueFromUserLogged) {
            const bookUser = await this.bookRepository.findOne(rescue.livro_oferecido_id);
            const externalBokUser = await this.bookRepository.findOne(rescue.livro_id);
            const externalUser = await this.userRepository.findById(externalBokUser.usuario_id);

            userRescueData = {
                bookId: bookUser.id,
                capa: bookUser.capa,
                titulo: bookUser.nome,
                genero: await this.bookGenderService.findGenderName(bookUser.id),
                edicao: bookUser.edicao,
                estado: await this.bookStateRepository.findOne(bookUser.estado_id),
                podeBuscar: bookUser.pode_buscar,
                querReceber: bookUser.quer_receber,
                perfil: (await this.userRepository.findById(req.id)).foto_perfil
            }

            extertalUserRescueData = {
                bookId: externalBokUser.id,
                capa: externalBokUser.capa,
                genero: await this.bookGenderService.findGenderName(externalBokUser.id),
                titulo: externalBokUser.nome,
                edicao: externalBokUser.edicao,
                estado: await this.bookStateRepository.findOne(externalBokUser.estado_id),
                podeBuscar: externalBokUser.pode_buscar,
                querReceber: externalBokUser.quer_receber,
                nome: externalUser.nome,
                perfil: await this.supabaseService.getFileURL(externalUser.nome, "UserImages"),
                telefone: externalUser.telefone,
                localizacao: `${externalUser.cidade} - ${externalUser.estado}`
            }
        } else {
            const bookUser = await this.bookRepository.findOne(rescue.livro_id);
            const externalBokUser = await this.bookRepository.findOne(rescue.livro_oferecido_id);
            const externalUser = await this.userRepository.findById(externalBokUser.usuario_id);

            userRescueData = {
                bookId: bookUser.id,
                capa: bookUser.capa,
                titulo: bookUser.nome,
                genero: await this.bookGenderService.findGenderName(bookUser.id),
                edicao: bookUser.edicao,
                estado: await this.bookStateRepository.findOne(bookUser.estado_id),
                podeBuscar: bookUser.pode_buscar,
                querReceber: bookUser.quer_receber,
                perfil: (await this.userRepository.findById(req.id)).foto_perfil
            }

            extertalUserRescueData = {
                bookId: externalBokUser.id,
                capa: externalBokUser.capa,
                titulo: externalBokUser.nome,
                genero: await this.bookGenderService.findGenderName(externalBokUser.id),
                edicao: externalBokUser.edicao,
                estado: await this.bookStateRepository.findOne(externalBokUser.estado_id),
                podeBuscar: externalBokUser.pode_buscar,
                querReceber: externalBokUser.quer_receber,
                nome: externalUser.nome,
                perfil: await this.supabaseService.getFileURL(externalUser.nome, "UserImages"),
                telefone: externalUser.telefone,
                localizacao: `${externalUser.cidade} - ${externalUser.estado}`
            }
        }

        if (rescue.status !== "Solicitação Aceita" && rescue.status !== "Solicitação Finalizada") {
            extertalUserRescueData = {
                ...extertalUserRescueData,
                telefone: undefined,
                localizacao: undefined
            }            
        }

        const returnData = {
            userRescueData,
            extertalUserRescueData
        }
        return returnData;
    }

    async findRescuesFromAUser(userId: string) {
        let dataReturn = {};
        let rescuesFromUserReturn = [];
        let rescuesFromUser = await this.rescueRepository.findByUser(userId);

        //preenchendo todas que sao solicitadas pelo usuario que esta acessando o app
        await Promise.all(
            rescuesFromUser.map(async rescue => {
                const book = await this.bookRepository.findOne(rescue.livro_id);
                const user = await this.userRepository.findById(book.usuario_id);
                
                const dataReturnMember = {
                    id: rescue.id,
                    bookImageURL: await this.supabaseService.getFileURL(book.nome, 'BookImages'),
                    edition: book.edicao,
                    title: book.nome,
                    author: book.autor,
                    status: rescue.status,
                    owner: user.nome,
                    ownerProfileURL: await this.supabaseService.getFileURL(user.nome, 'UserImages')
                }

                rescuesFromUserReturn.push({...dataReturnMember});
            })           
        );


        let allRescues = await this.rescueRepository.findAll();
        let rescuesToUser = [];

        await Promise.all(
            allRescues.map(async rescue => {
                const book = await this.bookRepository.findOne(rescue.livro_id);
                const user = await this.userRepository.findById(userId);
                
                if (userId === book.usuario_id) {
                    const dataReturnMember = {
                        id: rescue.id,
                        bookImageURL: await this.supabaseService.getFileURL(book.nome, 'BookImages'),
                        edition: book.edicao,
                        title: book.nome,
                        author: book.autor,
                        status: rescue.status,
                        owner: user.nome,
                        ownerProfileURL: await this.supabaseService.getFileURL(user.nome, 'UserImages')
                    }
                    
                    rescuesToUser.push(dataReturnMember);
                }
            })            
        );
        
        dataReturn = {rescuesFromUser: rescuesFromUserReturn, rescuesToUser: rescuesToUser};
        return dataReturn;
    }
}
