package com.mertkilic.takeaway_sdk.api

import com.mertkilic.takeaway_sdk.model.RestaurantListResponse

class MockTakeawayApi : TakeawayApi {

  override suspend fun getRestaurants() =
      JsonUtils.parseObjectFromResources("restaurants", RestaurantListResponse::class.java)

}