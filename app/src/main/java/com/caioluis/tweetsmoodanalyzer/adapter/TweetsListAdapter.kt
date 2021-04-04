package com.caioluis.tweetsmoodanalyzer.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.caioluis.tweetsmoodanalyzer.R
import com.caioluis.tweetsmoodanalyzer.model.SentimentEmoji
import com.caioluis.tweetsmoodanalyzer.model.UiTweet


/**
 * Created by Caio Luis (caio-luis) on 03/04/21
 */
class TweetsListAdapter :
    ListAdapter<UiTweet, TweetsListAdapter.TweetsViewHolder>(TWEETS_COMPARATOR) {
    private var onItemClicked: (tweet: UiTweet) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TweetsViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_tweet, parent, false)
        return TweetsViewHolder(view, onItemClicked)
    }

    override fun onBindViewHolder(holder: TweetsViewHolder, position: Int) {
        val tweet: UiTweet? = getItem(position)
        if (tweet != null) holder.bind(tweet)
    }

    fun onItemClickListener(listener: (tweet: UiTweet) -> Unit) {
        onItemClicked = listener
    }

    fun setEmojiOnItem(tweet: UiTweet, sentiment: SentimentEmoji) {
        val position = currentList.indexOf(tweet)
        tweet.sentimentEmoji = sentiment
        notifyItemChanged(position)
    }

    inner class TweetsViewHolder(
        view: View,
        private val itemClickListener: (tweet: UiTweet) -> Unit
    ) :
        RecyclerView.ViewHolder(view) {
        fun bind(item: UiTweet) {
            val tweetText = itemView.findViewById<AppCompatTextView>(R.id.itemTweetText)
            val emoji = itemView.findViewById<AppCompatTextView>(R.id.itemTweetSentimentEmoji)

            tweetText.text = item.text
            if (item.sentimentEmoji != null)
                emoji.text = item.sentimentEmoji?.toString()

            itemView.setOnClickListener {
                itemClickListener.invoke(item)
            }
        }
    }

    companion object {
        private val TWEETS_COMPARATOR = object : DiffUtil.ItemCallback<UiTweet>() {
            override fun areItemsTheSame(oldItem: UiTweet, newItem: UiTweet): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: UiTweet, newItem: UiTweet): Boolean {
                return oldItem == newItem
            }
        }
    }
}