package com.caioluis.tweetsmoodanalyzer.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.caioluis.tweetsmoodanalyzer.R
import com.caioluis.tweetsmoodanalyzer.domain.base.Response
import com.caioluis.tweetsmoodanalyzer.domain.entity.DomainTweetsList
import com.caioluis.tweetsmoodanalyzer.viewmodel.TweetsDataViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity() {

    private val tweetsDataViewModel: TweetsDataViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        observeTweetsDataResponse()
        tweetsDataViewModel.getTweets("")
    }

    private fun observeTweetsDataResponse() {
        tweetsDataViewModel.tweetsObservableLiveData.observe(
            this@HomeActivity,
            Observer(::handleTweetsDataResponse)
        )
    }

    private fun handleTweetsDataResponse(response: Response<DomainTweetsList>) {
        response.handleResponse(
            onLoading = {},
            onSuccess = {},
            onFailure = {}
        )
    }

    private fun showTweetsList() {

    }

    private fun showLoading() {

    }

    private fun hideLoading() {

    }
}