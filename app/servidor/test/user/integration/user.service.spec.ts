import { Test, TestingModule } from "@nestjs/testing";
import { UserService } from "../../../src/application/services/user.service";
import { PrismaService } from "../../../src/infraestructure/database/prisma/prisma.service";
import { UserRepository } from "../../../src/infraestructure/repositories/user.repository";
import { ConflictException } from "@nestjs/common";
import { JwtModule } from "@nestjs/jwt";
import { JwtStrategy } from "../../../src/application/strategies/jwt.strategy";
import { SupabaseService } from "../../../src/application/services/supabase.service";
import { BookGendersService } from "../../../src/application/services/book-gender.service";

describe('UserService', () => {
    let service: UserService;
    let prismaService: PrismaService;
  
    beforeEach(async () => {
      const prismaModule: TestingModule = await Test.createTestingModule({
        providers: [PrismaService]
      }).compile();
      prismaService = prismaModule.get<PrismaService>(PrismaService);
  
      const userRepository = new UserRepository(prismaService);
      const module: TestingModule = await Test.createTestingModule({
        imports: [JwtModule.register({secret: process.env.JWT_SECRET, signOptions: {expiresIn:'1h'}})],
        providers: [
          UserService,
          JwtStrategy,
          {
            provide: UserRepository,
            useValue: userRepository,
          },
        ],
      }).compile();
  
      service = module.get<UserService>(UserService);
    });

    it('should be defined', () => {
        expect(service).toBeDefined();
     });

     describe('POST methods', () => {
      it('should create a user', async () => {
        const user = {
            nome:'fulando de tal', 
            email:'email1@email.com', 
            senha:'111111111',
            telefone: '91-99999999',
            cep: '99999-999',
            cidade: 'itapiuna',
            estado: 'ceara'
        };

        const userCreated = await service.create(user);

        expect(userCreated).toBeDefined();
      })

      it('should return a 409 status when some unique value cause conflict', async () => {
          const user = {
            nome:'fulando de tal', 
            email:'email1@email.com', 
            senha:'111111111',
            telefone: '99-99999999',
            cep: '99999-999',
            cidade: 'itapiuna',
            estado: 'ceara'
        };
    
          await expect(service.create(user)).rejects.toThrow(ConflictException);
        });
      });
});
