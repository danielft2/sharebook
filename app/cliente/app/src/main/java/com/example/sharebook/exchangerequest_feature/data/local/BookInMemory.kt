package com.example.sharebook.exchangerequest_feature.data.local

import com.example.sharebook.core.domain.enum.BookPreferenceTag
import com.example.sharebook.core.domain.enum.BookStateTag
import com.example.sharebook.core.domain.model.BookExternalModel

fun bookInMemory(): BookExternalModel {
    return BookExternalModel(
        id = "52",
        name = "O morro dos ventos uivantes",
        author = "Emily Bronte",
        edition = "5ª Edicao",
        gender = "Romance",
        coverUrl = "https://m.media-amazon.com/images/I/51amKTwzkPL._SY445_SX342_.jpg",
        state = BookStateTag.NEW,
        preference = BookPreferenceTag.RECEIVE,
        userName = "Leticia Aguiar",
        userLocation = "Ibicuitinga - CE",
        userProfileUrl = "https://images.pexels.com/photos/1239291/pexels-photo-1239291.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"
    )
}