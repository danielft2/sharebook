import { Injectable } from '@nestjs/common';
import { GenderRepository } from '../../infraestructure/repositories/gender.repository';

@Injectable()
export class GenderService {
  constructor(private genderRepository: GenderRepository) {}

  async findOne(id: string) {
    return await this.genderRepository.findOne(id);
  }

  async findMany() {
    return await this.genderRepository.findMany();
  }
}
