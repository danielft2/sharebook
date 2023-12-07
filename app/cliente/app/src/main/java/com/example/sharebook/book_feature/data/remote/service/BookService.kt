package com.example.sharebook.book_feature.data.remote.service
import com.example.sharebook.book_feature.data.remote.response.BookDetailsResponse
import com.example.sharebook.book_feature.data.remote.response.BookStatesResponse
import okhttp3.MultipartBody
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path

interface BookService {
    @GET("/book/{book_id}")
    suspend fun detailsBook(@Path("book_id") bookId: String): BookDetailsResponse

    @Multipart
    @POST("/book")
    suspend fun createBook(
        @Part isbn: MultipartBody.Part,
        @Part nome: MultipartBody.Part,
        @Part sinopse: MultipartBody.Part,
        @Part autor: MultipartBody.Part,
        @Part usuario_id: MultipartBody.Part,
        @Part edicao: MultipartBody.Part,
        @Part idioma: MultipartBody.Part,
        @Part genero: MultipartBody.Part,
        @Part pode_buscar: MultipartBody.Part,
        @Part quer_receber: MultipartBody.Part,
        @Part estado_id: MultipartBody.Part,
        @Part latitude: MultipartBody.Part,
        @Part longitude: MultipartBody.Part,
        @Part images: List<MultipartBody.Part>,
        @Part cape: MultipartBody.Part,
    )

    @Multipart
    @PUT("/book/{book_id}")
    suspend fun updateBook(
        @Path("book_id") bookId: String,
        @Part nome: MultipartBody.Part,
        @Part sinopse: MultipartBody.Part,
        @Part autor: MultipartBody.Part,
        @Part edicao: MultipartBody.Part,
        @Part idioma: MultipartBody.Part,
        @Part genero: MultipartBody.Part,
        @Part pode_buscar: MultipartBody.Part,
        @Part quer_receber: MultipartBody.Part,
        @Part estado_id: MultipartBody.Part,
        @Part cape: MultipartBody.Part,
    )

    @GET("/book-state")
    suspend fun listStates(): BookStatesResponse
}