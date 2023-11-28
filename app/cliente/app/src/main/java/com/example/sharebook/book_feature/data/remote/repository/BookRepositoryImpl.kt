package com.example.sharebook.book_feature.data.remote.repository

import com.example.sharebook.book_feature.data.remote.response.BookDetailsResponse
import com.example.sharebook.book_feature.data.remote.service.BookService
import com.example.sharebook.book_feature.domain.adapter.BookRepository
import javax.inject.Inject

class BookRepositoryImpl @Inject constructor(
    private val bookService: BookService
    ): BookRepository {

    override suspend fun detailsBook(bookId: String): BookDetailsResponse {
        return bookService.detailsBook(bookId = bookId)
    }
}