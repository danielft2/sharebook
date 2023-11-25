package com.example.sharebook.exchanges_feature.data.local
import com.example.sharebook.core.domain.enum.BookPreferenceTag
import com.example.sharebook.core.domain.enum.BookStateTag
import com.example.sharebook.core.domain.model.BookUserLoggedModel

fun booksInMemory(): List<BookUserLoggedModel> {
    return listOf(
        BookUserLoggedModel(
            id = "1",
            gender = "Romance",
            name = "O Morro dos Ventos Uivantes",
            author = "Emily Brontë ",
            edition = "5° Edição",
            coverUrl = "https://m.media-amazon.com/images/I/914Dtu+A1hL._SY466_.jpg",
            state = BookStateTag.NEW,
            preference = BookPreferenceTag.RECEIVE
        ),
        BookUserLoggedModel(
            id = "2",
            gender = "Aventura",
            name = "A revolução dos bichos",
            author = "George Orwell",
            edition = "1° Edição",
            coverUrl = "https://m.media-amazon.com/images/I/61owA5ey3iL._SY445_SX342_.jpg",
            state = BookStateTag.USED,
            preference = BookPreferenceTag.SEND
        ),
        BookUserLoggedModel(
            id = "3",
            gender = "Aventura",
            name = "Mentes Extraordinarias",
            author = "Alberto Dell´isola",
            edition = "Edição Especial",
            coverUrl = "https://m.media-amazon.com/images/I/61SMLKtN6YL._SY466_.jpg",
            state = BookStateTag.NEW,
            preference = BookPreferenceTag.RECEIVE
        ),
        BookUserLoggedModel(
            id = "3",
            gender = "Aventura",
            name = "Mentes Extraordinarias",
            author = "Alberto Dell´isola",
            edition = "Edição Especial",
            coverUrl = "https://m.media-amazon.com/images/I/61SMLKtN6YL._SY466_.jpg",
            state = BookStateTag.NEW,
            preference = BookPreferenceTag.RECEIVE
        ),
        BookUserLoggedModel(
            id = "3",
            gender = "Aventura",
            name = "Mentes Extraordinarias",
            author = "Alberto Dell´isola",
            edition = "Edição Especial",
            coverUrl = "https://m.media-amazon.com/images/I/61SMLKtN6YL._SY466_.jpg",
            state = BookStateTag.NEW,
            preference = BookPreferenceTag.RECEIVE
        )
    )
}
