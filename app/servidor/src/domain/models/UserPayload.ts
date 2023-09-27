export interface UserPayload {
    sub: string;
    email: string;
    name: string;
    phone: string;
    cep: string;
    iat?: number;
    exp?: number;
  }