import { Body, Controller, Get, Param, Post, Req } from '@nestjs/common';
import { BookService } from '../../application/services/book.service';
import { ApiBody, ApiParam } from '@nestjs/swagger';
import { Book } from '../../domain/entities/book.entity';

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

  @Post()
  @ApiBody({
    schema: {
      example: {
        isbn: '9788498672220',
        name: 'Diário de um Banana',
        synopsis:
          'A escola não é uma experiência agradável para o quase adolescente Greg Heffley, mas sim um campo minado que ele precisa enfrentar.',
        writer: ['Jeff Kinney'],
        owner_id: '05304a82-8a06-11ee-b9d1-0242ac120002',
        edition: 1,
        language: 'Espanhol',
        can_receive: true,
        can_get: false,
        cape: 'Diario de um Banana',
        images: [''],
        state_id: '448358ea-c333-4982-ac6e-627b75d2e6cc',
        latitude: '-4.97813',
        longitude: '-39.0188',
      },
    },
  })
  async create(@Body() book: Book) {
    return this.bookService.create(book);
  }
}
