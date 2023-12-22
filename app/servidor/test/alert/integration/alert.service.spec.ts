import { Test, TestingModule } from '@nestjs/testing';
import { PrismaService } from '../../../src/infraestructure/database/prisma/prisma.service';
import { UserRepository } from '../../../src/infraestructure/repositories/user.repository';
import { ConflictException } from '@nestjs/common';
import { AlertService } from '../../../src/application/services/alert.service';
import { AlertRepository } from '../../../src/infraestructure/repositories/alert.repository';
import { Alert } from '../../../src/domain/entities/alert.entity';

describe('AlertService', () => {
  let service: AlertService;
  let prismaService: PrismaService;

  beforeEach(async () => {
    const prismaModule: TestingModule = await Test.createTestingModule({
      providers: [PrismaService],
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

  // describe('POST methods', () => {
  //   it('should create a alert', async () => {
  //     const bookName = 'Diário de Anne Frank';
  //     const isFilteredByLocalization = true;
  //     const isFilteredBySend = false;
  //     const usuarioId = '05304a82-8a06-11ee-b9d1-0242ac120002';
  //     expect(
  //       await service.createAlert(
  //         usuarioId,
  //         bookName,
  //         isFilteredByLocalization,
  //         isFilteredBySend,
  //       ),
  //     ).toBeTruthy();
  //   });
  // });
});
