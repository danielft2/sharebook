import { JwtModule } from "@nestjs/jwt";
import { Test, TestingModule } from "@nestjs/testing";
import { AuthService } from "../../../src/application/services/auth.service";
import { UserService } from "../../../src/application/services/user.service";
import { JwtStrategy } from "../../../src/application/strategies/jwt.strategy";
import { PrismaService } from "../../../src/infraestructure/database/prisma/prisma.service";
import { UserRepository } from "../../../src/infraestructure/repositories/user.repository";

import { UnauthorizedException } from '@nestjs/common';
import * as dotenv from 'dotenv';
import { User } from "src/domain/entities/user.entity";
dotenv.config({ path: 'test/.env.test' });

describe('AuthService', () => {
    let service: AuthService;
    let prismaService: PrismaService;
    let userRepositoryGetted: UserRepository;
  
    beforeEach(async () => {
      const prismaModule: TestingModule = await Test.createTestingModule({
        providers: [PrismaService]
      }).compile();
      prismaService = prismaModule.get<PrismaService>(PrismaService);
  
      const userRepository = new UserRepository(prismaService);
      const module: TestingModule = await Test.createTestingModule({
        imports: [JwtModule.register({secret: process.env.JWT_SECRET, signOptions: {expiresIn:'1h'}})],
        providers: [
          AuthService,
          UserService,
          JwtStrategy,
          {
            provide: UserRepository,
            useValue: userRepository,
          },
        ],
      }).compile();
  
      service = module.get<AuthService>(AuthService);
      userRepositoryGetted = module.get<UserRepository>(UserRepository)
    });

    it('should be defined', () => {
        expect(service).toBeDefined();
    });

    const userDataAnouthenticated = {
        nome:'fulando de tal',
        email:'email@email.com',
        senha:'111111111',
        telefone: '88-888888888',
        cep: '99999-999',
        estado: 'itapiuna',
        cidade: 'ceara'
      };

    const userDataAuthenticated: User = {
        id: "528e7277-ed6a-4768-b35a-cc6a669f5003",
        nome:'Alexson1',
        email:'email1@email.com',
        senha:'111111111',
        telefone: '99-99999999',
        cep: '99999-999',
        estado: 'itapiuna',
        cidade: 'ceara'
    };
  
    it('should return a 409 when data is incorrect', async () => {
        await expect(service.validateUser(userDataAnouthenticated.email, userDataAnouthenticated.senha)).rejects.toThrow(Error);
    });

    it('should return a user when data is correct', async () => {
        const responseValidate = await service.validateUser(userDataAuthenticated.email, userDataAuthenticated.senha);
        expect(Object.keys(responseValidate)).toEqual(["id", "nome", "email", "telefone", "senha", "cep", "estado", "cidade", "foto_perfil"]);
    });

    it('it should return user data and jwt token when authentication process is complete', async () => {
        const returnValue = await service.login(userDataAuthenticated);
        expect(Object.keys(returnValue)).toEqual(["returnData", "access_token"]);
    });
});
