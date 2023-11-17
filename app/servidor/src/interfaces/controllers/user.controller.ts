import { Controller, Get, Post, Body, Patch, Param, Delete } from '@nestjs/common';
import { UserService } from '../../application/services/user.service';
import { User } from '../../domain/entities/user.entity';
import { IsPublic } from '../decorators/is-public.decorator';

@Controller('user')
export class UserController {
  constructor(private readonly userService: UserService) {}

  @IsPublic()
  @Post()
  async create(@Body() user: User) {
    const userDataReturn = await this.userService.create(user);
    return {
      ...userDataReturn,
      senha: undefined
    }
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
      senha: undefined
    }
  }
}
