import { Module } from '@nestjs/common';
import { GenderController } from '../../interfaces/controllers/gender.controller';
import { GenderService } from '../services/gender.service';
import { BookGendersService } from '../services/book-gender.service';
import { BookGendersRepository } from '../../infraestructure/repositories/book-gender.repository';
import { PrismaService } from '../../infraestructure/database/prisma/prisma.service';
import { GenderRepository } from '../../infraestructure/repositories/gender.repository';
import { BookGenderController } from '../../interfaces/controllers/book-gender.controller';

@Module({
  providers: [
    BookGendersService,
    BookGendersRepository,
    PrismaService,
    GenderService,
    GenderRepository,
  ],
  controllers: [BookGenderController],
  imports: [BookGenderModule],
})
export class BookGenderModule {}
