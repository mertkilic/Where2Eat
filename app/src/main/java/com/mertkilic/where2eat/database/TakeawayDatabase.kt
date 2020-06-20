package com.mertkilic.where2eat.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mertkilic.where2eat.database.dao.RestaurantDao
import com.mertkilic.where2eat.domain.Restaurant

@Database(entities = [Restaurant::class], version = 1)
abstract class TakeawayDatabase : RoomDatabase() {
  abstract fun restaurantDao(): RestaurantDao
}