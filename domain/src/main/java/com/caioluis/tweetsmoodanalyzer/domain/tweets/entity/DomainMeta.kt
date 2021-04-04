package com.caioluis.tweetsmoodanalyzer.domain.tweets.entity

/**
 * Created by Caio Luis (caio-luis) on 02/04/21
 */
data class DomainMeta(
    val newestId: Int,
    val oldestId: Int,
    val resultCount: Int,
    val nextToken: String
)