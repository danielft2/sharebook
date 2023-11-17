package com.example.sharebook.book_feature.domain.usecase

import com.example.sharebook.book_feature.data.remote.repository.RequestBookRepository
import javax.inject.Inject

class SendRequestUseCase @Inject constructor(private val repository: RequestBookRepository) {
    operator fun invoke() {
        println("Realizando a chamada à API")
    }
}