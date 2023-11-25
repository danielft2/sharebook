import { Rescue } from "src/domain/entities/rescue.entity";
import { PrismaService } from "../database/prisma/prisma.service";
import { Injectable } from "@nestjs/common";

@Injectable()
export class RescueRepository {
  constructor(private readonly prisma: PrismaService) {}

  async create(rescue: Rescue) {
    return await this.prisma.solicitacao.create({
       data: {
        usuario_solicitante_id: rescue.idRescueUser,
        livro_id: rescue.idBook,
        status: rescue.status,
        livro_oferecido_id: rescue.idBookFromRescue
       }
    });
  }

  async findById(id: string) {
    return await this.prisma.solicitacao.findUnique({
      where: { id },
    });
  }

  async findByBookId(bookId: string) {
    return await this.prisma.solicitacao.findMany({
        where: {livro_id: bookId}
    })
  }

  async findByBookAndUserId(bookId: string, userId: string) {
    return await this.prisma.solicitacao.findFirst({
      where: {
        livro_id: bookId,
        usuario_solicitante_id: userId
      }
    })
  }

  async findByUser(userId: string) {
    return await this.prisma.solicitacao.findMany({
      where: {usuario_solicitante_id: userId}
    })
  }

  async update(rescue: Rescue) {
    return await this.prisma.solicitacao.update({
        where: {id: rescue.id},
        data: {
            ...rescue
        }
    })
  }

  async findAll() {
    return await this.prisma.solicitacao.findMany({});
  }
}