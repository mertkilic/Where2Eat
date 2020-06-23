package com.mertkilic.where2eat.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mertkilic.where2eat.features.restaurantlist.data.Favorite
import com.mertkilic.where2eat.features.restaurantlist.data.RestaurantDao
import com.mertkilic.where2eat.features.restaurantlist.data.Restaurant

@Database(entities = [Restaurant::class, Favorite::class], version = 1)
@TypeConverters(Converters::class)
abstract class TakeawayDatabase : RoomDatabase() {
  abstract fun restaurantDao(): RestaurantDao
}