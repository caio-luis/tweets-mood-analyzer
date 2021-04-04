package com.caioluis.tweetsmoodanalyzer.data.remote.tweets.entity

import com.caioluis.tweetsmoodanalyzer.domain.tweets.entity.DomainMeta
import com.caioluis.tweetsmoodanalyzer.domain.tweets.entity.DomainTweetsList
import com.google.gson.annotations.SerializedName

/**
 * Created by Caio Luis (caio-luis) on 02/04/21
 */
data class RemoteTweetsList(
    @SerializedName("data")
    val tweets: List<RemoteTweet>? = listOf(),
    val meta: DomainMeta
)

fun RemoteTweetsList.toDomain() = DomainTweetsList(
    tweets = tweets?.map { it.toDomain() } ?: listOf(),
    meta = meta
)
