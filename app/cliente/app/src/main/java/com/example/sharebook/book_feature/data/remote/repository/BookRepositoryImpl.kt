package com.example.sharebook.book_feature.data.remote.repository

import com.example.sharebook.book_feature.data.remote.model.CreateBookModel
import com.example.sharebook.book_feature.data.remote.model.UpdateBookModel
import com.example.sharebook.book_feature.data.remote.response.BookDetailsResponse
import com.example.sharebook.book_feature.data.remote.response.BookStatesResponse
import com.example.sharebook.book_feature.data.remote.service.BookService
import com.example.sharebook.book_feature.domain.adapter.BookRepository
import javax.inject.Inject

class BookRepositoryImpl @Inject constructor(
    private val bookService: BookService
    ): BookRepository {

    override suspend fun detailsBook(bookId: String): BookDetailsResponse {
        return bookService.detailsBook(bookId = bookId)
    }

    override suspend fun createBook(book: CreateBookModel) {
        return bookService.createBook(
            isbn = book.isbn,
            nome = book.nome,
            autor = book.autor,
            edicao = book.edicao,
            genero = book.genero,
            sinopse = book.sinopse,
            idioma = book.idioma,
            pode_buscar = book.podeBuscar,
            quer_receber = book.querRecber,
            latitude = book.latitude,
            longitude = book.longitude,
            estado_id = book.estadoId,
            usuario_id = book.usuarioId,
            cape = book.cape,
            images = book.images
        )
    }

    override suspend fun updateBook(bookId: String, book: UpdateBookModel) {
        return bookService.updateBook(
            bookId = bookId,
            nome = book.nome,
            autor = book.autor,
            edicao = book.edicao,
            genero = book.genero,
            sinopse = book.sinopse,
            idioma = book.idioma,
            pode_buscar = book.podeBuscar,
            quer_receber = book.querRecber,
            estado_id = book.estadoId,
            cape = book.cape,
        )
    }

    override suspend fun listStates(): BookStatesResponse {
        return bookService.listStates()
    }
}