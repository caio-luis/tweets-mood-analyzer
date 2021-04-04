package com.caioluis.tweetsmoodanalyzer.util

import com.caioluis.tweetsmoodanalyzer.model.SentimentEmoji
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

/**
 * Created by Caio Luis (caio-luis) on 04/04/21
 */
class SentimentScoreCheckerTest {

    @Test
    fun `assert that returns smile emoji in all score range`() {

        val scoreSequence = 0.25..1.0 step 0.01

        for (score in scoreSequence) {
            assertThat(SentimentScoreChecker.getSentimentEmoji(score)).isEqualTo(SentimentEmoji.Smile)
        }
    }

    @Test
    fun `assert that returns neutral emoji in all score range`() {

        val scoreSequence = -0.25..0.25 step 0.01

        for (score in scoreSequence) {
            assertThat(SentimentScoreChecker.getSentimentEmoji(score)).isEqualTo(SentimentEmoji.Neutral)
        }
    }

    @Test
    fun `assert that returns pensive emoji in all score range`() {

        val scoreSequence = -1.0..-0.25 step 0.01

        for (score in scoreSequence) {
            assertThat(SentimentScoreChecker.getSentimentEmoji(score)).isEqualTo(SentimentEmoji.Pensive)
        }
    }

    @Test
    fun `assert that returns neutral emoji when not in range`() {
        assertThat(SentimentScoreChecker.getSentimentEmoji(1.1)).isEqualTo(SentimentEmoji.Neutral)
    }

    infix fun ClosedRange<Double>.step(step: Double): Sequence<Double> =
        generateSequence(start) { it + step }.takeWhile { it <= endInclusive }
}