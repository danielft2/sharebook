import { MiddlewareConsumer, Module, NestModule } from '@nestjs/common';
import { JwtModule } from '@nestjs/jwt';

import { AuthService } from '../services/auth.service';
import { AuthController } from 'src/interfaces/controllers/auth.controller';
import { LocalStrategy } from '../strategies/local.strategy';
import { UserModule } from './user.module';
import { JwtStrategy } from '../strategies/jwt.strategy';
import { LoginValidationMiddleware } from 'src/interfaces/middlewares/login-validation.middleware';

@Module({
  imports:[UserModule, 
    JwtModule.register({secret: process.env.JWT_SECRET, signOptions: {expiresIn:'1h'}})
  ],
  controllers: [AuthController],
  providers: [AuthService, LocalStrategy, JwtStrategy],
})

export class AuthModule implements NestModule {
  configure(consumer: MiddlewareConsumer) {
    consumer.apply(LoginValidationMiddleware).forRoutes('login');
  }
}
