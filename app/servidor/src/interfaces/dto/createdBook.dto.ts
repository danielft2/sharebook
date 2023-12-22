import { Transform } from 'class-transformer';
import { IsInt } from 'class-validator';
import { optionalBooleanMapper } from 'src/helpers';

export class CreatedBookDto {
  isbn: string;
  nome: string;
  sinopse?: string;
  autor: string;
  usuario_id: string;
  @IsInt()
  @Transform((v) => parseInt(v.value))
  edicao: number;
  idioma: string;
  @Transform(({ value }) => !!optionalBooleanMapper.get(value))
  quer_receber: boolean;
  @Transform(({ value }) => !!optionalBooleanMapper.get(value))
  pode_buscar: boolean;
  estado_id: string;
  latitude: string;
  longitude: string;
  genero: string;
}
