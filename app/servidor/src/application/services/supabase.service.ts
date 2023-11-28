import { Injectable, InternalServerErrorException } from '@nestjs/common';
import { SupabaseClient, createClient } from '@supabase/supabase-js';

@Injectable()
export class SupabaseService {
  private supabase: SupabaseClient;

  constructor() {
    this.supabase = createClient(
      `${process.env.SUPABASE_URL}`,
      `${process.env.SUPABASE_KEY}`,
    );
  }

  async create(fileName: string, bucketName: string, file: Buffer) {
    const { error } = await this.supabase.storage
      .from(bucketName)
      .upload(fileName, file);

    if (error)
      throw new InternalServerErrorException(
        'Error in the creation of the file',
      );
  }

  async findOne(fileName: string, bucketName: string) {
    console.log(fileName)
    const { data, error } = await this.supabase.storage
      .from(bucketName)
      .download(fileName);

    if (error) throw new InternalServerErrorException('Error in file access');

    return data;
  }

  async remove(fileName: string, bucketName: string) {
    const { error } = await this.supabase.storage
      .from(bucketName)
      .remove([fileName]);

    if (error) throw new InternalServerErrorException('Error removing file');
  }

  async getFileURL(fileName: string, bucketName: string) {
    const { data, error } = await this.supabase.storage
      .from(bucketName)
      .createSignedUrl(fileName, 120);

    if (error)  throw new InternalServerErrorException('Error in file access');

    return data.signedUrl;
  }
}
