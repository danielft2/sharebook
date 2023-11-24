import { Test, TestingModule } from '@nestjs/testing';
import { BookService } from '../../../src/application/services/book.service';
import { PrismaService } from '../../../src/infraestructure/database/prisma/prisma.service';
import { BookRepository } from '../../../src/infraestructure/repositories/book.repository';
import { BookControler } from '../../../src/interfaces/controllers/book.controller';
import { UserRepository } from '../../../src/infraestructure/repositories/user.repository';
import { IbgeFinderService } from '../../../src/application/services/ibge-finder.service';

describe('BookService', () => {
  let service: BookService;
  let prismaService: PrismaService;

  beforeEach(async () => {
    const prismaModule: TestingModule = await Test.createTestingModule({
      providers: [PrismaService],
    }).compile();

    prismaService = prismaModule.get<PrismaService>(PrismaService);

    const bookRepository = new BookRepository(prismaService);
    const userRepository = new UserRepository(prismaService);
    const module: TestingModule = await Test.createTestingModule({
      controllers: [BookControler],
      providers: [
        BookService,
        UserRepository,
        IbgeFinderService,
        {
          provide: BookRepository,
          useValue: bookRepository,
        },
        {
          provide: UserRepository,
          useValue: userRepository,
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

      const book = await service.findOne(bookId);
      expect(book).toBeTruthy();
    });

    it('should return a list of books', async () => {
      const books = await service.findAll(
        '05304a82-8a06-11ee-b9d1-0242ac120002',
      );

      expect(books).toBeInstanceOf(Object);
    });
  });
});
