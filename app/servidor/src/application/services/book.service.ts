import { Injectable } from '@nestjs/common';
import { BookRepository } from '../../infraestructure/repositories/book.repository';

@Injectable()
export class BookService{
    constructor(private bookRepository : BookRepository) {}

    async findAll(){
        return this.bookRepository.findMany();
    }

    async findOne(isbn: string){
        return this.bookRepository.findOne(isbn);
    }
}