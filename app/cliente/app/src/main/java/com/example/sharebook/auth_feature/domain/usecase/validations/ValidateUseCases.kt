package com.example.sharebook.auth_feature.domain.usecase.validations

import com.example.sharebook.core.domain.usecase.ValidateRequiredUseCase

data class ValidateUseCases(
    val validateEmailUseCase: ValidateEmailUseCase,
    val validatePasswordUseCase: ValidatePasswordUseCase,
    val validateRequiredUseCase: ValidateRequiredUseCase,
    val validatePhoneUseCase: ValidatePhoneUseCase,
    val validateConfirmPasswordUseCase: ValidateConfirmPasswordUseCase,
    val validateCepUseCase: ValidateCepUseCase
)


