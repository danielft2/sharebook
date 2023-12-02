import { Transform } from 'class-transformer';
import { IsInt } from 'class-validator';
import { optionalBooleanMapper } from 'src/helpers';

export class CreatedBookDto {
  id?: string;
  isbn: string;
  nome: string;
  sinopse?: string;
  autor: string[];
  usuario_id: string;
  @IsInt()
  @Transform((v) => parseInt(v.value))
  edicao: number;
  idioma: string;
  @Transform(({ value }) => !!optionalBooleanMapper.get(value))
  quer_receber: boolean;
  @Transform(({ value }) => !!optionalBooleanMapper.get(value))
  pode_buscar: boolean;
  capa: Express.Multer.File;
  imagens: Express.Multer.File[];
  estado_id: string;
  latitude: string;
  longitude: string;
}
