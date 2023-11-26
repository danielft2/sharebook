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
    const books = (await this.bookRepository.findMany()).filter(
      (book) => user_id !== book.usuario_id,
    );

    const availableBooks = await Promise.all(
      books.map(async (book) => {
        return {
          id: book.id,
          nome: book.nome,
          autores: book.autor,
          capa: book.capa,
        };
      }),
    );

    const favoriteGenders = await Promise.all(
      books.map(async (book) => {
        if (this.containsGender(user_id, book.id)) {
          return {
            id: book.id,
            nome: book.nome,
            autores: book.autor,
            capa: book.capa,
          };
        }
      }),
    );

    // Realizar nessa variavel uma filtragem para remover possiveis nulos
    const nextToYou = await Promise.all(
      books.map(async (book) => {
        const userNextToYou = await this.userRepository.findById(
          book.usuario_id,
        );
        const userUf = await this.getUserUf(user.cep);
        const userNextToYouUf = await this.getUserUf(userNextToYou.cep);
        if (userUf === userNextToYouUf) {
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
      const bookOwner = await this.userRepository.findById(user_id);
      const userInformation = {
        profilePhoto: user.foto_perfil,
        userName: bookOwner.nome,
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
