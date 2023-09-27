import { Controller, Get, Post, Body, Patch, Param, Delete } from '@nestjs/common';
import { UserService } from 'src/application/services/user.service';
import { User } from 'src/domain/entities/user.entity';

@Controller('user')
export class UserController {
  constructor(private readonly userService: UserService) {}

  @Post()
  create(@Body()user: User) {
    return this.userService.create(user);
  }

  @Get()
  findAll() {
    return this.userService.findAll();
  }

  @Get(':id')
  async findOneById(@Param('id') id: string) {
    const userDataReturn = await this.userService.findOneById(id);
    return {
      ...userDataReturn,
      password: undefined
    }
  }
}
