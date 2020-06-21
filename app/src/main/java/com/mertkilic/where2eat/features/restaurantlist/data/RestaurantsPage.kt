package com.mertkilic.where2eat.features.restaurantlist.data

import com.mertkilic.takeaway_sdk.model.RestaurantListResponse

data class RestaurantsPage(val restaurants: List<Restaurant>) {
  constructor(restaurantListResponse: RestaurantListResponse) : this(
    restaurants = restaurantListResponse.restaurants?.map {
      Restaurant(it)
    } ?: emptyList()
  )
}