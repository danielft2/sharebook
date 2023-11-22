package com.example.sharebook.book_management_feature.data.remote.service

import com.example.sharebook.book_management_feature.data.remote.model.AddBookModel
import com.example.sharebook.book_management_feature.data.remote.response.AddBookResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface BookManagementService {
    @POST("/addbook")
    suspend fun addBook(@Body body: AddBookModel): AddBookResponse
}