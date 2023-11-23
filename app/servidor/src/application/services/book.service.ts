import { Injectable } from '@nestjs/common';
import { BookRepository } from '../../infraestructure/repositories/book.repository';
import { IbgeFinderService } from './ibge-finder.service';
import { UserService } from './user.service';

@Injectable()
export class BookService {
  constructor(
    private bookRepository: BookRepository,
    private ibgeFinderService: IbgeFinderService,
    private userService: UserService,
  ) {}

  async getUserIBGE(user_id: string) {
    const user = await this.userService.findOneById(user_id);
    return this.ibgeFinderService.getIBGE(user.cep);
  }

  async findAll(user_id: string) {
    // Pega os 20 primeiros livros disponiveis, tirando os do proprio usuario
    const availableBooks = (await this.bookRepository.findMany())
      .filter((book) => {
        if (user_id !== book.usuario_id) return book;
      })
      .slice(0, 20);

    // const favoriteGenders = (await this.bookRepository.findMany()).filter(
    //     (book) => {
    //         if(user_id !== book.usuario_id) {
    //
    //         }
    //     }
    // ).slice(0, 20);

    const nextToYou = (await this.bookRepository.findMany())
      .filter((book) => {
        if (
          user_id !== book.usuario_id &&
          this.getUserIBGE(user_id) === this.getUserIBGE(book.usuario_id)
        ) {
          return book;
        }
      })
      .slice(0, 20);
    return {
      availableBooks,
      // favoriteGenders
      nextToYou,
    };
  }

  async findOne(id: string) {
    return this.bookRepository.findOne(id);
  }
}
