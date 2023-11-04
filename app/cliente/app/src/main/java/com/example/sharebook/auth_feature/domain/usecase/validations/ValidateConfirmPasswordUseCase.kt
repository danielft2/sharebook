package com.example.sharebook.auth_feature.domain.usecase.validations

import com.example.sharebook.R
import com.example.sharebook.core.utils.ValidationFieldResult

class ValidateConfirmPasswordUseCase {
    operator fun invoke(password: String, confirmPassword: String): ValidationFieldResult {
        if (password.isBlank()) {
            return ValidationFieldResult(resId = R.string.field_required)
        }

        if (password != confirmPassword) {
            return ValidationFieldResult(resId = R.string.field_different_passwords)
        }

        return ValidationFieldResult(isValid = true)
    }
}