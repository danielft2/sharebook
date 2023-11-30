import {
  Body,
  Controller,
  Delete,
  Get,
  Param,
  Post,
  Put,
  Req,
  UploadedFile,
  UseInterceptors,
} from '@nestjs/common';
import { BookService } from '../../application/services/book.service';
import { ApiBody, ApiParam } from '@nestjs/swagger';
import { Book } from '../../domain/entities/book.entity';
import { FileInterceptor } from '@nestjs/platform-express';

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
        nome: 'Diário de um Banana',
        sinopse:
          'A escola não é uma experiência agradável para o quase adolescente Greg Heffley, mas sim um campo minado que ele precisa enfrentar.',
        autor: ['Jeff Kinney'],
        usuario_id: '05304a82-8a06-11ee-b9d1-0242ac120002',
        edicao: 1,
        idioma: 'Espanhol',
        quer_receber: true,
        pode_buscar: false,
        capa: 'Diario de um Banana',
        imagens: [''],
        estado_id: '448358ea-c333-4982-ac6e-627b75d2e6cc',
        latitude: '-4.97813',
        longitude: '-39.0188',
      },
    },
  })
  // @UseInterceptors(FileInterceptor('cape'))
  async create(@Body() book: Book/* , @UploadedFile() cape: Express.Multer.File */) {
    return this.bookService.create(book);
  }

  @Put()
  @ApiBody({
    schema: {
      example: {
        id: 'valid_id',
        isbn: '9788498672220',
        nome: 'Diário de um Banana 2',
        sinopse:
          'A escola não é uma experiência agradável para o quase adolescente Greg Heffley, mas sim um campo minado que ele precisa enfrentar.',
        autor: ['Jeff Kinney'],
        usuario_id: '05304a82-8a06-11ee-b9d1-0242ac120002',
        edicao: 1,
        idioma: 'Espanhol',
        quer_receber: true,
        pode_buscar: false,
        capa: 'Diario de um Banana 2',
        imagens: [''],
        estado_id: '448358ea-c333-4982-ac6e-627b75d2e6cc',
        latitude: '-4.97813',
        longitude: '-39.0188',
      },
    },
  })
  async update(@Body() book: Book) {
    return this.bookService.update(book);
  }

  @Delete(':id')
  @ApiParam({
    name: 'book id',
    description: 'delete a book based on its id',
  })
  delete(@Param('id') id: string) {
    return this.bookService.delete(id);
  }
}
