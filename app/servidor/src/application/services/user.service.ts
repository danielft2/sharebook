import {
    ConflictException,
    Injectable,
    NotFoundException,
  } from '@nestjs/common';
  import { UserRepository } from '../../infraestructure/repositories/user.repository';
  import { User } from '../..//domain/entities/user.entity';
import { PrismaClientKnownRequestError } from '@prisma/client/runtime/library';
  
  @Injectable()
  export class UserService {
    constructor(private userRepository: UserRepository) {}
  
    async create(user: User) {
      const result: PrismaClientKnownRequestError | any = await this.userRepository.create(user);
      if (result.code === 'P2002') {
        throw new ConflictException();
      }
      return result;
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
      throw new NotFoundException()
    }
  }
  