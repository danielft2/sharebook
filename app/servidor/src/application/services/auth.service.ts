import { Injectable } from '@nestjs/common';
import * as bcrypt from 'bcrypt';
import { UserService } from './user.service';
import { User } from '../../domain/entities/user.entity';
import { JwtService } from '@nestjs/jwt';
import { UserPayload } from 'src/domain/models/UserPayload';
import { UserDataReturn } from 'src/domain/models/UserDataReturn';

@Injectable()
export class AuthService {
  constructor(
    private readonly userService: UserService,
    private readonly jwtService: JwtService,
  ) {}

  async validateUser(email: string, password: string) {
    const user = await this.userService.findOneByEmail(email);

    if (user) {
      const isPasswordValid = await bcrypt.compare(password, user.senha);
      //const isPasswordValid = password === user.senha;

      if (isPasswordValid)
        return {
          ...user,
          password: undefined,
        };
    }

    throw new Error('The provided data is incorrect');
  }

  async login(user: User) {
    const payload: UserPayload = {
      sub: user.id,
      email: user.email,
      name: user.nome,
      cep: user.cep,
      phone: user.telefone,
    };

    const jwtToken = this.jwtService.sign(payload);
    const returnData: UserDataReturn = {
      cep: user.cep,
      email: user.email,
      phone: user.telefone,
      name: user.nome,
      id: user.id,
    };

    return {
      returnData,
      access_token: jwtToken,
    };
  }
}
