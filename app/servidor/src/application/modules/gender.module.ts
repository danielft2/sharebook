import { Module } from '@nestjs/common';
import { GenderController } from '../../interfaces/controllers/gender.controller';
import { GenderService } from '../services/gender.service';
import { GenderRepository } from '../../infraestructure/repositories/gender.repository';
import { PrismaModule } from '../../infraestructure/database/prisma/prisma.module';

@Module({
  imports: [PrismaModule],
  controllers: [GenderController],
  providers: [GenderService, GenderRepository],
})
export class GenderModule {}
