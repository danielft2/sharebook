import { Test, TestingModule } from "@nestjs/testing";
import { PrismaService } from "../../../src/infraestructure/database/prisma/prisma.service";
import { UserRepository } from "../../../src/infraestructure/repositories/user.repository";
import { ConflictException } from "@nestjs/common";
import { AlertService } from "src/application/services/alert.service";
import { AlertRepository } from "src/infraestructure/repositories/alert.repository";

describe('AlertService', () => {
    let service: AlertService;
    let prismaService: PrismaService;
  
    beforeEach(async () => {
      const prismaModule: TestingModule = await Test.createTestingModule({
        providers: [PrismaService]
      }).compile();
      prismaService = prismaModule.get<PrismaService>(PrismaService);
  
      const alertRepository = new UserRepository(prismaService);
      const module: TestingModule = await Test.createTestingModule({
        providers: [
          AlertService,
          {
            provide: AlertRepository,
            useValue: alertRepository,
          },
        ],
      }).compile();
  
      service = module.get<AlertService>(AlertService);
    });

    it('should be defined', () => {
        expect(service).toBeDefined();
     });

     describe('POST methods', () => {
      it('should create a alert', async () => {
        const alert = {
            bookName:'O iluminado', 
            usuarioId: '778183ed-6812-4b56-a0eb-c7666480038e',
            isFilteredByLocalization: false,
            isFilteredBySend: false
        };

        const userCreated = await service.createAlert(alert.usuarioId, alert.bookName, alert.isFilteredByLocalization, alert.isFilteredBySend);

        expect(userCreated).toBeDefined();
      })
      });
});