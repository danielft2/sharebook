import { Module } from "@nestjs/common";
import { PrismaModule } from "src/infraestructure/database/prisma/prisma.module";
import { AlertController } from "src/interfaces/controllers/alert.controller";
import { AlertService } from "../services/alert.service";
import { AlertRepository } from "src/infraestructure/repositories/alert.repository";

@Module({
    imports: [PrismaModule],
    controllers: [AlertController],
    providers: [AlertService, AlertRepository],
    exports:[AlertService]
  })
  export class AlertModule {}