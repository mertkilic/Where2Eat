package com.mertkilic.takeaway_sdk.api


import com.mertkilic.takeaway_sdk.model.RestaurantListResponse
import retrofit2.http.GET

/**
 * Actual takeaway endpoints that go to network
 */
interface TakeawayApi {

  @GET("restaurants")
  suspend fun getRestaurants(): RestaurantListResponse
}