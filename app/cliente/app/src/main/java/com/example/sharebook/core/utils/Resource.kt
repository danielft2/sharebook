package com.example.sharebook.core.utils

sealed class Resource<T>(val data: T? = null, val message: String? = null, val code: Number? = null) {
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(message: String, data: T? = null, code: Number? = null) : Resource<T>(data, message, code)
    class Loading<T> : Resource<T>()
    class Finnaly<T> : Resource<T>()
}