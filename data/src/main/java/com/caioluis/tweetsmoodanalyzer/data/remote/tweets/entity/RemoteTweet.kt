package com.caioluis.tweetsmoodanalyzer.data.remote.tweets.entity

import com.caioluis.tweetsmoodanalyzer.domain.tweets.entity.DomainTweet

/**
 * Created by Caio Luis (caio-luis) on 02/04/21
 */
data class RemoteTweet(
    val id: String,
    val text: String
)

fun RemoteTweet.toDomain() = DomainTweet(id, text)