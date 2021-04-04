package com.caioluis.tweetsmoodanalyzer.data.base

import com.caioluis.tweetsmoodanalyzer.data.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created by Caio Luis (caio-luis) on 02/04/21
 */
class QueryAuthenticationInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val urlWithQueryParam = originalRequest.url.newBuilder()
            .addQueryParameter("key", BuildConfig.GOOGLE_API_TOKEN)
            .build()

        val request = originalRequest.newBuilder().url(urlWithQueryParam).build()
        return chain.proceed(request)
    }
}