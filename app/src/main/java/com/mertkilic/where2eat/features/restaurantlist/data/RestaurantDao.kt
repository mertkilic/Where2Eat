package com.mertkilic.where2eat.features.restaurantlist.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.RawQuery
import androidx.sqlite.db.SupportSQLiteQuery
import com.mertkilic.where2eat.database.BaseDao

@Dao
abstract class RestaurantDao : BaseDao<Restaurant>() {

  @Query("SELECT * FROM restaurants")
  abstract fun getAll(): LiveData<List<Restaurant>>

  @RawQuery(observedEntities = [Restaurant::class])
  abstract fun findByName(query: SupportSQLiteQuery): LiveData<List<Restaurant>>

  @Query("SELECT * FROM favorites")
  abstract fun getFavorites(): LiveData<List<Favorite>>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  abstract suspend fun addToFavorites(favourite: Favorite)

  @Delete
  abstract suspend fun removeFromFavorites(favourite: Favorite)
}