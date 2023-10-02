import { Test, TestingModule } from "@nestjs/testing";
import { UserService } from "../../../src/application/services/user.service";
import { PrismaService } from "../../../src/infraestructure/database/prisma/prisma.service";
import { UserRepository } from "../../../src/infraestructure/repositories/user.repository";
import { ConflictException } from "@nestjs/common";

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
        providers: [
          UserService,
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
        it('should return a 409 status when some unique value cause conflict', async () => {
          const user = {
            nome:'fulando de tal', 
            email:'email1@email.com', 
            senha:'111111111',
            telefone: '99-99999999',
            cep: '99999-999',
            cidade: 'itapina',
            estado: 'ceara'
        };
    
          await expect(service.create(user)).rejects.toThrow(ConflictException);
        });
      });

      it('should create a user', async () => {
        const user = {
            nome:'fulando de tal', 
            email:'emailteste@email.com', 
            senha:'111111111',
            telefone: '91-99999999',
            cep: '99999-999',
            cidade: 'itapina',
            estado: 'ceara'
        };

        const userCreated = await service.create(user);

        expect(userCreated).toBeDefined();
      })
});