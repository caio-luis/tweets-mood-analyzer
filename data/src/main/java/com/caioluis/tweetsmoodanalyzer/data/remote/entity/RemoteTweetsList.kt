package com.caioluis.tweetsmoodanalyzer.data.remote.entity

import com.caioluis.tweetsmoodanalyzer.domain.entity.DomainMeta
import com.caioluis.tweetsmoodanalyzer.domain.entity.DomainTweetsList
import com.google.gson.annotations.SerializedName

/**
 * Created by Caio Luis (caio-luis) on 02/04/21
 */
data class RemoteTweetsList(
    @SerializedName("data")
    val tweets: List<RemoteTweet>,
    val meta: DomainMeta
)

fun RemoteTweetsList.toDomain() = DomainTweetsList(
    tweets = tweets.map { it.toDomain() },
    meta = meta
)
