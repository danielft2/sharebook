package com.example.sharebook.home_feature.domain.adpater

import com.example.sharebook.home_feature.data.remote.response.ListBooksResponse

interface HomeRepository {
    suspend fun listBooks(usuario_id: String) : ListBooksResponse
}