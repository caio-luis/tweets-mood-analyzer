package com.caioluis.tweetsmoodanalyzer

import com.caioluis.tweetsmoodanalyzer.viewmodel.TweetsDataViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by Caio Luis (caio-luis) on 02/04/21
 */

val viewModelModule = module {
    viewModel { TweetsDataViewModel(getTweetsDataUseCase = get()) }
}