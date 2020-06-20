package com.mertkilic.takeaway_sdk.api

import com.mertkilic.takeaway_sdk.model.RestaurantListResponse


interface ApiClient {
  fun getType(): ApiType
  suspend fun getRestaurants(): RestaurantListResponse
}