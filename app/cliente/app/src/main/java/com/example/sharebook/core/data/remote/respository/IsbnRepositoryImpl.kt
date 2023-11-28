package com.example.sharebook.core.data.remote.respository

import com.example.sharebook.core.data.remote.response.IsbnResponse
import com.example.sharebook.core.data.remote.service.IsbnService
import com.example.sharebook.core.domain.adapter.IsbnRepository
import javax.inject.Inject

class IsbnRepositoryImpl @Inject constructor(private val isbnService: IsbnService) : IsbnRepository {
    override suspend fun consultIsbn(isbn: String): IsbnResponse {
        return isbnService.consultIsbn(isbn = isbn)
    }
}