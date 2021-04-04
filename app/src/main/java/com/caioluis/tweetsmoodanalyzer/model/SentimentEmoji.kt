package com.caioluis.tweetsmoodanalyzer.model

/**
 * Created by Caio Luis (caio-luis) on 03/04/21
 */
sealed class SentimentEmoji {
    object Pensive : SentimentEmoji()
    object Neutral : SentimentEmoji()
    object Smile : SentimentEmoji()

    override fun toString(): String {
        return when (this) {
            is Smile -> SMILE_EMOJI_UNICODE
            is Neutral -> NEUTRAL_FACE_EMOJI_UNICODE
            is Pensive -> PENSIVE_EMOJI_UNICODE
        }
    }

    companion object {
        private const val PENSIVE_EMOJI_UNICODE = "\uD83D\uDE04"
        private const val NEUTRAL_FACE_EMOJI_UNICODE = "\uD83D\uDE10"
        private const val SMILE_EMOJI_UNICODE = "\uD83D\uDE14"
    }
}