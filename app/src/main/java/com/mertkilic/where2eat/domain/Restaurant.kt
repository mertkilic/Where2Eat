package com.mertkilic.where2eat.domain

import androidx.room.Entity
import com.mertkilic.takeaway_sdk.model.RestaurantResponse

@Entity(tableName = "restaurants")
data class Restaurant(
  val name: String,
  val status: Status,
  val sortingValues: SortingValues
){
  constructor(restaurantResponse: RestaurantResponse):this(
    name = restaurantResponse.name?:"",
    status = Status.fromString(restaurantResponse.status),
    sortingValues = SortingValues(restaurantResponse.sortingValues)
  )
}