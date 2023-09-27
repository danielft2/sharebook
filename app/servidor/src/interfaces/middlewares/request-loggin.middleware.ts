import { Injectable, NestMiddleware } from '@nestjs/common';
import { Request, Response, NextFunction } from 'express';

@Injectable()
export class RequestLoggingMiddleware implements NestMiddleware {
  use(req: Request, res: Response, next: NextFunction) {
    const startTime = Date.now();

    // Log informações sobre a solicitação
    console.log(`[${new Date().toISOString()}] ${req.method} ${req.url}`);

    // Capture o corpo da solicitação (opcional)
    if (req.method === 'POST' || req.method === 'PUT') {
      console.log('Request Body:', req.body);
    }

    // Registre a resposta após o término da solicitação
    res.on('finish', () => {
      const duration = Date.now() - startTime;
      console.log(`[${new Date().toISOString()}] Status ${res.statusCode} - ${duration}ms`);
    });

    next();
  }
}
