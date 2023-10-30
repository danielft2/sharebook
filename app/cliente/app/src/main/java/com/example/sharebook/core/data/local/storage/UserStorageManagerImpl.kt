package com.example.sharebook.core.data.local.storage
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.sharebook.core.domain.adapter.UserStorageManagement
import com.example.sharebook.core.domain.model.UserModel
import com.example.sharebook.core.utils.Constants
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserStorageManagerImpl @Inject constructor(
    private val store: DataStore<Preferences>,
    private val gson: Gson
) : UserStorageManagement {
    companion object Key {
        private const val USER_KEY = Constants.STORAGE_USER_KEY
        val userKey = stringPreferencesKey(USER_KEY)
    }

    override suspend fun saved(user: UserModel) {
        store.edit { preference ->
            preference[userKey] = gson.toJson(user)
        }
    }

    override fun retry(): Flow<UserModel?> {
        val userFlow: Flow<UserModel?> = store.data.map { preference ->
            gson.fromJson(preference[userKey], UserModel::class.java) ?: null
        }

        return userFlow
    }

    override suspend fun delete() {

    }
}