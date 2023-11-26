import { Injectable } from '@nestjs/common';
import { BookRepository } from '../../infraestructure/repositories/book.repository';
import { IbgeFinderService } from './ibge-finder.service';
import { UserRepository } from '../../infraestructure/repositories/user.repository';
import { UserGendersService } from './user-gender.service';
import { BookGendersService } from './book-gender.service';

@Injectable()
export class BookService {
  constructor(
    private bookRepository: BookRepository,
    private ibgeFinderService: IbgeFinderService,
    private userRepository: UserRepository,
    private userGenderService: UserGendersService,
    private bookGenderService: BookGendersService,
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

  async findAll(user_id: string) {
    const user = await this.userRepository.findById(user_id);
    const books = await this.bookRepository.findMany();

    const availableBooks = await Promise.all(
      books.map(async (book) => {
        if (user_id !== book.usuario_id) {
          return {
            id: book.id,
            nome: book.nome,
            autores: book.autor,
            capa: book.capa,
          };
        }
      }),
    );

    const favoriteGenders = await Promise.all(
      books.map(async (book) => {
        if (
          user_id !== book.usuario_id &&
          this.containsGender(user_id, book.id)
        ) {
          return {
            id: book.id,
            nome: book.nome,
            autores: book.autor,
            capa: book.capa,
          };
        }
      }),
    );

    const nextToYou = await Promise.all(
      books.map(async (book) => {
        const userNextToYou = await this.userRepository.findById(
          book.usuario_id,
        );
        if (
          user_id !== book.usuario_id &&
          this.getUserIBGE(user.cep) === this.getUserIBGE(userNextToYou.cep)
        ) {
          return {
            id: book.id,
            nome: book.nome,
            autores: book.autor,
            capa: book.capa,
          };
        }
      }),
    );

    return {
      availableBooks,
      favoriteGenders,
      nextToYou,
    };
  }

  async findMyBooks(user_id: string) {
    const books = await this.bookRepository.findMany();

    const myBooks = await Promise.all(
      books.map(async (book) => {
        if (user_id === book.usuario_id) {
          return {
            id: book.id,
            nome: book.nome,
            autores: book.autor,
            capa: book.capa,
          };
        }
      }),
    );

    return myBooks;
  }

  async findOne(book_id: string, user_id: string) {
    const book = await this.bookRepository.findOne(book_id);
    const user = await this.userRepository.findById(user_id);
    if (user.id === book.usuario_id) {
      return book;
    } else {
      const userInformation = {
        profilePhoto: user.foto_perfil,
        userName: user.nome,
        city: user.cidade,
        uf: this.getUserUf(user.cep),
      };
      return {
        userInformation,
        book,
      };
    }
  }
}
