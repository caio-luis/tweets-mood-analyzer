package com.caioluis.tweetsmoodanalyzer.data.remote.text_sentiment.entity

/**
 * Created by Caio Luis (caio-luis) on 02/04/21
 */
data class RemoteTextToAnalyze(
    val content: String,
    val type: String = "PLAIN_TEXT"
)
