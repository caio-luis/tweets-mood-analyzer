package com.caioluis.tweetsmoodanalyzer.util

import android.content.Context
import com.caioluis.tweetsmoodanalyzer.R
import java.net.UnknownHostException

/**
 * Created by Caio Luis (caio-luis) on 03/04/21
 */
object ErrorMessageRetriever {
    fun getErrorMessage(error: Throwable, context: Context) = when (error) {
        is UnknownHostException -> context.getString(R.string.internet_error)
        else -> context.getString(R.string.generic_error)
    }
}