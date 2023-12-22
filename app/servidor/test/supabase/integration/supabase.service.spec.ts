import { SupabaseService } from '../../../src/application/services/supabase.service';
import * as dotenv from 'dotenv';
import * as fs from 'fs-extra';
import { Test, TestingModule } from '@nestjs/testing';
import { InternalServerErrorException } from '@nestjs/common';

describe('SupabaseService', () => {
  let service: SupabaseService;

  beforeEach(async () => {
    dotenv.config();
    const module: TestingModule = await Test.createTestingModule({
      providers: [SupabaseService],
    }).compile();

    service = module.get<SupabaseService>(SupabaseService);
  });

  it('should be defined', () => {
    expect(service).toBeDefined();
  });

  it('should create a file', async () => {
    const fileBuffer = await fs.readFile('test/assets/testFile.txt');

    expect(await service.create('testFile.txt', 'TestFiles', fileBuffer))
      .resolves;
  });

  it('should find a file', async () => {
    const fileName = 'testFile.txt';

    expect(await service.findOne(fileName, 'TestFiles')).resolves;
  });

  it('should return a error when a file is not found', async () => {
    const fileName = 'fileExample.txt';

    await expect(service.findOne(fileName, 'TestFiles')).rejects.toThrow(
      InternalServerErrorException,
    );
  });

  it('should remove a file', async () => {
    const fileName = 'testFile.txt';

    expect(await service.remove(fileName, 'TestFiles')).resolves;
  });

  it('should return a string URL', async () => {
    const fileName = 'testImage.png';

    expect(await service.getFileURL(fileName, 'TestFiles')).resolves;
  });

  it('should return a error when URL is not found', async () => {
    const fileName = 'imageExample.png';

    await expect(service.getFileURL(fileName, 'TestFile')).rejects.toThrow(
      InternalServerErrorException,
    );
  });
});
