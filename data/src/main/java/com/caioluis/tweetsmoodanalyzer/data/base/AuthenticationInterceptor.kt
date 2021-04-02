package com.caioluis.tweetsmoodanalyzer.data.base

import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created by Caio Luis (caio-luis) on 02/04/21
 */
class AuthenticationInterceptor(
    private val token: String?
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val requestWithToken = originalRequest.newBuilder()
            .header("Authorization", "Bearer ${token ?: ""}")
            .build()
        return chain.proceed(requestWithToken)
    }
}