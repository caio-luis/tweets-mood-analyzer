package com.caioluis.tweetsmoodanalyzer.domain

import org.koin.dsl.module

/**
 * Created by Caio Luis (caio-luis) on 02/04/21
 */

val domainModule = module {
    factory { GetTweetsDataUseCase(tweetsDataRepository = get()) }
}