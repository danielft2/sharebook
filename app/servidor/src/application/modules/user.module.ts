import { Module } from '@nestjs/common';
import { UserService } from '../services/user.service';
import { UserController } from '../../interfaces/controllers/user.controller'; 
import { PrismaModule } from '../../infraestructure/database/prisma/prisma.module'; 
import { UserRepository } from '../../infraestructure/repositories/user.repository';
import { JwtModule } from '@nestjs/jwt';
import { JwtStrategy } from '../strategies/jwt.strategy';

@Module({
  imports: [PrismaModule, JwtModule.register({secret: process.env.JWT_SECRET, signOptions: {expiresIn:'1d'}})],
  controllers: [UserController],
  providers: [UserService, UserRepository, JwtStrategy],
  exports:[UserService]
})
export class UserModule {}
