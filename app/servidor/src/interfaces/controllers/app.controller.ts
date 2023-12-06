import { Controller, Get } from '@nestjs/common';
import { AppService } from '../../application/services/app.service';
import { IsPublic } from '../decorators/is-public.decorator';
import { CurrentUser } from '../decorators/current-user.decorator';
import { UserService } from '../../application/services/user.service';

@Controller()
export class AppController {
  constructor(private readonly appService: AppService, private readonly userService: UserService) {}

  @IsPublic()
  @Get()
  getHello(): string {
    return this.appService.getHello();
  }

  @Get('me')
  async getMe(@CurrentUser() userPayload) {
    const user = await this.userService.findOneById(userPayload.id);
    const userDataReturn = {
      nome: userPayload.name,
      cidade: user.cidade,
      estado: user.estado,
      foto_perfil: user.foto_perfil
    }
    return userDataReturn;
  }
}
