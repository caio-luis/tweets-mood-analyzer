package com.caioluis.tweetsmoodanalyzer.data.remote.tweets.implementation

import com.caioluis.tweetsmoodanalyzer.data.remote.tweets.TweetsDataService
import com.caioluis.tweetsmoodanalyzer.data.remote.tweets.entity.toDomain
import com.caioluis.tweetsmoodanalyzer.domain.tweets.entity.DomainTweetsList
import com.caioluis.tweetsmoodanalyzer.domain.tweets.repository.TweetsDataRepository

/**
 * Created by Caio Luis (caio-luis) on 02/04/21
 */
class TweetsDataRepositoryImpl(
    private val tweetsService: TweetsDataService
) : TweetsDataRepository {
    override suspend fun getTweetsData(userName: String): DomainTweetsList {
        return tweetsService.getTweetsData(
            mountUserNameStringToApi(userName)
        ).toDomain()
    }

    private fun mountUserNameStringToApi(param: String) = "from:$param"
}