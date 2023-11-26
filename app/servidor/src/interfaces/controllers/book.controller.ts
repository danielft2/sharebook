import { Controller, Get, Param, Req } from '@nestjs/common';
import { BookService } from '../../application/services/book.service';
import { ApiParam } from '@nestjs/swagger';

@Controller('book')
export class BookControler {
  constructor(private bookService: BookService) {}

  @Get('/home')
  async findMany(@Req() token) {
    return await this.bookService.findAll(token.user.id);
  }

  @Get('/mybooks')
  async findMyBooks(@Req() token) {
    return await this.bookService.findMyBooks(token.user.id);
  }

  @Get(':book_id')
  @ApiParam({
    name: 'book id',
    description: 'return a book based on the book_id',
  })
  async findOne(@Param('book_id') book_id: string, @Req() token) {
    return this.bookService.findOne(book_id, token.user.id);
  }
}
