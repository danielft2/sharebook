package com.example.sharebook.discovery_feature.data.remote.repository

import com.example.sharebook.discovery_feature.data.remote.service.SearchBookService
import com.example.sharebook.discovery_feature.domain.adapter.SearchBookRepository
import javax.inject.Inject

class SearchBookRepositoryImpl @Inject constructor(private val searchBookService: SearchBookService):
    SearchBookRepository {
    override suspend fun searchBook() {
        println("Realizando chamada à API")
    }
}