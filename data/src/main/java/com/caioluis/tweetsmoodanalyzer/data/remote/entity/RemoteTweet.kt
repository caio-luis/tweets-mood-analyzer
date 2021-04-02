package com.caioluis.tweetsmoodanalyzer.data.remote.entity

import com.caioluis.tweetsmoodanalyzer.domain.entity.DomainTweet

/**
 * Created by Caio Luis (caio-luis) on 02/04/21
 */
data class RemoteTweet(
    val id: Int,
    val text: String
)

fun RemoteTweet.toDomain() = DomainTweet(id, text)