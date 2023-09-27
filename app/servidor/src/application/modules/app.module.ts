import { MiddlewareConsumer, Module, NestModule } from '@nestjs/common';
import { AppController } from 'src/interfaces/controllers/app.controller';
import { AppService } from '../services/app.service';
import { UserModule } from './user.module';
import { AuthModule } from './auth.module';
import { APP_GUARD } from '@nestjs/core';
import { JwtAuthGuard } from 'src/interfaces/guards/jwt-auth.guard';
import { RequestLoggingMiddleware } from 'src/interfaces/middlewares/request-loggin.middleware';

@Module({
  imports: [UserModule, AuthModule],
  controllers: [AppController],
  providers: [AppService, { provide: APP_GUARD, useClass: JwtAuthGuard}],
})
export class AppModule {}
