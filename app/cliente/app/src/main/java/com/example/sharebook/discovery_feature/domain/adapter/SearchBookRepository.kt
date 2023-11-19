package com.example.sharebook.discovery_feature.domain.adapter

interface SearchBookRepository {
    suspend fun searchBook()
}