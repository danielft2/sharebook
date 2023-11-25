import { Module } from '@nestjs/common';
import { ChangeProcessService } from '../services/change-process.service';
import { ChangeProcessController } from '../../interfaces/controllers/change-process.controller';

@Module({
  providers: [ChangeProcessService],
  controllers: [ChangeProcessController]
})
export class ChangeProcessModule {}
