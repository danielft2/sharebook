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
import { GenderService } from '../services/gender.service';
import { GenderRepository } from 'src/infraestructure/repositories/gender.repository';
import { BookStateRepository } from 'src/infraestructure/repositories/book-state.repository';
import { BookStateService } from '../services/book-state.service';
import { RescueService } from '../services/rescue.service';
import { RescueRepository } from 'src/infraestructure/repositories/rescue.repository';
import { SupabaseService } from '../services/supabase.service';

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
    GenderService,
    GenderRepository,
    BookStateRepository,
    BookStateService,
    RescueService,
    RescueRepository,
    SupabaseService,
  ],
})
export class BookModule {}
