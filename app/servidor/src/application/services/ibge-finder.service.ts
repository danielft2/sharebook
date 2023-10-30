import { Injectable } from '@nestjs/common';
import axios from 'axios';

@Injectable()
export class IbgeFinderService {
  async getIBGE(cep: string) {
    const response = await axios.get(`https://viacep.com.br/ws/${cep}/json/`);
    return response.data.ibge;
  }
}
