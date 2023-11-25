import { Module } from '@nestjs/common';
import { GenderController } from '../../interfaces/controllers/gender.controller';
import { GenderService } from '../services/gender.service';

@Module({
  controllers: [GenderController],
  providers: [GenderService],
})
export class BookModule {}
