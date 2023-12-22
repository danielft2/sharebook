import { Module } from '@nestjs/common';
import { BookStateController } from '../../interfaces/controllers/book-state.controller';
import { BookStateService } from '../services/book-state.service';
import { PrismaModule } from '../../infraestructure/database/prisma/prisma.module';
import { BookStateRepository } from '../../infraestructure/repositories/book-state.repository';

@Module({
  imports: [PrismaModule],
  controllers: [BookStateController],
  providers: [BookStateService, BookStateRepository],
})
export class BookStateModule {}
