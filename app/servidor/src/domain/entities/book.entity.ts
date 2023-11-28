export class Book {
  id?: string;
  isbn: string;
  nome: string;
  sinopse?: string;
  autor: string[];
  usuario_id: string;
  edicao: number;
  idioma: string;
  quer_receber: boolean;
  pode_buscar: boolean;
  capa: string;
  imagens: string[];
  estado_id: string;
  latitude: string;
  longitude: string;
}
