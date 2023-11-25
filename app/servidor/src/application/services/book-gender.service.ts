import { Injectable } from '@nestjs/common';
import { BookGendersRepository } from '../../infraestructure/repositories/book-gender.repository';

@Injectable()
export class BookGendersService {
  constructor(private bookGenderRepository: BookGendersRepository) {}

  async findAll() {
    return await this.bookGenderRepository.findMany();
  }

  async findAllByBookId(book_id: string) {
    const bookGenders = await this.findAll();
    const genders = await Promise.all(
      bookGenders.map(async (bookGender) => {
        if (bookGender.livro_id === book_id) {
          return bookGender.genero_id;
        }
      }),
    );
    return genders;
  }
}
