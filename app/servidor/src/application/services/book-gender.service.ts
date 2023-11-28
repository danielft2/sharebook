import { Injectable } from '@nestjs/common';
import { BookGendersRepository } from '../../infraestructure/repositories/book-gender.repository';
import { GenderService } from './gender.service';

@Injectable()
export class BookGendersService {
  constructor(
    private bookGenderRepository: BookGendersRepository,
    private genderService: GenderService,
  ) {}

  async findAll() {
    return await this.bookGenderRepository.findMany();
  }

  async findAllByBookId(book_id: string) {
    const bookGenders = await this.findAll();

    const genders = bookGenders
      .filter((bookGender) => bookGender.livro_id === book_id)
      .map((bookGender) => bookGender.genero_id);

    return genders;
  }

  async findGenderName(book_id: string) {
    const genderIds = await this.findAllByBookId(book_id);
    const genderList = await Promise.all(
      genderIds.map(async (genderId) => {
        const gender = await this.genderService.findOne(genderId);
        return gender.nome;
      }),
    );

    return genderList;
  }
}
