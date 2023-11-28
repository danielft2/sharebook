/* eslint-disable prettier/prettier */
import { Injectable, NotFoundException } from '@nestjs/common';
import { BookRepository } from '../../infraestructure/repositories/book.repository';
import { IbgeFinderService } from './ibge-finder.service';
import { UserRepository } from '../../infraestructure/repositories/user.repository';
import { UserGendersService } from './user-gender.service';
import { BookGendersService } from './book-gender.service';
import { BookStateService } from './book-state.service';
import { RescueService } from './rescue.service';
import { Book } from '../../domain/entities/book.entity';
import { SupabaseService } from './supabase.service';
import { v4 as uuidv4 } from 'uuid';

@Injectable()
export class BookService {
  constructor(
    private bookRepository: BookRepository,
    private ibgeFinderService: IbgeFinderService,
    private userRepository: UserRepository,
    private userGenderService: UserGendersService,
    private bookGenderService: BookGendersService,
    private bookStateService: BookStateService,
    private rescueService: RescueService,
    private supabaseService: SupabaseService,
  ) {}

  private async getUserIBGE(user_cep: string) {
    return await this.ibgeFinderService.getIBGE(user_cep);
  }

  private async getUserUf(user_cep: string) {
    return await this.ibgeFinderService.getUf(user_cep);
  }

  private async containsGender(user_id: string, book_id: string) {
    const userGenders = await this.userGenderService.findAllByUserId(user_id);
    const bookGenders = await this.bookGenderService.findAllByBookId(book_id);

    return userGenders.some((userGenders) => bookGenders.includes(userGenders));
  }

  private async detailedBook(book_id: string) {
    const book = await this.bookRepository.findOne(book_id);
    const bookOwner = await this.userRepository.findById(book.usuario_id);
  
    const userInformation = {
      profilePhoto: bookOwner.foto_perfil,
      userName: bookOwner.nome,
      city: bookOwner.cidade,
      uf: await this.getUserUf(bookOwner.cep),
    };
  
    let images = [];
  
    if (book.imagens && Array.isArray(book.imagens)) {
      images = await Promise.all(
        book.imagens
          .filter((image) => image !== '')
          .map((image) =>
            this.supabaseService.getFileURL(image, 'BookImages')
          )
      );
    }
  
    return {
      userInformation,
      book: {
        id: book.id,
        isbn: book.isbn,
        nome: book.nome,
        sinopse: book.sinopse,
        autor: book.autor,
        usuario_id: book.usuario_id,
        edicao: book.edicao,
        idioma: book.idioma,
        pode_buscar: book.pode_buscar,
        pode_receber: book.quer_receber,
        capa: await this.supabaseService.getFileURL(book.capa, 'BookImages'),
        imagens: images,
        genders: await this.bookGenderService.findGenderName(book.id),
        book_state: (
          await this.bookStateService.findOne(book.estado_id)
        ).nome,
      },
    };
  }

  async findAll(user_id: string) {
    const user = await this.userRepository.findById(user_id);
    const userUf = await this.getUserUf(user.cep);
  
    const books = await this.bookRepository.findMany();
    const filteredBooks = books.filter((book) => book.usuario_id !== user_id);
  
    const getFileURL = async (book: Book) => ({
      id: book.id,
      nome: book.nome,
      autores: book.autor,
      capa: await this.supabaseService.getFileURL(book.capa, 'BookImages'),
    });
  
    const [availableBooks, favoriteGenders, nextToYou] = await Promise.all([
      Promise.all(filteredBooks.map(getFileURL)),
      Promise.all(
        filteredBooks
          .filter((book) => this.containsGender(user_id, book.id))
          .map(getFileURL)
      ),
      Promise.all(
        filteredBooks
          .filter(async (book) => {
            const userNextToYou = await this.userRepository.findById(
              book.usuario_id
            );
            const userNextToYouUf = await this.getUserUf(userNextToYou.cep);
            return userUf === userNextToYouUf;
          })
          .map(getFileURL)
      ),
    ]);
  
    return { availableBooks, favoriteGenders, nextToYou };
  }

  async findMyBooks(user_id: string) {
    const books = await this.bookRepository.findMany();

    const myBooks = await Promise.all(
      books
        .filter((book) => user_id === book.usuario_id)
        .map(async (book) => ({
          id: book.id,
          nome: book.nome,
          autores: book.autor,
          capa: await this.supabaseService.getFileURL(book.capa, 'BookImages'),
          edicao: book.edicao,
          pode_receber: book.quer_receber,
          pode_enviar: book.pode_buscar,
          genero: await this.bookGenderService.findGenderName(book.id),
          estado: (await this.bookStateService.findOne(book.estado_id)).nome,
        })),
    );

    return myBooks;
  }

  async findOne(book_id: string, user_id: string) {
    const book = await this.bookRepository.findOne(book_id);
    const user = await this.userRepository.findById(user_id);
    const detailedBook = await this.detailedBook(book_id);
    if (user.id === book.usuario_id) {
      const rescueDataPromises = (await this.rescueService.findAll())
        .filter(rescue => rescue.livro_id === book_id)
        .map(async (rescue) => {
          const user = await this.userRepository.findById(rescue.usuario_solicitante_id);
          return {
            nome: user.nome,
            cidade: user.cidade,
            uf: await this.getUserUf(user.cep),
            foto_perfil: await this.supabaseService.getFileURL(user.nome, 'UserImages')
          };
        });
      const rescues = await Promise.all(rescueDataPromises);
      return {
        rescues,
        detailedBook,
        is_request: false,
      };
    } else {
      const is_request = await this.rescueService.findIfUserHasRequestedBook(book_id, user_id);
      return {
        detailedBook,
        is_request,
      };
    }
  }

  async removerAcentos(str: string){
    return str.normalize('NFD').replace(/[\u0300-\u036f]/g, '');
  }

  async create(book: Book) {
    // const capa = await this.removerAcentos(book.nome);
    // this.supabaseService.create(capa, 'BookImages', Buffer.from(book.capa, 'base64'));
    
    // let images: string[] = [];

    // if (book.imagens && Array.isArray(book.imagens)) {
    //   images = await Promise.all(
    //     book.imagens.map(async (imagemBase64) => {
    //       const imageName = uuidv4();
    //       await this.supabaseService.create(imageName, 'BookImages', Buffer.from(imagemBase64, 'base64'));
    //       return imageName;
    //     })
    //   );
    // }
    // const updatedBook = {
    //   ...book,
    //   capa: capa,
    //   images: images
    // }
    // return await this.bookRepository.create(updatedBook);
    return await this.bookRepository.create(book);
  }

  async update(book: Book){
    const findedBook = await this.bookRepository.findOne(book.id);

    if(!findedBook.id) throw new NotFoundException();
    return this.bookRepository.update(book);
  }
}
