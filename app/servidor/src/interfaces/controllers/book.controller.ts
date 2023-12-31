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
  UploadedFiles,
  UseInterceptors,
} from '@nestjs/common';
import { BookService } from '../../application/services/book.service';
import { ApiBody, ApiParam } from '@nestjs/swagger';
import { Book } from '../../domain/entities/book.entity';
import {
  FileFieldsInterceptor,
  FileInterceptor,
} from '@nestjs/platform-express';
import { CreatedBookDto } from '../dto/createdBook.dto';
import { UpdatedBookDto } from '../dto/updatedBook.dto';

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
        estado_id: '448358ea-c333-4982-ac6e-627b75d2e6cc',
        latitude: '-4.97813',
        longitude: '-39.0188',
        cape: 'file',
        images: 'files[]',
      },
    },
  })
  @UseInterceptors(
    FileFieldsInterceptor([
      {
        name: 'cape',
        maxCount: 1,
      },
      {
        name: 'images',
        maxCount: 4,
      },
    ]),
  )
  async create(
    @Body() book: CreatedBookDto,
    @UploadedFiles()
    collection: Record<'cape' | 'images', Express.Multer.File[]>,
  ) {
    const autores: string[] = book.autor.split(', ');
    const generos: string[] = book.genero.split(', ');
    const data: Book = {
      ...book,
      capa: '',
      imagens: [''],
      autor: autores,
    };
    return this.bookService.create(data, collection, generos);
  }

  @Put(':id')
  @ApiBody({
    schema: {
      example: {
        id: 'valid_id',
        isbn: '9788498672220',
        nome: 'Diário de um Banana 2',
        sinopse:
          'A escola não é uma experiência agradável para o quase adolescente Greg Heffley, mas sim um campo minado que ele precisa enfrentar.',
        autor: ['Jeff Kinney'],
        edicao: 1,
        idioma: 'Espanhol',
        quer_receber: true,
        pode_buscar: false,
        estado_id: '448358ea-c333-4982-ac6e-627b75d2e6cc',
        cape: 'file',
      },
    },
  })
  @UseInterceptors(FileInterceptor('cape'))
  async update(
    @Param('id') id: string,
    @Body() book: UpdatedBookDto,
    @UploadedFile() cape: Express.Multer.File,
  ) {
    const autores: string[] = book.autor.split(', ');
    const genders: string[] = book.genero.split(', ');
    const data: Book = {
      isbn: book.isbn,
      nome: book.nome,
      sinopse: book.sinopse,
      usuario_id: '',
      edicao: book.edicao,
      idioma: book.idioma,
      pode_buscar: book.pode_buscar,
      quer_receber: book.quer_receber,
      estado_id: book.estado_id,
      latitude: '',
      longitude: '',
      capa: '',
      imagens: [''],
      autor: autores,
    };
    return this.bookService.update(id, data, cape, genders);
  }

  @Delete(':id')
  @ApiParam({
    name: 'book id',
    description: 'delete a book based on its id',
  })
  delete(@Param('id') id: string) {
    return this.bookService.delete(id);
  }

  @Get('/search/:query')
  async searchBook(@Param('query') query: string) {
    return this.bookService.searchBook(query);
  }
}
