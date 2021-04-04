package com.caioluis.tweetsmoodanalyzer.model

import com.caioluis.tweetsmoodanalyzer.domain.tweets.entity.DomainTweet

/**
 * Created by Caio Luis (caio-luis) on 03/04/21
 */
data class UiTweet(
    val id: String = "",
    val text: String = "",
    var sentimentEmoji: SentimentEmoji? = null,
)

fun DomainTweet.toUi() = UiTweet(id, text)