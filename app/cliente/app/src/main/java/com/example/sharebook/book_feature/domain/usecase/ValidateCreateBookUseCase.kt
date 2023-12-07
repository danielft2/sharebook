package com.example.sharebook.book_feature.domain.usecase

import com.example.sharebook.book_feature.data.remote.model.FormBookModel
import com.example.sharebook.core.domain.usecase.ValidateRequiredUseCase
import javax.inject.Inject


class ValidateCreateBookUseCase @Inject constructor(
    private val validateRequiredUseCase: ValidateRequiredUseCase
) {
    operator fun invoke(form: FormBookModel) {

    }
}