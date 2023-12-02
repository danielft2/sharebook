import { Controller, Get, Param } from '@nestjs/common';
import { BookStateService } from '../../application/services/book-state.service';
import { ApiParam } from '@nestjs/swagger';

@Controller('book-state')
export class BookStateController {
  constructor(private bookStateService: BookStateService) {}

  @Get()
  async findMany() {
    return this.bookStateService.findMany();
  }

  @Get('/:id')
  @ApiParam({
    name: 'state id',
    description: 'return a state based on the state_id',
  })
  async findOne(@Param('id') id: string) {
    return this.bookStateService.findOne(id);
  }
}
