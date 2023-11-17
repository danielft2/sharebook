package com.example.sharebook.book_feature.data.remote.service
import com.example.sharebook.book_feature.data.remote.response.RequestBookResponse
import retrofit2.http.POST

interface RequestBookService {
    @POST("/book")
    suspend fun acceptRequest(): RequestBookResponse
}