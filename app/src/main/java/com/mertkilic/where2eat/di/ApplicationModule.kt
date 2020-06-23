package com.mertkilic.where2eat.di

import android.content.Context
import androidx.room.Room
import com.mertkilic.where2eat.Where2EatApp
import com.mertkilic.where2eat.database.TakeawayDatabase
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

@Module
open class ApplicationModule(private val application: Where2EatApp) {

  @Provides fun applicationContext(): Context = application.applicationContext

  @Provides
  fun database(applicationContext: Context): TakeawayDatabase =
    Room.databaseBuilder(
      applicationContext,
      TakeawayDatabase::class.java, "ta-database"
    ).build()

  @Provides
  fun restaurantDao(database: TakeawayDatabase) = database.restaurantDao()

  @Provides
  fun provideCoroutineScopeIO() = CoroutineScope(Dispatchers.IO)
}