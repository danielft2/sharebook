package com.example.sharebook.book_feature.domain.adapter

import com.example.sharebook.book_feature.data.remote.model.FormBookModelMultipartForm
import com.example.sharebook.book_feature.data.remote.response.BookDetailsResponse
import com.example.sharebook.book_feature.data.remote.response.BookStatesResponse

interface BookRepository {
    suspend fun detailsBook(bookId: String): BookDetailsResponse
    suspend fun createBook(book: FormBookModelMultipartForm)
    suspend fun listStates(): BookStatesResponse
}