import { userInfo } from 'os';
import { Alert } from '../../domain/entities/alert.entity';
import { PrismaService } from '../database/prisma/prisma.service';
import { Injectable } from '@nestjs/common';

@Injectable()
export class AlertRepository {
  constructor(private prisma: PrismaService) {}

  async create(alert: Alert) {
    return this.prisma.alerta.create({
      data: {
        nome_livro: alert.bookName,
        filtrado_por_localizacao: alert.isFilteredByLocalization,
        filtrado_por_envio: alert.isFilteredBySend,
        usuario_id: alert.usuarioId,
      },
    });
  }

  async findAlertByUserIdAndBookName(user_id: string, book_name: string) {
    return this.prisma.alerta.findUnique({
      where: {
        usuario_id_nome_livro: {
          usuario_id: user_id,
          nome_livro: book_name
        }
      }
    })
  }

  async findById(id: string) {
    return this.prisma.alerta.findUnique({
      where: { id },
    });
  }

  async findAll() {
    return await this.prisma.alerta.findMany({});
  }
}
