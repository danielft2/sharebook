import { Injectable } from '@nestjs/common';
import * as bcrypt from 'bcrypt';
import { UserService } from './user.service';
import { User } from '../../domain/entities/user.entity';
import { JwtService } from '@nestjs/jwt';
import { UserDataReturn } from '../../domain/models/UserDataReturn';
import { UserPayload } from '../../../src/domain/models/UserPayload';

@Injectable()
export class AuthService {
    constructor(
        private readonly userService: UserService, 
        private readonly jwtService: JwtService
    ){}
    
    async validateUser(email: string, password: string) {
        const user = await this.userService.findOneByEmail(email);
       
        if (user) {
            const isPasswordValid = await bcrypt.compare(password, user.senha);
            
            if (isPasswordValid) return {
                ...user, 
                senha: undefined
            };
        }
        
        throw new Error("The provided data is incorrect");
    }
    
    async login(user: User) {
        let returnData: UserDataReturn;

        const payload: UserPayload =  {
            sub: user.id,
            email: user.email,
            name: user.nome,
            cep: user.cep,
            phone: user.telefone
        }

        const jwtToken = this.jwtService.sign(payload);
        returnData = {
            cep: user.cep,
            email: user.email,
            phone: user.telefone,
            name: user.nome,
            id: user.id
        }
    
        return {
            returnData,
            access_token: jwtToken
        }
    }
}
