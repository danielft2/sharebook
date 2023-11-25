import { Body, Controller, Get, Param, Post, Put } from '@nestjs/common';
import { RescueService } from 'src/application/services/rescue.service';
import { Rescue } from 'src/domain/entities/rescue.entity';

@Controller('rescue')
export class RescueController {
    constructor(private readonly rescueService: RescueService){}

    @Post()
    async create(@Body() rescue: Rescue) {
        return this.rescueService.create(rescue);
    }

    @Put()
    async update(@Body() rescue: Rescue) {
        return this.rescueService.update(rescue);
    }

    @Get('book/:id')
    async findRescuesFromABook(@Param('id') bookId: string) {
        return
    }

    @Get('user/:id')
    async findRescuesFromAUser(@Param('id') userId: string) {
        return this.rescueService.findRescuesFromAUser(userId);
    }
}
