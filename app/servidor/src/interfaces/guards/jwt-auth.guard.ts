import {
    ExecutionContext,
    Injectable,
    UnauthorizedException,
} from '@nestjs/common';
import { Reflector } from '@nestjs/core';
// Password
import { AuthGuard } from '@nestjs/passport';
// Decorators
import { IS_PUBLIC_KEY } from '../decorators/is-public.decorator';

@Injectable()
export class JwtAuthGuard extends AuthGuard('jwt') {
  constructor(private reflector: Reflector) {
    super();
  }

  handleRequest(err: Error, user: any, info: any, context: any, status?: any) {
    const isPublic = this.reflector.getAllAndOverride<boolean>(IS_PUBLIC_KEY, [
      context.getHandler(),
      context.getClass(),
    ]);

    if (isPublic) return true;

    if (info?.name === 'TokenExpiredError') {
      throw new UnauthorizedException('Token expired');
    }

    if (err || !user) {
      throw err || new UnauthorizedException();
    }

    return user;
  }
}