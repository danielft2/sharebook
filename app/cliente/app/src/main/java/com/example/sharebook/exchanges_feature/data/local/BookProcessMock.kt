package com.example.sharebook.exchanges_feature.data.local

import com.example.sharebook.exchanges_feature.domain.enum.BookState

data class BookProcessMock(
    val id: String = "1",
    val gender: String = "Romance",
    val name: String = "Pense de Novo",
    val author: String = "Adam Grant",
    val edtion: String = "Edição Especial",
    val coverUrl: String = "https://m.media-amazon.com/images/I/41BhZzYgEPL._SY445_SX342_.jpg",
    val state: BookState = BookState.NEW,
    val preference: String = "Preferencia em entregar",
    val description: String = "Pense de novo demonstra com clareza e bom humor que é possível manter " +
            "a mente aberta sem perder o poder de convencimento nem a autoconfiança. Se conhecimento é poder, " +
            "saber o que não sabemos é sabedoria.",
    val userName: String = "Leticia Aguiar",
    val userLocation: String = "Ibicuitinga - CE",
    val imagesGalery: Images = Images()
)

data class Images(
    val image1: String = "https://sextante.com.br/wp-content/uploads/2021/07/pensedenovo.png.webp",
    val image2: String = "https://m.media-amazon.com/images/I/71Sr8FYmjcL._UX250_.jpg"
)