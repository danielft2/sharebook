import { Injectable } from '@nestjs/common';
import { UserGendersRepository } from '../../infraestructure/repositories/user-gender.repository';

@Injectable()
export class UserGendersService {
  constructor(private userGenderRepository: UserGendersRepository) {}

  async findAll() {
    return await this.userGenderRepository.findMany();
  }

  async findAllByUserId(usuario_id: string) {
    const userGenders = await this.findAll();
    const genders = await Promise.all(
      userGenders.map(async (userGender) => {
        if (userGender.usuario_id === usuario_id) {
          return userGender.genero_id;
        }
      }),
    );
    return genders;
  }
}
