import { createParamDecorator, ExecutionContext } from '@nestjs/common';
import { User } from '../../domain/entities/user.entity';
import { AuthRequest } from '../../domain/models/AuthRequest';

export const CurrentUser = createParamDecorator(
  (data: unknown, context: ExecutionContext): User => {
    const request = context.switchToHttp().getRequest<AuthRequest>();

    return request.user;
  },
);