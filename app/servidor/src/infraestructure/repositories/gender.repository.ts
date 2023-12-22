import { Injectable } from '@nestjs/common';
import { PrismaService } from '../database/prisma/prisma.service';
import { Gender } from '../../domain/entities/gender.entity';

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

  async create(gender: Gender) {
    return await this.prisma.genero.create({
      data: {
        id: gender.id,
        nome: gender.genderName,
      },
    });
  }
}
