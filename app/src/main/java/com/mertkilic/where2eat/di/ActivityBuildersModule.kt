package com.mertkilic.where2eat.di

import com.mertkilic.where2eat.features.restaurantlist.ui.RestaurantListActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {
  @ActivityScope
  @ContributesAndroidInjector
  abstract fun contributeRestaurantListActivity(): RestaurantListActivity
}