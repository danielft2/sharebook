import { Body, Controller, Get, Param, Post, Put, Req } from '@nestjs/common';
import { ApiBody } from '@nestjs/swagger';
import { RescueService } from 'src/application/services/rescue.service';
import { Rescue } from 'src/domain/entities/rescue.entity';

@Controller('rescue')
export class RescueController {
  constructor(private readonly rescueService: RescueService) {}

  @Post()
  @ApiBody({
    schema: {
      example: {
        data: {
          usuario_solicitante_id: '',
          livro_id: '',
          status: '',
          livro_oferecido_id: '',
        },
      },
    },
  })
  async create(@Body() rescue: Rescue) {
    return this.rescueService.create(rescue);
  }

  @Put()
  @ApiBody({
    schema: {
      example: {
        data: {
          usuario_solicitante_id: '',
          livro_id: '',
          status: '',
          livro_oferecido_id: '',
        },
      },
    },
  })
  async update(@Body() rescue: Rescue) {
    return this.rescueService.update(rescue);
  }

  @Get('book/:id')
  async findRescuesFromABook(@Param('id') bookId: string) {
    return;
  }

  @Get('user/:id')
  async findRescuesFromAUser(@Param('id') userId: string) {
    return this.rescueService.findRescuesFromAUser(userId);
  }

  @Get(':id')
  async findRescueById(@Param('id') id: string, @Req() req) {
    return this.rescueService.findRescueById(id, req.user);
  }
}
