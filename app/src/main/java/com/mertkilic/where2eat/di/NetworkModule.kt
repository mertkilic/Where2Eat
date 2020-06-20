package com.mertkilic.where2eat.di

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient

@Module
open class NetworkModule {
  @ApplicationScope @Provides open fun okHttpClient(): OkHttpClient = OkHttpClient.Builder().build()
}