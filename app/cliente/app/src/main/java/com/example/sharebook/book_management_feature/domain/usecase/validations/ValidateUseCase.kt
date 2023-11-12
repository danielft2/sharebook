package com.example.sharebook.book_management_feature.domain.usecase.validations

import com.example.sharebook.core.domain.usecase.ValidateRequiredUseCase

data class ValidateUseCase (
    val validateIsbnUseCase: ValidateIsbnUseCase,
    val validateRequiredUseCase: ValidateRequiredUseCase
)