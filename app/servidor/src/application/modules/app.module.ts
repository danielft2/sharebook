import {
  MiddlewareConsumer,
  Module,
  NestModule,
  RequestMethod,
} from '@nestjs/common';
import { AppController } from '../../interfaces/controllers/app.controller';
import { AppService } from '../services/app.service';
import { UserModule } from './user.module';
import { AuthModule } from './auth.module';
import { APP_GUARD } from '@nestjs/core';
import { JwtAuthGuard } from '../../interfaces/guards/jwt-auth.guard';
import { BookModule } from './book.module';
import { AlertModule } from './alert.module';
import { JwtService } from '@nestjs/jwt';
import { RescueModule } from './rescue.module';
import { JwtMiddleware } from 'src/interfaces/middlewares/jwt-request.middleware';
import { BookStateModule } from './book-state.module';
import { GenderModule } from './gender.module';

@Module({
  imports: [
    UserModule,
    AuthModule,
    BookModule,
    AlertModule,
    RescueModule,
    BookStateModule,
    GenderModule,
  ],
  controllers: [AppController],
  providers: [
    AppService,
    { provide: APP_GUARD, useClass: JwtAuthGuard },
    JwtService,
  ],
})
export class AppModule {
  configure(consumer: MiddlewareConsumer) {
    consumer
      .apply(JwtMiddleware)
      .forRoutes(
        { path: '/book', method: RequestMethod.GET },
        { path: '/rescue/{id}', method: RequestMethod.GET },
      );
  }
}
