package com.mertkilic.takeaway_sdk.api

import com.mertkilic.takeaway_sdk.model.RestaurantListResponse


interface ApiClient {
  suspend fun getRestaurants(): RestaurantListResponse
}