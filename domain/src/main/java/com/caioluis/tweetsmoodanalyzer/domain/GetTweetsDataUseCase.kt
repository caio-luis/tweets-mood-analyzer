package com.caioluis.tweetsmoodanalyzer.domain

import com.caioluis.tweetsmoodanalyzer.domain.base.BaseUseCase
import com.caioluis.tweetsmoodanalyzer.domain.base.Response
import com.caioluis.tweetsmoodanalyzer.domain.entity.DomainTweetsList
import com.caioluis.tweetsmoodanalyzer.domain.repository.TweetsDataRepository

/**
 * Created by Caio Luis (caio-luis) on 02/04/21
 */
class GetTweetsDataUseCase(
    private val tweetsDataRepository: TweetsDataRepository
) : BaseUseCase<String, DomainTweetsList>() {
    override suspend fun run(parameters: String) {
        responseChannel.run {
            send(Response.Loading)

            val response = try {
                Response.Success(
                    tweetsDataRepository.getTweetsData(parameters)
                )
            } catch (ex: Exception) {
                Response.Failure(ex)
            }

            send(response)
        }
    }
}