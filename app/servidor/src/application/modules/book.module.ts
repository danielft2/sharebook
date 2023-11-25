import { Module } from '@nestjs/common';
import { BookControler } from '../../interfaces/controllers/book.controller';
import { BookService } from '../services/book.service';
import { BookRepository } from '../../infraestructure/repositories/book.repository';
import { PrismaModule } from '../../infraestructure/database/prisma/prisma.module';
import { IbgeFinderService } from '../services/ibge-finder.service';
import { UserRepository } from '../../infraestructure/repositories/user.repository';
import { UserGendersService } from '../services/user-gender.service';
import { BookGendersService } from '../services/book-gender.service';
import { UserGendersRepository } from '../../infraestructure/repositories/user-gender.repository';
import { BookGendersRepository } from '../../infraestructure/repositories/book-gender.repository';

@Module({
  imports: [PrismaModule],
  controllers: [BookControler],
  providers: [
    BookService,
    BookRepository,
    IbgeFinderService,
    UserRepository,
    UserGendersRepository,
    BookGendersRepository,
    UserGendersService,
    BookGendersService,
  ],
})
export class BookModule {}
