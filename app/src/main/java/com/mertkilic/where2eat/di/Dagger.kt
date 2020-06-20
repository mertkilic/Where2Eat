package com.mertkilic.where2eat.di

import com.mertkilic.where2eat.Where2EatApp

enum class Dagger {
  INJECTOR;

  lateinit var applicationComponent: ApplicationComponent

  fun create(application: Where2EatApp): Dagger {
    applicationComponent = DaggerApplicationComponent.builder()
      .networkModule(NetworkModule())
      .applicationModule(application.applicationModule)
      .build()

    return this
  }
}