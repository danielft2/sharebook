import {
    ConflictException,
    Injectable,
    NotFoundException,
  } from '@nestjs/common';
  import { UserRepository } from '../../infraestructure/repositories/user.repository';
  import { User } from '../../domain/entities/user.entity';
  
  @Injectable()
  export class UserService {
    constructor(private userRepository: UserRepository) {}
  
    async create(user: User) {
      const existUser = await this.userRepository.findByEmail(
        user.email,
      );
      if (existUser) throw new ConflictException();
  
      return this.userRepository.create(user);
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
  