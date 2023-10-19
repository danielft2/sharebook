import { Injectable } from '@nestjs/common';
import { PrismaService } from '../database/prisma/prisma.service';
import * as bcrypt from 'bcrypt';
import { User } from 'src/domain/entities/user.entity';

@Injectable()
export class UserRepository {
  constructor(private readonly prisma: PrismaService) {}

  async create(user: User) {
    const userData = {
      ...user,
      senha: await bcrypt.hash(user.senha, 10),
    };

    return await this.prisma.usuario.create({
      data: {
        nome: userData.nome,
        email: userData.email,
        senha: userData.senha,
        telefone: userData.telefone,
        cep: userData.cep,
        cidade: userData.cidade,
        estado: userData.estado,
        foto_perfil: userData.profile_photo
      }
    });
  }

  async findById(id: string) {
    return this.prisma.usuario.findUnique({
        where: {id},
    });
  }

  async findByEmail(email: string) {
    return this.prisma.usuario.findUnique({
      where: { email },
    });
  }

  async findAll() {
    return await this.prisma.usuario.findMany({});
  }
}
