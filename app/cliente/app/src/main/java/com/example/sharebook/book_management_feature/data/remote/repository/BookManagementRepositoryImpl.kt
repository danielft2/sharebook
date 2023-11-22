package com.example.sharebook.book_management_feature.data.remote.repository

import com.example.sharebook.book_management_feature.data.remote.model.AddBookModel
import com.example.sharebook.book_management_feature.data.remote.response.AddBookResponse
import com.example.sharebook.book_management_feature.data.remote.service.BookManagementService
import com.example.sharebook.book_management_feature.domain.adapter.BookManagementRepository
import javax.inject.Inject

class BookManagementRepositoryImpl @Inject constructor(private val bookManagementService: BookManagementService): BookManagementRepository{
    override suspend fun addBook(body: AddBookModel): AddBookResponse {
        return bookManagementService.addBook(body)
    }
}