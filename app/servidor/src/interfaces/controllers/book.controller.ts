import { Controller, Get, Param } from "@nestjs/common";
import { BookService } from "../../application/services/book.service";

@Controller('book')
export class BookControler {
    constructor(private bookService: BookService) {}

    @Get('home/:user_id')
    async findMany(@Param('user_id') user_id: string){
        return this.bookService.findAll(user_id);
    }
    
    @Get(':isbn')
    async findOne(@Param('isbn') isbn: string){
        return this.bookService.findOne(isbn);
    }
}