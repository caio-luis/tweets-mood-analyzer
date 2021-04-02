package com.caioluis.tweetsmoodanalyzer.domain.entity

/**
 * Created by Caio Luis (caio-luis) on 02/04/21
 */
data class DomainTweetsList(
    val tweets: List<DomainTweet>,
    val meta: DomainMeta
)