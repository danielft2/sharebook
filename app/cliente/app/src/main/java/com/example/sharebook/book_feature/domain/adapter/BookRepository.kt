package com.example.sharebook.book_feature.domain.adapter

import com.example.sharebook.book_feature.data.remote.response.BookDetailsResponse

interface BookRepository {
    suspend fun detailsBook(bookId: String): BookDetailsResponse
}