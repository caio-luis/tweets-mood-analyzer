package com.caioluis.tweetsmoodanalyzer

import com.caioluis.tweetsmoodanalyzer.model.SentimentEmoji

/**
 * Created by Caio Luis (caio-luis) on 03/04/21
 */
object SentimentScoreChecker {
    fun getSentimentEmoji(score: Double): SentimentEmoji {
        return when (score) {
            in 0.25..1.0 -> SentimentEmoji.Smile
            in -0.25..0.25 -> SentimentEmoji.Neutral
            in -1.0..-0.25 -> SentimentEmoji.Pensive
            else -> SentimentEmoji.Neutral
        }
    }
}