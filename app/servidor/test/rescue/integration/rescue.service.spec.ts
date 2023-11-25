import { Test, TestingModule } from '@nestjs/testing';
import { RescueService } from '../../../src/application/services/rescue.service';

describe('RescueService', () => {
  let service: RescueService;

  beforeEach(async () => {
    const module: TestingModule = await Test.createTestingModule({
      providers: [RescueService],
    }).compile();

    service = module.get<RescueService>(RescueService);
  });

  it('should be defined', () => {
    expect(service).toBeDefined();
  });
});
