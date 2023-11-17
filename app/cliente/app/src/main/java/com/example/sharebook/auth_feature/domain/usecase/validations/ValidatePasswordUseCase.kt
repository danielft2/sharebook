package com.example.sharebook.auth_feature.domain.usecase.validations
import com.example.sharebook.R
import com.example.sharebook.core.utils.ValidationFieldResult

class ValidatePasswordUseCase {
    operator fun invoke(password: String): ValidationFieldResult {
        if (password.isBlank()) {
            return ValidationFieldResult(resId = R.string.field_required)
        }

        if (password.length < 6) {
            return ValidationFieldResult(resId = R.string.field_password_min)
        }

        return ValidationFieldResult(isValid = true)
    }
}
