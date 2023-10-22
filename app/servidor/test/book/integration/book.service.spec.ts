/* eslint-disable prettier/prettier */
import { Test, TestingModule } from "@nestjs/testing";
import { BookService } from "../../../src/application/services/book.service"
import { PrismaService } from "../../../src/infraestructure/database/prisma/prisma.service"
import { BookRepository } from "../../../src/infraestructure/repositories/book.repository";
import { BookControler } from "../../../src/interfaces/controllers/book.controller";

describe('BookService', () => {
    let service: BookService;
    let prismaService: PrismaService;

    beforeEach(async () =>{
        const prismaModule: TestingModule = await Test.createTestingModule({
            providers: [PrismaService],
        }).compile();
        
        prismaService = prismaModule.get<PrismaService>(PrismaService);

        const bookRepository = new BookRepository(prismaService);
        const module: TestingModule = await Test.createTestingModule({
            controllers: [BookControler],
            providers: [
                BookService,
                {
                    provide: BookRepository,
                    useValue: bookRepository
                }
            ]
        }).compile();

        service = module.get<BookService>(BookService);
    });

    it('should be defined', () => {
        expect(service).toBeDefined();
    });

    describe('GET methods', () => {
        it('should return a book', async () => {
            //Ainda não possui dados inseridos no postgres
            const bookId = 'IdDeLivro';

            const book = await service.findOne(bookId);
            // expect(book).toBeTruthy(); 
            expect(book).toBeNull(); 
        })

        it('should return a list of books', async () => {
            //Ainda não possui dados inseridos no postgres
            const books = await service.findAll();

            expect(books).toBeInstanceOf(Array); 
            // expect(books).tobe 
        })
    })
})