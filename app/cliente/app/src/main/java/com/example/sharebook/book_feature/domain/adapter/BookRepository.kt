package com.example.sharebook.book_feature.domain.adapter

import com.example.sharebook.book_feature.data.remote.model.CreateBookModel
import com.example.sharebook.book_feature.data.remote.model.UpdateBookModel
import com.example.sharebook.book_feature.data.remote.response.BookDetailsResponse
import com.example.sharebook.book_feature.data.remote.response.BookStatesResponse

interface BookRepository {
    suspend fun detailsBook(bookId: String): BookDetailsResponse
    suspend fun createBook(book: CreateBookModel)
    suspend fun updateBook(bookId: String, book: UpdateBookModel)
    suspend fun listStates(): BookStatesResponse
}