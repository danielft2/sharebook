import { Controller, Get, Param } from '@nestjs/common';
import { GenderService } from '../../../src/application/services/gender.service';

@Controller('gender')
export class GenderController {
  constructor(private genderService: GenderService) {}

  @Get('id')
  async findOne(@Param('id') id: string) {
    return this.genderService.findOne(id);
  }

  @Get()
  async findMany() {
    return this.genderService.findMany();
  }
}
