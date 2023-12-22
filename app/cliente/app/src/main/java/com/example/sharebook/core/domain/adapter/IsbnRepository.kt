package com.example.sharebook.core.domain.adapter

import com.example.sharebook.core.data.remote.response.IsbnResponse

interface IsbnRepository {
    suspend fun consultIsbn(isbn: String): IsbnResponse
}