package com.example.sharebook.auth_feature.domain.usecase.validations

data class ValidateUseCases(
    val validateEmailUseCase: ValidateEmailUseCase,
    val validatePasswordUseCase: ValidatePasswordUseCase,
    val validateRequiredUseCase: ValidateRequiredUseCase,
    val validatePhoneUseCase: ValidatePhoneUseCase,
    val validateConfirmPasswordUseCase: ValidateConfirmPasswordUseCase,
    val validateCepUseCase: ValidateCepUseCase
)


