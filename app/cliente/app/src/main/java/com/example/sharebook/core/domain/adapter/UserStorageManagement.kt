package com.example.sharebook.core.domain.adapter

import com.example.sharebook.core.domain.model.UserModel
import kotlinx.coroutines.flow.Flow

interface UserStorageManagement {
    suspend fun saved(user: UserModel)
    fun retry(): Flow<UserModel?>
    suspend fun delete()
}