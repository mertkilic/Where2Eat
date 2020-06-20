package com.mertkilic.where2eat.di

import android.content.Context
import com.mertkilic.where2eat.Where2EatApp
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import okhttp3.OkHttpClient

@ApplicationScope
@Component(
  modules = [
    ApplicationModule::class, NetworkModule::class,
    AndroidSupportInjectionModule::class, ActivityBuildersModule::class
  ]
)
interface ApplicationComponent: AndroidInjector<Where2EatApp> {

  fun context(): Context

  fun okHttpClient(): OkHttpClient

}