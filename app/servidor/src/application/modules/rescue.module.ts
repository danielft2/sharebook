import { Module } from '@nestjs/common';
import { RescueService } from '../services/rescue.service';
import { RescueController } from '../../interfaces/controllers/rescue.controller';
import { PrismaService } from 'src/infraestructure/database/prisma/prisma.service';
import { SupabaseService } from '../services/supabase.service';
import { UserRepository } from 'src/infraestructure/repositories/user.repository';
import { BookRepository } from 'src/infraestructure/repositories/book.repository';
import { RescueRepository } from 'src/infraestructure/repositories/rescue.repository';
import { BookStateRepository } from 'src/infraestructure/repositories/book-state.repository';
import { BookGendersService } from '../services/book-gender.service';
import { GenderService } from '../services/gender.service';
import { GenderRepository } from 'src/infraestructure/repositories/gender.repository';
import { BookGendersRepository } from 'src/infraestructure/repositories/book-gender.repository';

@Module({
  providers: [
    RescueService, 
    PrismaService, 
    SupabaseService,
    UserRepository, 
    BookRepository, 
    RescueRepository, 
    BookStateRepository,
    BookGendersService,
    GenderService,
    GenderRepository,
    BookGendersRepository
  ],
  controllers: [RescueController]
})
export class RescueModule {}
