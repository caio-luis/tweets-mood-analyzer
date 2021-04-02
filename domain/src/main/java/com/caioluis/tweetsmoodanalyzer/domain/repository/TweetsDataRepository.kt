package com.caioluis.tweetsmoodanalyzer.domain.repository

import com.caioluis.tweetsmoodanalyzer.domain.entity.DomainTweetsList

/**
 * Created by Caio Luis (caio-luis) on 02/04/21
 */
interface TweetsDataRepository {
    suspend fun getTweetsData(userName: String): DomainTweetsList
}