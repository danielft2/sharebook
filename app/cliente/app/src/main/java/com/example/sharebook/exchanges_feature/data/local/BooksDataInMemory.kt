package com.example.sharebook.exchanges_feature.data.local
import com.example.sharebook.exchanges_feature.domain.enum.BookProcessState
import com.example.sharebook.exchanges_feature.domain.enum.BookState
import com.example.sharebook.exchanges_feature.domain.model.EnteredProcessBookModel
import com.example.sharebook.exchanges_feature.domain.model.UserBookModel
import com.example.sharebook.home_feature.domain.model.BookModel


object BooksDataInMemory {
    val myBoooksList: List<UserBookModel> = listOf(
        UserBookModel(
            id = "1",
            gender = "Romance",
            name = "O Morro dos Ventos Uivantes",
            author = " Emily Brontë ",
            edtion = "5° Edição",
            coverUrl = "https://m.media-amazon.com/images/I/914Dtu+A1hL._SY466_.jpg",
            state = BookState.NEW,
            preference = "Preferência em receber"
        ),
        UserBookModel(
            id = "2",
            gender = "Aventura",
            name = "A revolução dos bichos",
            author = "George Orwell",
            edtion = "1° Edição",
            coverUrl = "https://m.media-amazon.com/images/I/61owA5ey3iL._SY445_SX342_.jpg",
            state = BookState.USED,
            preference = "Preferência em receber"
        ),
        UserBookModel(
            id = "3",
            gender = "Aventura",
            name = "Mentes Extraordinarias",
            author = "Alberto Dell´isola",
            edtion = "Edição Especial",
            coverUrl = "https://m.media-amazon.com/images/I/61SMLKtN6YL._SY466_.jpg",
            state = BookState.NEW,
            preference = "Preferencia em entregar"
        )
    )

    val enteredProcessList: List<EnteredProcessBookModel> = listOf(
        EnteredProcessBookModel(
            id = "1",
            gender = "Romance",
            name = "Pense de Novo",
            author = "Adam Grant",
            edtion = "Edição Especial",
            coverUrl = "https://m.media-amazon.com/images/I/41BhZzYgEPL._SY445_SX342_.jpg",
            state = BookProcessState.IN_PROGRESS,
            preference = "Preferencia em entregar",
            userName = "Leticia Aguiar",
            userLocation = "Ibicuitinga - CE"
        )
    )
}