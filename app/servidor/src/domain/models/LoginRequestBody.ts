import { IsEmail, IsNumber, IsString } from 'class-validator';

export class LoginRequestBody {
  @IsEmail()
  email: number;

  @IsString()
  password: string;
}