package com.example.sharebook.book_management_feature.domain.adapter

import com.example.sharebook.book_management_feature.data.remote.model.AddBookModel
import com.example.sharebook.book_management_feature.data.remote.response.AddBookResponse

interface BookManagementRepository {
    suspend fun addBook(body: AddBookModel) : AddBookResponse
}