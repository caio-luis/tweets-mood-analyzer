package com.caioluis.tweetsmoodanalyzer.data.remote.implementation

import com.caioluis.tweetsmoodanalyzer.data.remote.TweetsDataService
import com.caioluis.tweetsmoodanalyzer.data.remote.entity.toDomain
import com.caioluis.tweetsmoodanalyzer.domain.entity.DomainTweetsList
import com.caioluis.tweetsmoodanalyzer.domain.repository.TweetsDataRepository

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

    fun mountUserNameStringToApi(param: String) = "from:$param"
}