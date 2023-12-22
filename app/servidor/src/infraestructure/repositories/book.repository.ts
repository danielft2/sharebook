import { Injectable } from '@nestjs/common';
import { PrismaService } from '../database/prisma/prisma.service';
import { Book } from '../../domain/entities/book.entity';

@Injectable()
export class BookRepository {
  constructor(private prisma: PrismaService) {}

  async create(book: Book) {
    return await this.prisma.livro.create({
      data: {
        isbn: book.isbn,
        nome: book.nome,
        sinopse: book.sinopse,
        autor: book.autor,
        usuario_id: book.usuario_id,
        edicao: book.edicao,
        idioma: book.idioma,
        pode_buscar: book.pode_buscar,
        quer_receber: book.quer_receber,
        capa: book.capa,
        imagens: book.imagens,
        estado_id: book.estado_id,
        longitude: book.longitude,
        latitude: book.latitude,
      },
    });
  }

  async update(book_id: string, book: Book) {
    return await this.prisma.livro.update({
      where: { id: book_id },
      data: {
        ...book,
      },
    });
  }

  async findOne(id: string) {
    return await this.prisma.livro.findUnique({
      where: { id },
    });
  }

  async findMany() {
    return await this.prisma.livro.findMany();
  }

  async delete(id: string) {
    return await this.prisma.livro.delete({
      where: { id },
    });
  }

  async searchBook(query: string) {
    return await this.prisma.livro.findMany({
      where: {
        OR: [
          {
            nome: { contains: query, mode: 'insensitive' },
          },
        ],
      },
    });
  }
}
