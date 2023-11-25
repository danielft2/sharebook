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

  private async containsGender(user_id: string, book_id: string) {
    const userGenders = await this.userGenderService.findAllByUserId(user_id);
    const bookGenders = await this.bookGenderService.findAllByBookId(book_id);

    return userGenders.some((userGenders) => bookGenders.includes(userGenders));
  }

  async findAll(user_id: string) {
    const user = await this.userRepository.findById(user_id);

    const availableBooks = (await this.bookRepository.findMany())
      .filter((book) => {
        if (user_id !== book.usuario_id) return book;
      })
      .slice(0, 20);

    const favoriteGenders = (await this.bookRepository.findMany())
      .filter((book) => {
        if (
          user_id !== book.usuario_id &&
          this.containsGender(user_id, book.id)
        ) {
          return book;
        }
      })
      .slice(0, 20);

    const nextToYou = (await this.bookRepository.findMany())
      .filter(async (book) => {
        const userNextToYou = await this.userRepository.findById(book.usuario_id);
        if (
          user_id !== book.usuario_id &&
          this.getUserIBGE(user.cep) === this.getUserIBGE(userNextToYou.cep)
        ) {
          return book;
        }
      })
      .slice(0, 20);
    return {
      availableBooks,
      favoriteGenders,
      nextToYou,
    };
  }

  async findOne(id: string) {
    // Adicionar para retornar também a:
    /* 
    A foto de perfil do usuario
    O nome do usuario
    A cidade do usuario
    A sigla do estado? / Estado do usuario
    */
    return await this.bookRepository.findOne(id);
  }
}
