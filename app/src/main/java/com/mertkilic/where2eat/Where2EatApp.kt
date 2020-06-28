package com.mertkilic.where2eat

import com.mertkilic.where2eat.di.ApplicationModule
import com.mertkilic.where2eat.di.Dagger
import dagger.android.support.DaggerApplication

class Where2EatApp : DaggerApplication() {

  val applicationModule by lazy { ApplicationModule(this) }

  override fun onCreate() {
    super.onCreate()
  }

  override fun applicationInjector() = Dagger.create(this).applicationComponent
}