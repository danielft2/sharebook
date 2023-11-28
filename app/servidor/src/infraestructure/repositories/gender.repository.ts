import { Injectable } from '@nestjs/common';
import { PrismaService } from '../database/prisma/prisma.service';

@Injectable()
export class GenderRepository {
  constructor(private prisma: PrismaService) {}

  async findOne(id: string) {
    return await this.prisma.genero.findUnique({
      where: { id },
    });
  }

  async findMany() {
    return await this.prisma.genero.findMany();
  }
}
