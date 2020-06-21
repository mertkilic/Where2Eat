package com.mertkilic.where2eat.di

import com.mertkilic.takeaway_sdk.api.ApiClient
import com.mertkilic.takeaway_sdk.api.MockApiClient
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient

@Module
open class NetworkModule {
  @ApplicationScope @Provides open fun okHttpClient(): OkHttpClient = OkHttpClient.Builder().build()

  @ApplicationScope @Provides open fun takeawayApiClient(okHttpClient: OkHttpClient): ApiClient = MockApiClient()
  /**
   *   Here we can also initialize real ApiClient depending on the BuildConfig E.g.:
   *   if(BuildConfig == STAGING) MockApiClient() else TakeawayApiClient(okHttpClient)
   */
}