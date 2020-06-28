package com.mertkilic.where2eat.di

import com.mertkilic.takeaway_sdk.api.ApiClient
import com.mertkilic.takeaway_sdk.api.MockTakeawayApi
import com.mertkilic.takeaway_sdk.api.TakeawayApi
import com.mertkilic.takeaway_sdk.api.TakeawayApiClient
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient

@Module
open class NetworkModule {
  @ApplicationScope @Provides open fun okHttpClient(): OkHttpClient = OkHttpClient.Builder().build()

  @ApplicationScope @Provides open fun takeawayApiClient(takeawayApi: TakeawayApi): ApiClient = TakeawayApiClient(takeawayApi)

  @ApplicationScope @Provides open fun takeawayApi(okHttpClient: OkHttpClient): TakeawayApi = MockTakeawayApi()
  /**
   *   Here we can also initialize real TakeawayApi depending on the BuildConfig E.g.:
   *   if(BuildConfig == STAGING) MockTakeawayApi() else createApi()
   *
   *   private fun createApi(httpClient: OkHttpClient, baseUrl: String): TakeawayApi {
   *   val retrofit = Retrofit.Builder()
   *   .baseUrl(baseUrl)
   *   .client(httpClient)
   *   .addConverterFactory(MoshiConverterFactory.create(Moshi.Builder().build()))
   *   .build()
   *   return retrofit.create(TakeawayApi::class.java)
   *   }
   */
}