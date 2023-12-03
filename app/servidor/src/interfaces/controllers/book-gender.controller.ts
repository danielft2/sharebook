import { Controller, Get, Param } from '@nestjs/common';
import { BookGendersService } from '../../application/services/book-gender.service';

@Controller('book-gender')
export class BookGenderController {
  constructor(private bookGenderService: BookGendersService) {}

  @Get()
  async findMany() {
    return this.bookGenderService.findAll();
  }
}
