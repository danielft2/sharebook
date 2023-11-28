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
    const module: TestingModule = await Test.createTestingModule({
      providers: [
        BookService,
        UserRepository,
        IbgeFinderService,
        UserGendersService,
        BookGendersService,
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
    });

    it('should return a list of books', async () => {
      const books = await service.findAll(
        '05304a82-8a06-11ee-b9d1-0242ac120002',
      );

      expect(
        books.availableBooks.length ||
          books.nextToYou.length ||
          books.favoriteGenders,
      ).toBeGreaterThan(0);
    });
  });
});
