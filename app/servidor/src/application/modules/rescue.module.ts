import { Module } from '@nestjs/common';
import { RescueService } from '../services/rescue.service';
import { RescueController } from '../../interfaces/controllers/rescue.controller';
import { PrismaService } from 'src/infraestructure/database/prisma/prisma.service';
import { SupabaseService } from '../services/supabase.service';
import { UserRepository } from 'src/infraestructure/repositories/user.repository';
import { BookRepository } from 'src/infraestructure/repositories/book.repository';
import { RescueRepository } from 'src/infraestructure/repositories/rescue.repository';
import { BookStateRepository } from 'src/infraestructure/repositories/book-state.repository';

@Module({
  providers: [
    RescueService, 
    PrismaService, 
    SupabaseService,
    UserRepository, 
    BookRepository, 
    RescueRepository, 
    BookStateRepository,
  ],
  controllers: [RescueController]
})
export class RescueModule {}
