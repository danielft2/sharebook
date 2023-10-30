import { Injectable } from '@nestjs/common';
import { PrismaService } from '../database/prisma/prisma.service';

@Injectable()
export class BookRepository {
  constructor(private prisma: PrismaService) {}

  async findOne(isbn: string) {
    return await this.prisma.livro.findUnique({
      where: { isbn },
    });
  }

  async findMany() {
    return await this.prisma.livro.findMany();
  }
}
