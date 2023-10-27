import { Module } from "@nestjs/common";
import { BookControler } from "../../interfaces/controllers/book.controller";
import { BookService } from "../services/book.service";
import { BookRepository } from "../../infraestructure/repositories/book.repository";
import { PrismaModule } from "../../infraestructure/database/prisma/prisma.module";
import { IbgeFinderService } from "../services/ibge-finder.service";
import { UserService } from "../services/user.service";
import { UserModule } from "./user.module";

@Module({
    imports: [PrismaModule, UserModule],
    controllers: [BookControler],
    providers: [BookService, BookRepository, IbgeFinderService],
})
export class BookModule {}