package com.example.sharebook.auth_feature.domain.usecase.validations

import com.example.sharebook.R
import com.example.sharebook.core.utils.ValidationFieldResult

class ValidateCepUseCase {
    operator fun invoke(cep: String): ValidationFieldResult {
        if (cep.isBlank()) {
            return ValidationFieldResult(resId = R.string.field_required)
        }

        if (cep.length < 8) {
            return ValidationFieldResult(resId = R.string.field_cep_invalid)
        }

        return ValidationFieldResult(isValid = true)
    }
}