import {
  ConflictException,
  Injectable,
  NotFoundException,
} from '@nestjs/common';
import { UserRepository } from '../../infraestructure/repositories/user.repository';
import { User } from '../../domain/entities/user.entity';
import { JwtService } from '@nestjs/jwt';

@Injectable()
export class UserService {
  constructor(
    private userRepository: UserRepository,
    private jwtService: JwtService,
  ) {}

  async create(user: User) {
    const existUser = await this.userRepository.findByEmail(user.email);
    if (existUser) throw new ConflictException();
    const userDataCreated = await this.userRepository.create(user);

    const payload = {
      sub: user.id,
      email: user.email,
      name: user.nome,
      cep: user.cep,
      phone: user.telefone,
    };

    const userDataReturn = {
      ...userDataCreated,
      // access_token: this.jwtService.sign(payload),
    };

    return userDataReturn;
  }

  async findAll() {
    return await this.userRepository.findAll();
  }

  async findOneById(id: string) {
    const user = await this.userRepository.findById(id);
    if (user) {
      const userReturn = await this.userRepository.findById(user.id);

      return userReturn;
    }

    throw new NotFoundException();
  }

  async findOneByEmail(email: string) {
    const user = await this.userRepository.findByEmail(email);
    if (user) return user;
    throw new NotFoundException();
  }
}
