import { ConflictException, Injectable, NotFoundException } from '@nestjs/common';
import { RescueRepository } from 'src/infraestructure/repositories/rescue.repository';
import { SupabaseService } from './supabase.service';
import { Rescue } from 'src/domain/entities/rescue.entity';
import { BookRepository } from 'src/infraestructure/repositories/book.repository';
import { UserRepository } from 'src/infraestructure/repositories/user.repository';

@Injectable()
export class RescueService {
    constructor(
        private readonly rescueRepository: RescueRepository,
        private readonly supabaseService: SupabaseService,
        private readonly bookRepository: BookRepository,
        private readonly userRepository: UserRepository
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
