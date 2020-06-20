package com.mertkilic.takeaway_sdk.api

import com.mertkilic.takeaway_sdk.model.RestaurantListResponse

class MockApiClient : ApiClient {

  override fun getType() = ApiType.MOCK

  override suspend fun getRestaurants() =
      JsonUtils.parseObjectFromResources("restaurants", RestaurantListResponse::class.java)

}