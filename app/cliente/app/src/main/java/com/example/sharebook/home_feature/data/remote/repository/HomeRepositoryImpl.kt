package com.example.sharebook.home_feature.data.remote.repository

import com.example.sharebook.home_feature.data.remote.response.ListBooksResponse
import com.example.sharebook.home_feature.data.remote.service.HomeService
import com.example.sharebook.home_feature.domain.adpater.HomeRepository
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(private val homeService: HomeService) : HomeRepository {
    override suspend fun listBooks(): ListBooksResponse {
        return homeService.listBooks()
    }
}