package com.example.sharebook.core.data.local.storage

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.sharebook.core.domain.adapter.TokenStorageManagement
import com.example.sharebook.core.utils.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TokenStorageManagerImpl @Inject constructor(
    private val store: DataStore<Preferences>
) : TokenStorageManagement  {
    companion object Key {
        private const val TOKEN_KEY = Constants.STORAGE_TOKEN_KEY
        val tokenKey = stringPreferencesKey(TOKEN_KEY)
    }

    override suspend fun saved(accessToken: String) {
        store.edit { preferences ->
            preferences[tokenKey] = accessToken
        }
    }

    override fun retry(): Flow<String?> {
        return store.data.map { preference ->
            preference[tokenKey]
        }
    }

    override suspend fun delete() {
        TODO("Not yet implemented")
    }
}