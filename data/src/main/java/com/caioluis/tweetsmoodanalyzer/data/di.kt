package com.caioluis.tweetsmoodanalyzer.data

import com.caioluis.tweetsmoodanalyzer.data.base.BaseUrls
import com.caioluis.tweetsmoodanalyzer.data.base.ServiceBuilder
import com.caioluis.tweetsmoodanalyzer.data.remote.TweetsDataService
import com.caioluis.tweetsmoodanalyzer.data.remote.implementation.TweetsDataRepositoryImpl
import com.caioluis.tweetsmoodanalyzer.domain.repository.TweetsDataRepository
import org.koin.dsl.module

/**
 * Created by Caio Luis (caio-luis) on 02/04/21
 */

val dataModule = module {
    factory {
        ServiceBuilder<TweetsDataService>(
            baseUrl = BaseUrls.TWITTER_BASE_URL,
            authToken = BuildConfig.TWITTER_BEARER_TOKEN
        )
    }
    factory<TweetsDataRepository> { TweetsDataRepositoryImpl(tweetsService = get()) }
}