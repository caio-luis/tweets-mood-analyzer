package com.caioluis.tweetsmoodanalyzer

import android.app.Application
import com.caioluis.tweetsmoodanalyzer.data.dataModule
import com.caioluis.tweetsmoodanalyzer.domain.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

/**
 * Created by Caio Luis (caio-luis) on 02/04/21
 */
class TweetsMoodAnalyzerApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        setUpKoin()
    }

    private fun setUpKoin() {
        startKoin {
            androidLogger(level = Level.DEBUG)
            androidContext(this@TweetsMoodAnalyzerApplication)
            modules(
                listOf(
                    dataModule,
                    domainModule,
                    viewModelModule
                )
            )
        }
    }
}