import { Body, Controller, Post } from "@nestjs/common";
import { AlertService } from "src/application/services/alert.service";
import { Alert } from "src/domain/entities/alert.entity";

@Controller('alert')
export class AlertController {
    constructor (private readonly alertService: AlertService){}

    @Post()
    async create(@Body() alert: Alert) {
        return this.alertService.createAlert(alert.usuarioId, alert.bookName, alert.isFilteredByLocalization, alert.isFilteredBySend);
    }
}