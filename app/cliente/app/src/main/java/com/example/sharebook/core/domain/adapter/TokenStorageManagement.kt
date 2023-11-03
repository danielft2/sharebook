package com.example.sharebook.core.domain.adapter

import kotlinx.coroutines.flow.Flow

interface TokenStorageManagement {
    suspend fun saved(accessToken: String)
    fun retry(): Flow<String?>
    suspend fun delete()
}