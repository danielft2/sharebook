import { Module } from '@nestjs/common';
import { SupabaseService } from '../services/supabase.service';

@Module({
  controllers: [],
  providers: [SupabaseService],
})
export class SupabaseModule {}
