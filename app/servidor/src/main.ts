import { NestFactory } from '@nestjs/core';
import { ValidationPipe } from '@nestjs/common';
import { AppModule } from './application/modules/app.module';
import { DocumentBuilder, SwaggerModule } from '@nestjs/swagger';
import { config } from 'dotenv';
config();

async function bootstrap() {
  const app = await NestFactory.create(AppModule);
  // // Swagger Documentation
  const config = new DocumentBuilder()
    .setTitle('Sharebook')
    .setVersion('1.0')
    .build();
  const document = SwaggerModule.createDocument(app, config);
  SwaggerModule.setup('sharebook', app, document);
  // Pipes
  app.useGlobalPipes(
    new ValidationPipe({
      transform: true,
      //whitelist: true,
      forbidNonWhitelisted: true,
    }),
  );

  // // CORS
  app.enableCors();

  app.enableShutdownHooks();
  await app.listen(3333, '0.0.0.0');
}
bootstrap();
