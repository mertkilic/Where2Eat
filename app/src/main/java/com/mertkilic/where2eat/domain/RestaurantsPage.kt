package com.mertkilic.where2eat.domain

import com.mertkilic.takeaway_sdk.model.RestaurantListResponse

data class RestaurantsPage(val restaurants: List<Restaurant>) {
  constructor(restaurantListResponse: RestaurantListResponse) : this(
    restaurants = restaurantListResponse.restaurants?.map {
      Restaurant(it)
    } ?: emptyList()
  )
}