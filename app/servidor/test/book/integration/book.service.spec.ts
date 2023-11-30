import { Test, TestingModule } from '@nestjs/testing';
import { BookService } from '../../../src/application/services/book.service';
import { PrismaService } from '../../../src/infraestructure/database/prisma/prisma.service';
import { BookRepository } from '../../../src/infraestructure/repositories/book.repository';
import { UserRepository } from '../../../src/infraestructure/repositories/user.repository';
import { IbgeFinderService } from '../../../src/application/services/ibge-finder.service';
import { UserGendersService } from '../../../src/application/services/user-gender.service';
import { BookGendersService } from '../../../src/application/services/book-gender.service';
import { UserGendersRepository } from '../../../src/infraestructure/repositories/user-gender.repository';
import { BookGendersRepository } from '../../../src/infraestructure/repositories/book-gender.repository';
import { RescueService } from '../../../src/application/services/rescue.service';
import { RescueRepository } from '../../../src/infraestructure/repositories/rescue.repository';
import { BookStateService } from '../../../src/application/services/book-state.service';
import { BookStateRepository } from '../../../src/infraestructure/repositories/book-state.repository';
import { GenderService } from '../../../src/application/services/gender.service';
import { SupabaseService } from '../../../src/application/services/supabase.service';
import { GenderRepository } from '../../../src/infraestructure/repositories/gender.repository';
import { Book } from '../../../src/domain/entities/book.entity';
import * as fs from 'fs-extra';
describe('BookService', () => {
  let service: BookService;
  let prismaService: PrismaService;

  beforeEach(async () => {
    const prismaModule: TestingModule = await Test.createTestingModule({
      providers: [PrismaService],
    }).compile();

    prismaService = prismaModule.get<PrismaService>(PrismaService);

    const bookRepository = new BookRepository(prismaService);
    const userGenderRepository = new UserGendersRepository(prismaService);
    const bookGenderRepository = new BookGendersRepository(prismaService);
    const userRepository = new UserRepository(prismaService);
    const rescueRepository = new RescueRepository(prismaService);
    const bookStateRepository = new BookStateRepository(prismaService);
    const genderRepository = new GenderRepository(prismaService);
    const module: TestingModule = await Test.createTestingModule({
      providers: [
        BookService,
        UserRepository,
        IbgeFinderService,
        UserGendersService,
        BookGendersService,
        BookStateService,
        RescueService,
        GenderService,
        SupabaseService,
        {
          provide: BookRepository,
          useValue: bookRepository,
        },
        {
          provide: UserRepository,
          useValue: userRepository,
        },
        {
          provide: BookGendersRepository,
          useValue: bookGenderRepository,
        },
        {
          provide: UserGendersRepository,
          useValue: userGenderRepository,
        },
        {
          provide: RescueRepository,
          useValue: rescueRepository,
        },
        {
          provide: BookStateRepository,
          useValue: bookStateRepository,
        },
        {
          provide: GenderRepository,
          useValue: genderRepository,
        },
      ],
    }).compile();

    service = module.get<BookService>(BookService);
  });

  it('should be defined', () => {
    expect(service).toBeDefined();
  });

  describe('GET methods', () => {
    it('should return a book', async () => {
      const bookId = 'dfe8be88-3b0d-496e-baab-395b63751f43';
      const userId = '58d43f0c-5442-48b4-8103-4ec2bcd42ea6';
      const book = await service.findOne(bookId, userId);
      expect(book).toBeTruthy();
    }, 15000);

    it('should return a list of books', async () => {
      const books = await service.findAll(
        '05304a82-8a06-11ee-b9d1-0242ac120002',
      );

      expect(
        books.availableBooks.length ||
          books.nextToYou.length ||
          books.favoriteGenders,
      ).toBeGreaterThan(0);
    }, 25000);

    it('should return a list of user books', async () => {
      const books = await service.findMyBooks(
        '05304a82-8a06-11ee-b9d1-0242ac120002',
      );

      expect(books).toBeInstanceOf(Array);
    }, 20000);
  });

  describe('POST methods', () => {
    it('should create a book', async () => {
      const book: Book = {
        isbn: '9788498672220',
        nome: 'Diário de um Banana 2',
        sinopse:
          'A escola não é uma experiência agradável para o quase adolescente Greg Heffley, mas sim um campo minado que ele precisa enfrentar.',
        autor: ['Jeff Kinney'],
        usuario_id: '05304a82-8a06-11ee-b9d1-0242ac120002',
        edicao: 1,
        idioma: 'Português',
        quer_receber: true,
        pode_buscar: false,
        capa: 'Diario de um Banana 2',
        imagens: [''],
        estado_id: '448358ea-c333-4982-ac6e-627b75d2e6cc',
        latitude: '-4.97813',
        longitude: '-39.0188',
      };
      const fileBuffer = await fs.readFile('test/assets/Diario de um banana.jpg');

      expect(await service.create(book, fileBuffer)).resolves;
    });
  });
});
