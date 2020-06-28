package com.mertkilic.takeaway_sdk.api


import com.mertkilic.takeaway_sdk.model.RestaurantListResponse

class TakeawayApiClient(private val takeawayApi: TakeawayApi) : ApiClient {

  override suspend fun getRestaurants(): RestaurantListResponse {
    return takeawayApi.getRestaurants()
  }
}