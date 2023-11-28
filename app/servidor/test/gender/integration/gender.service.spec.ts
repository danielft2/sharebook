import { Test, TestingModule } from '@nestjs/testing';
import { GenderService } from '../../../src/application/services/gender.service';
import { PrismaService } from '../../../src/infraestructure/database/prisma/prisma.service';
import { GenderRepository } from '../../../src/infraestructure/repositories/gender.repository';
import { GenderController } from '../../../src/interfaces/controllers/gender.controller';

describe('GenderService', () => {
  let service: GenderService;
  let prismaService: PrismaService;

  beforeEach(async () => {
    const prismaModule: TestingModule = await Test.createTestingModule({
      providers: [PrismaService],
    }).compile();

    prismaService = prismaModule.get<PrismaService>(PrismaService);

    const genderRepository = new GenderRepository(prismaService);

    const module: TestingModule = await Test.createTestingModule({
      controllers: [GenderController],
      providers: [
        GenderService,
        {
          provide: GenderRepository,
          useValue: genderRepository,
        },
      ],
    }).compile();

    service = module.get<GenderService>(GenderService);
  });

  it('should be defined', () => {
    expect(service).toBeDefined();
  });

  describe('GET methods', () => {
    it('should return a single gender', async () => {
      const genderId = '1c527b0a-6da8-4ccd-b23f-fcd946cf488a';
      const gender = await service.findOne(genderId);
      expect(gender).toBeTruthy();
    });

    it('should return a list of genders', async () => {
      const genders = await service.findMany();
      expect(genders).toBeInstanceOf(Array);
    });
  });
});
