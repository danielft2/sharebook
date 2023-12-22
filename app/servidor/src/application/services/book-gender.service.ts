import { Injectable, NotFoundException } from '@nestjs/common';
import { BookGendersRepository } from '../../infraestructure/repositories/book-gender.repository';
import { GenderService } from './gender.service';
import { BookGender } from '../../domain/entities/book-gender.entity';

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

  async findBookGenderById(book_id: string) {
    const bookGenders = (await this.bookGenderRepository.findMany()).filter(
      (bookGender) => bookGender.livro_id === book_id,
    );
    return bookGenders;
  }

  async create(bookGender: BookGender) {
    return await this.bookGenderRepository.create(bookGender);
  }

  async delete(bookGender: BookGender) {
    const findedBookGender = await this.bookGenderRepository.findOne(
      bookGender,
    );

    if (!findedBookGender) throw new NotFoundException();
    return this.bookGenderRepository.delete(bookGender);
  }
}
