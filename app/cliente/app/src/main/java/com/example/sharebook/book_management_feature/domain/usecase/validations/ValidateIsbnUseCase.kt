package com.example.sharebook.book_management_feature.domain.usecase.validations

import com.example.sharebook.R
import com.example.sharebook.core.utils.ValidationFieldResult

class ValidateIsbnUseCase {
    operator fun invoke(isbn: String) : ValidationFieldResult {
        if(isbn.isBlank()){
            return ValidationFieldResult(resId = R.string.field_required)
        }

        if(isbn.length != 10 || isbn.length != 13){
            return ValidationFieldResult(resId = R.string.field_isbn_invalid)
        }

        return ValidationFieldResult(isValid = true)
    }
}