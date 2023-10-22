package com.example.sharebook.auth_feature.domain.usecase.validations

import android.util.Patterns
import com.example.sharebook.R
import com.example.sharebook.core.utils.ValidationFieldResult

class ValidatePhoneUseCase {
    operator fun invoke(phone: String): ValidationFieldResult {
        if (phone.isBlank()) {
            return ValidationFieldResult(resId = R.string.field_required)
        }

        if (!Patterns.PHONE.matcher(phone).matches()) {
            return ValidationFieldResult(resId = R.string.field_phone_invalid)
        }

        return ValidationFieldResult(isValid = true)
    }
}