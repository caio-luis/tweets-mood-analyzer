package com.caioluis.tweetsmoodanalyzer.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageButton
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.caioluis.tweetsmoodanalyzer.R
import com.caioluis.tweetsmoodanalyzer.adapter.TweetsListAdapter
import com.caioluis.tweetsmoodanalyzer.domain.base.Response
import com.caioluis.tweetsmoodanalyzer.model.SentimentEmoji
import com.caioluis.tweetsmoodanalyzer.model.UiTweet
import com.caioluis.tweetsmoodanalyzer.ui.ProgressDialogFragment
import com.caioluis.tweetsmoodanalyzer.util.ErrorMessageRetriever
import com.caioluis.tweetsmoodanalyzer.viewmodel.TextSentimentViewModel
import com.caioluis.tweetsmoodanalyzer.viewmodel.TweetsDataViewModel
import com.google.android.material.textfield.TextInputEditText
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity() {

    private val tweetsDataViewModel: TweetsDataViewModel by viewModel()
    private val textSentimentViewModel: TextSentimentViewModel by viewModel()

    private lateinit var tweetsSearchBar: TextInputEditText
    private lateinit var searchButton: AppCompatImageButton
    private lateinit var tweetsListRecyclerView: RecyclerView
    private lateinit var tweetsAdapter: TweetsListAdapter
    private lateinit var progressDialog: ProgressDialogFragment
    private var clickedTweet = UiTweet()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        initVariables()
        setListeners()
        observeTweetsDataResponse()
        observeTextSentimentDataResponse()
    }

    private fun setListeners() {
        searchButton.setOnClickListener {
            getTweetsFromTypedUser()
        }

        tweetsAdapter.onItemClickListener {
            clickedTweet = it
            getTweetsTextSentiment(it)
        }
    }

    private fun getTweetsTextSentiment(uiTweet: UiTweet) {
        textSentimentViewModel.sendTextToSentimentAnalysis(uiTweet.text)
    }

    private fun initVariables() {
        tweetsSearchBar = findViewById(R.id.tweetsUserSearchBar)
        tweetsListRecyclerView = findViewById(R.id.tweetsListRecyclerView)
        searchButton = findViewById(R.id.tweetsSearchButton)
        progressDialog = ProgressDialogFragment()

        tweetsAdapter = TweetsListAdapter().also {
            tweetsListRecyclerView.adapter = it
        }
    }

    private fun getTweetsFromTypedUser() {
        val userName = tweetsSearchBar.text
        if (!userName.isNullOrBlank())
            tweetsDataViewModel.getTweets(userName.toString())
    }

    private fun observeTweetsDataResponse() {
        tweetsDataViewModel.tweetsObservableLiveData.observe(
            this@HomeActivity,
            Observer(::handleTweetsDataResponse)
        )
    }

    private fun observeTextSentimentDataResponse() {
        textSentimentViewModel.textSentimentObservable.observe(
            this@HomeActivity,
            Observer(::handleTextSentimentDataResponse)
        )
    }

    private fun handleTweetsDataResponse(response: Response<List<UiTweet>>) {
        response.handleResponse(
            onLoading = ::showLoading,
            onSuccess = {
                showTweetsList(it)
                clearInputAndSetHint()
            },
            onFailure = {
                hideLoading()
                val errorMessage = ErrorMessageRetriever.getErrorMessage(error = it, context = this)
                showLongToast(errorMessage)
            }
        )
    }

    private fun handleTextSentimentDataResponse(response: Response<SentimentEmoji>) {
        response.handleResponse(
            onLoading = ::showLoading,
            onSuccess = {
                hideLoading()
                setSentimentEmojiOnItem(it)
            },
            onFailure = {
                hideLoading()
                val errorMessage = ErrorMessageRetriever.getErrorMessage(error = it, context = this)
                showLongToast(errorMessage)
            }
        )
    }

    private fun showLongToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    private fun setSentimentEmojiOnItem(sentiment: SentimentEmoji) {
        tweetsAdapter.setEmojiOnItem(clickedTweet, sentiment)
    }

    private fun showTweetsList(tweets: List<UiTweet>) {
        hideLoading()
        tweetsAdapter.submitList(tweets)
    }

    private fun clearInputAndSetHint() {
        tweetsSearchBar.apply {
            hint = text.toString()
            text?.clear()
        }
    }

    private fun showLoading() {
        if (!progressDialog.isAdded && !progressDialog.isResumed)
            progressDialog.show(supportFragmentManager, ProgressDialogFragment.TAG)
    }

    private fun hideLoading() {
        if (progressDialog.isAdded && progressDialog.isResumed)
            progressDialog.dismissAllowingStateLoss()
    }
}