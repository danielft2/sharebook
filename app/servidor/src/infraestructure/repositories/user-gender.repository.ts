import { Injectable } from '@nestjs/common';
import { PrismaService } from '../database/prisma/prisma.service';

@Injectable()
export class UserGendersRepository {
  constructor(private prisma: PrismaService) {}

  async findMany() {
    return this.prisma.generoUsuario.findMany();
  }
}
