import { Module } from "@nestjs/common";
import { BookControler } from "../../interfaces/controllers/book.controller";
import { BookService } from "../services/book.service";
import { BookRepository } from "../../infraestructure/repositories/book.repository";
import { PrismaModule } from "../../infraestructure/database/prisma/prisma.module";

@Module({
    imports: [PrismaModule],
    controllers: [BookControler],
    providers: [BookService, BookRepository],
})
export class BookModule {}