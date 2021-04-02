package com.caioluis.tweetsmoodanalyzer.data.remote

import com.caioluis.tweetsmoodanalyzer.data.remote.entity.RemoteTweetsList
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Caio Luis (caio-luis) on 02/04/21
 */
interface TweetsDataService {

    @GET("2/tweets/search/recent")
    suspend fun getTweetsData(
        @Query("query") userName: String
    ): RemoteTweetsList
}