package com.mertkilic.where2eat.features.restaurantlist.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class Favorite(
  @PrimaryKey
  val restaurantName: String
)