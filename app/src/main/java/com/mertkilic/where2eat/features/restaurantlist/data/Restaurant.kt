package com.mertkilic.where2eat.features.restaurantlist.data

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mertkilic.takeaway_sdk.model.RestaurantResponse

@Entity(tableName = "restaurants")
data class Restaurant(
  @PrimaryKey
  val name: String,
  val status: Status,
  @Embedded
  val sortingValues: SortingValues,
  var isFavorite: Boolean
) {
  constructor(restaurantResponse: RestaurantResponse) : this(
    name = restaurantResponse.name ?: "",
    status = Status.fromString(restaurantResponse.status ?: ""),
    sortingValues = SortingValues(
      restaurantResponse.sortingValues
    ),
    isFavorite = false
  )
}