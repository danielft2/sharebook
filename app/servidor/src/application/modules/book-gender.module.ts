import { Module } from '@nestjs/common';
import { GenderController } from '../../interfaces/controllers/gender.controller';
import { GenderService } from '../services/gender.service';
import { BookGendersService } from '../services/book-gender.service';
import { BookGendersRepository } from 'src/infraestructure/repositories/book-gender.repository';
import { PrismaModule } from 'src/infraestructure/database/prisma/prisma.module';
import { PrismaService } from 'src/infraestructure/database/prisma/prisma.service';
import { GenderRepository } from 'src/infraestructure/repositories/gender.repository';

@Module({
  providers: [
    BookGendersService,
    BookGendersRepository,
    PrismaService,
    GenderService,
    GenderRepository
    ],
    imports: [BookGenderModule]
})
export class BookGenderModule {}