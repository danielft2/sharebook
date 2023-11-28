import { Test, TestingModule } from '@nestjs/testing';
import { RescueService } from '../../../src/application/services/rescue.service';
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

describe('RescueService', () => {
  let service: RescueService;
  let prismaService: PrismaService;
    
    
  beforeEach(async () => {
    const prismaModule: TestingModule = await Test.createTestingModule({
      providers: [PrismaService]
    }).compile();
    prismaService = prismaModule.get<PrismaService>(PrismaService);

    const rescueRepository = new RescueRepository(prismaService);
    const bookRepository = new BookRepository(prismaService);
    const bookStateRepository = new BookStateRepository(prismaService);
    const userRepository = new UserRepository(prismaService);
    const bookGendersRepository =  new BookGendersRepository(prismaService);
    const genderRepository = new GenderRepository(prismaService);
    const module: TestingModule = await Test.createTestingModule({
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
          useValue: bookRepository
        },
        {
          provide: BookStateRepository,
          useValue: bookStateRepository
        },
        {
          provide: UserRepository,
          useValue: userRepository,
        },
        {
          provide: BookGendersRepository,
          useValue: bookGendersRepository
        },
        {
          provide: GenderRepository,
          useValue: genderRepository
        }
      ],
    }).compile();

    service = module.get<RescueService>(RescueService);
  });

  it('should be defined', () => {
    expect(service).toBeDefined();
  });

  it('should create a rescue', async () => {
    const rescueData = {
      idRescueUser:"05304a82-8a06-11ee-b9d1-0242ac120002",
      idBook: "1f65ae09-61e4-49eb-ba3e-f7aa5a72eddc",
      idBookFromRescue: "dfe8be88-3b0d-496e-baab-395b63751f43",
      status: "Aguardando Confirmação"
    }

    const rescueCreated = await service.create(rescueData);
    expect(rescueCreated).toBeDefined();
  })

  it('should return a rescue', async () => {
    const rescueId = "b8590ed3-5e5d-4a75-9c9a-a868ae945334";
    const userId = "05304a82-8a06-11ee-b9d1-0242ac120002"

    const rescueReturned = await service.findRescueById(rescueId, userId);
    expect(rescueReturned).toBeDefined();
  })
});
