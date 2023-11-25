import { Test, TestingModule } from '@nestjs/testing';
import { IbgeFinderService } from '../../../src/application/services/ibge-finder.service';

describe('IbgeFinderService', () => {
  let service: IbgeFinderService;

  beforeEach(async () => {
    const module: TestingModule = await Test.createTestingModule({
      providers: [IbgeFinderService],
    }).compile();

    service = module.get<IbgeFinderService>(IbgeFinderService);
  });

  it('should be defined', async () => {
    expect(service).toBeDefined();
  });

  describe('GET method', () => {
    it('should return the ibge of a specific CEP', async () => {
      const cep = '60764020';
      const ibge = await service.getIBGE(cep);
      expect(ibge).toBe('2304400');
    });
  });
});
