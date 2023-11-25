import { PrismaService } from "../database/prisma/prisma.service";
import { Injectable } from "@nestjs/common";
import { ChangeProcess } from "src/domain/entities/changeProcess.entity";

@Injectable()
export class ChangeProcessRepository {
  constructor(private readonly prisma: PrismaService) {}

  async create(changeProcess: ChangeProcess) {
    return await this.prisma.processoDeTroca.create({
       data: {
        usuario_solicitante_id: changeProcess.idRescueUser,
        livro_id: changeProcess.idBook,
        status: changeProcess.status,
        livro_oferecido_id: changeProcess.idBookFromRescue
       }
    });
  }

  async findById(id: string) {
    return await this.prisma.processoDeTroca.findUnique({
      where: { id },
    });
  }

  async findByBookId(bookId: string) {
    return await this.prisma.processoDeTroca.findMany({
        where: {livro_id: bookId}
    })
  }

  async findByBookAndUserId(bookId: string, userId: string) {
    return await this.prisma.processoDeTroca.findFirst({
      where: {
        livro_id: bookId,
        usuario_solicitante_id: userId
      }
    })
  }

  async update(changeProcess: ChangeProcess) {
    return await this.prisma.processoDeTroca.update({
        where: {id: changeProcess.id},
        data: {
            ...changeProcess
        }
    })
  }

  async findAll() {
    return await this.prisma.processoDeTroca.findMany({});
  }
}