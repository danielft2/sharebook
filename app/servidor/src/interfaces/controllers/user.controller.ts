import {
  Controller,
  Get,
  Post,
  Body,
  Patch,
  Param,
  Delete,
  Next,
} from '@nestjs/common';
import { UserService } from '../../application/services/user.service';
import { User } from '../../domain/entities/user.entity';
import { IsPublic } from '../decorators/is-public.decorator';
import { NextFunction } from 'express';
import { AuthService } from '../../application/services/auth.service';

@Controller('user')
export class UserController {
  constructor(
    private readonly userService: UserService,
    private readonly authService: AuthService,
  ) {}

  @IsPublic()
  @Post()
  async create(@Body() user: User, @Next() next: NextFunction) {
    //const userDataReturn = await this.userService.create(user);
    await this.userService.create(user);
    return next();
    // return {
    //   ...userDataReturn,
    //   senha: undefined,
    // };
  }

  @IsPublic()
  @Post()
  async loginAfterCreate(@Body() user: User) {
    const createdUser = await this.userService.findOneByEmail(user.email);
    const result = await this.authService.login(createdUser);
    const data = {
      ...createdUser,
      senha: undefined,
      access_token: result.access_token,
    };
    return data;
    // const userDataReturn = await this.userService.create(user);
    // return {
    //   ...userDataReturn,
    //   senha: undefined,
    // };
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
      senha: undefined,
    };
  }
}
