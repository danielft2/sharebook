import { Controller, Get, Param } from '@nestjs/common';
import { BookService } from '../../application/services/book.service';
import { ApiHeader, ApiParam } from '@nestjs/swagger';

@Controller('book')
export class BookControler {
  constructor(private bookService: BookService) {}

  @Get('home/:user_id')
  @ApiParam({
    name: 'user_id',
    description: 'return books of homepage based on user id',
  })
  async findMany(@Param('user_id') user_id: string) {
    return this.bookService.findAll(user_id);
  }

  @Get(':isbn')
  @ApiParam({
    name: 'isbn',
    description: 'return a book based on the isbn',
  })
  async findOne(@Param('isbn') isbn: string) {
    return this.bookService.findOne(isbn);
  }
}
