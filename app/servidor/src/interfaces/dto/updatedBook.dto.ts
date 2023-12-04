import { Transform } from 'class-transformer';
import { IsInt } from 'class-validator';
import { optionalBooleanMapper } from 'src/helpers';

export class UpdatedBookDto {
  isbn: string;
  nome: string;
  sinopse?: string;
  autor: string;
  @IsInt()
  @Transform((v) => parseInt(v.value))
  edicao: number;
  idioma: string;
  @Transform(({ value }) => !!optionalBooleanMapper.get(value))
  quer_receber: boolean;
  @Transform(({ value }) => !!optionalBooleanMapper.get(value))
  pode_buscar: boolean;
  estado_id: string;
  genero: string;
}
