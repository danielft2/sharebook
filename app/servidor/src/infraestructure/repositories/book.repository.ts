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
        nome: book.name,
        sinopse: book.synopsis,
        autor: book.writer,
        usuario_id: book.owner_id,
        edicao: book.edition,
        idioma: book.language,
        pode_buscar: book.can_get,
        quer_receber: book.can_receive,
        capa: book.cape,
        imagens: book.images,
        estado_id: book.state_id,
        longitude: book.longitude,
        latitude: book.latitude,
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
}
