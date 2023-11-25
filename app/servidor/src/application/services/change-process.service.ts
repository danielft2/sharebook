import { ConflictException, Injectable, NotFoundException } from '@nestjs/common';
import { SupabaseService } from './supabase.service';
import { ChangeProcess } from 'src/domain/entities/changeProcess.entity';
import { ChangeProcessRepository } from 'src/infraestructure/repositories/changeProcess.repository';

@Injectable()
export class ChangeProcessService {
    constructor(private readonly changeProcessRepository: ChangeProcessRepository, private readonly supabaseService: SupabaseService){}

    async create(changeProcess: ChangeProcess) {
        const changeProcessFinded = await this.changeProcessRepository.findByBookAndUserId(changeProcess.idBook, changeProcess.idRescueUser);
        if (changeProcessFinded.id) throw new ConflictException();
        return this.changeProcessRepository.create(changeProcess);
    }

    async update(changeProcess: ChangeProcess) {
        const changeProcessFinded = await this.changeProcessRepository.findByBookAndUserId(changeProcess.idBook, changeProcess.idRescueUser);
        if (!changeProcessFinded.id) throw new NotFoundException();

        return this.changeProcessRepository.update(changeProcess);
    }
}
