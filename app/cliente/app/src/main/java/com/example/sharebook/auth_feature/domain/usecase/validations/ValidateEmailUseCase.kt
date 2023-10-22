package com.example.sharebook.auth_feature.domain.usecase.validations

import android.util.Patterns
import com.example.sharebook.R
import com.example.sharebook.core.utils.ValidationFieldResult

class ValidateEmailUseCase {
    operator fun invoke(email: String): ValidationFieldResult {
        if (email.isBlank()) {
            return ValidationFieldResult(resId = R.string.field_required)
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return ValidationFieldResult(resId = R.string.field_email_invalid)
        }

        return ValidationFieldResult(isValid = true)
    }
}