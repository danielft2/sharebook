import { Injectable } from '@nestjs/common';
import { BookStateRepository } from '../../infraestructure/repositories/book-state.repository';

@Injectable()
export class BookStateService {
  constructor(private bookStateRepository: BookStateRepository) {}

  async findOne(id: string) {
    return await this.bookStateRepository.findOne(id);
  }

  async findMany() {
    return await this.bookStateRepository.findMany();
  }
}
