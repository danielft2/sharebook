import { ConflictException, Injectable } from '@nestjs/common';
import { Alert } from '../../domain/entities/alert.entity';
import { AlertRepository } from '../../infraestructure/repositories/alert.repository';

@Injectable()
export class AlertService {
  constructor(private readonly alertRepository: AlertRepository) {}

  async createAlert(
    userId: string,
    bookName: string,
    isFilteredByLocalization: boolean,
    isFilteredBySend: boolean,
  ) {
    const alert = await this.alertRepository.findAlertByUserIdAndBookName(
      userId,
      bookName,
    );
    if (alert) {
      throw new ConflictException();
    }

    const alertData: Alert = {
      bookName: bookName,
      usuarioId: userId,
      isFilteredByLocalization: isFilteredByLocalization
        ? isFilteredByLocalization
        : false,
      isFilteredBySend: isFilteredBySend ? isFilteredBySend : false,
    };

    return await this.alertRepository.create(alertData);
  }
}
