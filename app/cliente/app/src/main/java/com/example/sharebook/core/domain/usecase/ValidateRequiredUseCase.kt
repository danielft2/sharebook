package com.example.sharebook.core.domain.usecase

import com.example.sharebook.R
import com.example.sharebook.core.utils.ValidationFieldResult

class ValidateRequiredUseCase {
    operator fun invoke(field: String): ValidationFieldResult {
        if (field.isBlank()) {
            return ValidationFieldResult(resId = R.string.field_required)
        }

        return ValidationFieldResult(isValid = true)
    }
}