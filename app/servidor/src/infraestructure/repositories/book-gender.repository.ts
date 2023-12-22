import { Injectable } from '@nestjs/common';
import { PrismaService } from '../database/prisma/prisma.service';
import { BookGender } from '../../domain/entities/book-gender.entity';

@Injectable()
export class BookGendersRepository {
  constructor(private prisma: PrismaService) {}

  async findMany() {
    return this.prisma.generoLivro.findMany();
  }

  async findByBookId(book_id: string) {
    return this.prisma.generoLivro.findMany({
      where: { livro_id: book_id },
    });
  }

  async findOne(bookGender: BookGender) {
    return this.prisma.generoLivro.findUnique({
      where: {
        livro_id_genero_id: {
          genero_id: bookGender.genderId,
          livro_id: bookGender.bookId,
        },
      },
    });
  }

  async findByGenderId(gender_id: string) {
    return this.prisma.generoLivro.findMany({
      where: { genero_id: gender_id },
    });
  }

  async create(bookGender: BookGender) {
    return this.prisma.generoLivro.create({
      data: {
        genero_id: bookGender.genderId,
        livro_id: bookGender.bookId,
      },
    });
  }

  async delete(bookGender: BookGender) {
    return this.prisma.generoLivro.delete({
      where: {
        livro_id_genero_id: {
          genero_id: bookGender.genderId,
          livro_id: bookGender.bookId,
        },
      },
    });
  }
}
