import { Body, Controller, Post, Put } from '@nestjs/common';
import { ChangeProcessService } from 'src/application/services/change-process.service';
import { ChangeProcess } from 'src/domain/entities/changeProcess.entity';

@Controller('change-process')
export class ChangeProcessController {
    constructor(private readonly changeProcessService: ChangeProcessService){}
    
    @Post()
    async create(@Body() changeProcess: ChangeProcess) {
        return this.changeProcessService.create(changeProcess);
    }

    @Put()
    async update(@Body() changeProcess: ChangeProcess) {
        return this.changeProcessService.update(changeProcess);
    }
}
