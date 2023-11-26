package com.example.sharebook.book_feature.data.remote.service
import com.example.sharebook.book_feature.data.remote.response.BookDetailsResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface BookService {
    @GET("/book/{book_id}")
    suspend fun detailsBook(@Path("book_id") bookId: String): BookDetailsResponse
}