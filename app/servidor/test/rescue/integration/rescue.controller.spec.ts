import { Test, TestingModule } from '@nestjs/testing';
import { RescueController } from '../../../src/interfaces/controllers/rescue.controller';
import { PrismaService } from '../../../src/infraestructure/database/prisma/prisma.service';
import { RescueRepository } from '../../../src/infraestructure/repositories/rescue.repository';
import { SupabaseService } from '../../../src/application/services/supabase.service';
import { BookGendersService } from '../../../src/application/services/book-gender.service';
import { BookRepository } from '../../../src/infraestructure/repositories/book.repository';
import { BookStateRepository } from '../../../src/infraestructure/repositories/book-state.repository';
import { UserRepository } from '../../../src/infraestructure/repositories/user.repository';
import { BookGendersRepository } from '../../../src/infraestructure/repositories/book-gender.repository';
import { GenderService } from '../../../src/application/services/gender.service';
import { GenderRepository } from '../../../src/infraestructure/repositories/gender.repository';
import { RescueService } from '../../../src/application/services/rescue.service';

describe('RescueController', () => {
  let controller: RescueController;
  let prismaService: PrismaService;

  beforeEach(async () => {
    const prismaModule: TestingModule = await Test.createTestingModule({
      providers: [PrismaService],
    }).compile();
    prismaService = prismaModule.get<PrismaService>(PrismaService);

    const rescueRepository = new RescueRepository(prismaService);
    const bookRepository = new BookRepository(prismaService);
    const bookStateRepository = new BookStateRepository(prismaService);
    const userRepository = new UserRepository(prismaService);
    const bookGendersRepository = new BookGendersRepository(prismaService);
    const genderRepository = new GenderRepository(prismaService);
    const module: TestingModule = await Test.createTestingModule({
      controllers: [RescueController],
      providers: [
        RescueService,
        SupabaseService,
        BookGendersService,
        GenderService,
        {
          provide: RescueRepository,
          useValue: rescueRepository,
        },
        {
          provide: BookRepository,
          useValue: bookRepository,
        },
        {
          provide: BookStateRepository,
          useValue: bookStateRepository,
        },
        {
          provide: UserRepository,
          useValue: userRepository,
        },
        {
          provide: BookGendersRepository,
          useValue: bookGendersRepository,
        },
        {
          provide: GenderRepository,
          useValue: genderRepository,
        },
      ],
    }).compile();

    controller = module.get<RescueController>(RescueController);
  });

  it('should be defined', () => {
    expect(controller).toBeDefined();
  });

  describe('POST methods', () => {
    it('should create a rescue', async () => {
      const rescueData = {
        idRescueUser: '1734ed59-eaa8-4c31-9b38-f546790668e8',
        idBook: '654a5bfa-2245-4094-9b8b-93727a7bc778',
        idBookFromRescue: 'eb43f38c-887b-410e-ad5f-624861cf6c95',
        status: 'Aguardando Confirmação',
      };

      const rescueCreated = await controller.create(rescueData);
      expect(rescueCreated).toBeDefined();
    });
  });

  // describe('PUT methods', () => {
  //   it('should update a rescue', async () => {
  //     const rescues = (await service.findAll()).find((rescue) => {
  //       rescue.livro_id === '1f65ae09-61e4-49eb-ba3e-f7aa5a72eddc' &&
  //         rescue.livro_oferecido_id ===
  //           'dfe8be88-3b0d-496e-baab-395b63751f43' &&
  //         rescue.usuario_solicitante_id ===
  //           '05304a82-8a06-11ee-b9d1-0242ac120002';
  //     });
  //     const rescueData = {
  //       id: rescues.id,
  //       idRescueUser: '05304a82-8a06-11ee-b9d1-0242ac120002',
  //       idBook: '1f65ae09-61e4-49eb-ba3e-f7aa5a72eddc',
  //       idBookFromRescue: 'dfe8be88-3b0d-496e-baab-395b63751f43',
  //       status: 'Solicitação Aceita',
  //     };

  //     expect(await service.update(rescueData)).toBeDefined();
  //   });
  // });

  describe('GET methods', () => {
    it('should return a rescue', async () => {
      const rescueId = '13139b6c-6b53-41e6-9c6e-91d3bc3120be';
      const userId = '05304a82-8a06-11ee-b9d1-0242ac120002';
      const req = {
        user: {
          id: '05304a82-8a06-11ee-b9d1-0242ac120002',
        },
      };
      const rescueReturned = await controller.findRescueById(rescueId, req);
      expect(rescueReturned).toBeDefined();
    });

    it('should return a different rescue if the user is the owner of the book', async () => {
      const rescueId = '13139b6c-6b53-41e6-9c6e-91d3bc3120be';
      const userId = '1734ed59-eaa8-4c31-9b38-f546790668e8';
      const req = {
        user: {
          id: '1734ed59-eaa8-4c31-9b38-f546790668e8',
        },
      };
      const rescueReturned = await controller.findRescueById(rescueId, req);
      expect(rescueReturned).toBeDefined();
    });

    it('should return the rescues of a user', async () => {
      const userId = '1734ed59-eaa8-4c31-9b38-f546790668e8';

      expect(await controller.findRescuesFromAUser(userId)).toBeDefined();
    });
  });
});
