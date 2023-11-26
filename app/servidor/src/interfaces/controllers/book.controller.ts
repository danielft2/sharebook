import { Controller, Get, Param, Req } from '@nestjs/common';
import { BookService } from '../../application/services/book.service';
import { ApiParam } from '@nestjs/swagger';

@Controller('book')
export class BookControler {
  constructor(private bookService: BookService) {}

  @Get('/home')
  async findMany(@Req() req) {
    return await this.bookService.findAll(req.user.id);
  }

  @Get('/mybooks')
  async findMyBooks(@Req() req) {
    return await this.bookService.findMyBooks(req.user.id);
  }

  @Get('/:book_id')
  @ApiParam({
    name: 'book id',
    description: 'return a book based on the book_id',
  })
  async findOne(@Param('book_id') book_id: string, @Req() req) {
    return this.bookService.findOne(book_id, req.user.id);
  }
}
