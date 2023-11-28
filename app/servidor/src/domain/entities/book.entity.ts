export class Book {
  id?: string;
  isbn: string;
  name: string;
  synopsis?: string;
  writer: string[];
  owner_id: string;
  edition: number;
  language: string;
  can_receive: boolean;
  can_get: boolean;
  cape: string;
  images: string[];
  state_id: string;
  latitude: string;
  longitude: string;
}
