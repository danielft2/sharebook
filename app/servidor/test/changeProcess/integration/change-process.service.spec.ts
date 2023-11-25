import { Test, TestingModule } from '@nestjs/testing';
import { ChangeProcessService } from '../../../src/application/services/change-process.service';

describe('ChangeProcessService', () => {
  let service: ChangeProcessService;

  beforeEach(async () => {
    const module: TestingModule = await Test.createTestingModule({
      providers: [ChangeProcessService],
    }).compile();

    service = module.get<ChangeProcessService>(ChangeProcessService);
  });

  it('should be defined', () => {
    expect(service).toBeDefined();
  });
});
