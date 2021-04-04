package com.caioluis.tweetsmoodanalyzer.util

import android.content.Context
import com.caioluis.tweetsmoodanalyzer.R
import com.caioluis.tweetsmoodanalyzer.exception.UserNotFoundException
import retrofit2.HttpException
import java.net.UnknownHostException

/**
 * Created by Caio Luis (caio-luis) on 03/04/21
 */
object ErrorMessageRetriever {
    fun getErrorMessage(error: Throwable, context: Context) = when (error) {
        is UnknownHostException -> context.getString(R.string.error_connection)

        is UserNotFoundException -> context.getString(R.string.error_user_not_found)

        is HttpException -> {
            when (error.code()) {
                400 -> context.getString(R.string.error_google_request_not_authorized)
                401 -> context.getString(R.string.error_twitter_request_not_authorized)
                else -> context.getString(R.string.generic_error)
            }
        }

        else -> context.getString(R.string.generic_error)
    }
}