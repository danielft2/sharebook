package com.example.sharebook.home_feature.data.remote.inmemory
import com.example.sharebook.home_feature.domain.BookModel

object SectionsCarouselInMemory {
    val availableForExchange: List<BookModel> = listOf(
        BookModel(
            id = "1",
            name = "O Filho do Açougueiro",
            author = "Christian David",
            coverUrl = "https://m.media-amazon.com/images/I/512I4ZOulEL._SY445_SX342_.jpg"
        ),
        BookModel(
            id = "2",
            name = "A Cinco Passos de Você",
            author = "Antoine de Saint-Ex",
            coverUrl = "https://m.media-amazon.com/images/I/81Q11TuUR3L._SY466_.jpg"
        ),
        BookModel(
            id = "3",
            name = "A revolução dos bichos",
            author = "George Orwell",
            coverUrl = "https://m.media-amazon.com/images/I/61owA5ey3iL._SY445_SX342_.jpg"
        ),
        BookModel(
            id = "4",
            name = "Mentes Extraordinarias",
            author = "Alberto Dell´isola",
            coverUrl = "https://m.media-amazon.com/images/I/61SMLKtN6YL._SY466_.jpg"
        )
    )

    val nearToYou: List<BookModel> = listOf(
        BookModel(
            id = "1",
            name = "Pense de Novo",
            author = "Adam Grant",
            coverUrl = "https://m.media-amazon.com/images/I/41BhZzYgEPL._SY445_SX342_.jpg"
        ),
        BookModel(
            id = "2",
            name = "Mentes Extraordinarias",
            author = "Alberto Dell´isola",
            coverUrl = "https://m.media-amazon.com/images/I/41+B-dlRxyL._SY445_SX342_.jpg"
        ),
        BookModel(
            id = "3",
            name = "Você é insubstituível",
            author = "Jenny Han",
            coverUrl = "https://m.media-amazon.com/images/I/41xS9cI3wiL._SY445_SX342_.jpg"
        ),
        BookModel(
            id = "1",
            name = "O Filho do Açougueiro",
            author = "Christian David",
            coverUrl = "https://m.media-amazon.com/images/I/512I4ZOulEL._SY445_SX342_.jpg"
        ),
    )
}
