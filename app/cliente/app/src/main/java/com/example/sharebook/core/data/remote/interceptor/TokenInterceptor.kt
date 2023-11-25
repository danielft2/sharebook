package com.example.sharebook.core.data.remote.interceptor

import com.example.sharebook.core.domain.adapter.TokenStorageManagement
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class TokenInterceptor @Inject constructor(private val tokenStorageManagement: TokenStorageManagement)
    : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val accessToken = runBlocking {
            tokenStorageManagement.retry().first()
        }

        val request = chain.request()
        val url = request.url.newBuilder().build()

        val newRequest = request
            .newBuilder()
            .url(url)
            .addHeader("Authorization","Bearer $accessToken")
            .build()
        return chain.proceed(newRequest)
    }
}