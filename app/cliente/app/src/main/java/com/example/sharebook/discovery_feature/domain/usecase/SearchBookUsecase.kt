package com.example.sharebook.discovery_feature.domain.usecase

import com.example.sharebook.discovery_feature.domain.adapter.SearchBookRepository
import javax.inject.Inject

class SearchBookUsecase @Inject constructor(private val searchBookRepository: SearchBookRepository){
    operator fun invoke() {
        println("Realizando a chamada à API")
    }
}