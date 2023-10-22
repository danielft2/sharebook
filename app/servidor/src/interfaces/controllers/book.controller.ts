import { Controller, Get, Param } from "@nestjs/common";
import { BookService } from "../../../src/application/services/book.service";

@Controller('book')
export class BookControler {
    constructor(private bookService: BookService) {}

    @Get()
    async findAll(){
        return this.bookService.findAll();
    }
    
    @Get(':isbn')
    async findOne(@Param('isbn') isbn: string){
        return this.bookService.findOne(isbn);
    }
}