package com.caioluis.tweetsmoodanalyzer.data.base

import com.caioluis.tweetsmoodanalyzer.data.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

interface ServiceBuilder {

    companion object {
        inline operator fun <reified S> invoke(baseUrl: String, authToken: String?): S {

            val httpClient = buildInterceptors(authToken)

            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient)
                .build()
                .create(S::class.java)
        }

        fun buildInterceptors(authToken: String?): OkHttpClient = OkHttpClient.Builder().apply {
            val authInterceptor = AuthenticationInterceptor(authToken)
            val queryParamAuthInterceptor = QueryAuthenticationInterceptor()

            callTimeout(60, TimeUnit.SECONDS)
            connectTimeout(60, TimeUnit.SECONDS)
            readTimeout(60, TimeUnit.SECONDS)
            writeTimeout(60, TimeUnit.SECONDS)
            addInterceptor(getLoggerInterceptor())
            if (authToken.isNullOrBlank()) {
                addInterceptor(queryParamAuthInterceptor)
            }
            if (!interceptors().contains(authInterceptor))
                addInterceptor(authInterceptor)
        }.build()

        private fun getLoggerInterceptor(): HttpLoggingInterceptor {
            val level = if (BuildConfig.DEBUG)
                HttpLoggingInterceptor.Level.BODY
            else
                HttpLoggingInterceptor.Level.NONE
            return HttpLoggingInterceptor().apply { this.level = level }
        }
    }
}