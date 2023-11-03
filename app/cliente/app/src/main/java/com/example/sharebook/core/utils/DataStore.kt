package com.example.sharebook.core.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore

val Context.dataStorage: DataStore<Preferences> by preferencesDataStore("@sharebook_storage")