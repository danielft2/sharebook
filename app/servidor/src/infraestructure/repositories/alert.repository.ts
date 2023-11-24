import { Alert } from "src/domain/entities/alert.entity";
import { PrismaService } from "../database/prisma/prisma.service";
import { Injectable } from "@nestjs/common";

@Injectable()
export class AlertRepository {
  constructor(private readonly prisma: PrismaService) {}

  async create(alert: Alert) {
    return this.prisma.alerta.create({
        data: {
            nome_livro: alert.bookName,
            filtrado_por_localizacao: alert.isFilteredByLocalization,
            filtrado_por_envio: alert.isFilteredBySend,
            usuario_id: alert.usuarioId
        }
    })
  }

  async findById(id: string) {
    return this.prisma.alerta.findUnique({
      where: { id },
    });
  }

  async findByUserAndBook(userId: string, bookName: string) {
    return this.prisma.alerta.findFirst({
        where: {
            usuario_id: userId,
            nome_livro: bookName
        }
    })
  }

  async findAll() {
    return await this.prisma.alerta.findMany({});
  }
}