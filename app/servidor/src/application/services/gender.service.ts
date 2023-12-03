import { Injectable } from '@nestjs/common';
import { GenderRepository } from '../../infraestructure/repositories/gender.repository';
import { Gender } from '../../domain/entities/gender.entity';

@Injectable()
export class GenderService {
  constructor(private genderRepository: GenderRepository) {}

  async findOne(id: string) {
    return await this.genderRepository.findOne(id);
  }

  async findMany() {
    return await this.genderRepository.findMany();
  }

  async create(gender: Gender) {
    return await this.genderRepository.create(gender);
  }
}
