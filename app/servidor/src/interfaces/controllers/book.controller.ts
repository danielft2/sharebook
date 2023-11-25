import { Controller, Get, Param, Req } from '@nestjs/common';
import { BookService } from '../../application/services/book.service';
import { ApiParam } from '@nestjs/swagger';

@Controller('book')
export class BookControler {
  constructor(private bookService: BookService) {}

  @Get('home/:token')
  @ApiParam({
    name: 'token',
    description: 'return books of homepage based on the token',
  })
  async findMany(@Req() token) {
    return await this.bookService.findAll(token.returnData.id);
  }

  @Get('mybooks/:token')
  @ApiParam({
    name: 'token',
    description: 'return your books based on the token',
  })
  async findMyBooks(@Req() token) {
    return await this.bookService.findMyBooks(token.returnData.id);
  }

  @Get(':book_id/:token')
  @ApiParam({
    name: 'book id and token',
    description: 'return a book based on the book_id and the token',
  })
  async findOne(@Param('book_id') book_id: string, @Req() token) {
    return this.bookService.findOne(book_id, token.returnData.id);
  }
}
