package com.example.sharebook.book_feature.domain.adapter

interface RequestBookRepository {
    suspend fun acceptRequest()
    suspend fun refuseRequest()
    suspend fun sendRequest()
}