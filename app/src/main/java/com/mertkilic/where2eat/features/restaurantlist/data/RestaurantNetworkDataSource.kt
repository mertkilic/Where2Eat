package com.mertkilic.where2eat.features.restaurantlist.data

import com.mertkilic.takeaway_sdk.api.ApiClient
import com.mertkilic.where2eat.data.BaseDataSource
import com.mertkilic.where2eat.data.Result
import javax.inject.Inject

class RestaurantNetworkDataSource @Inject constructor(
  private val apiClient: ApiClient
) : BaseDataSource() {

  suspend fun fetchRestaurantList(): Result<RestaurantsPage> =
    getResult {
      val restaurantListResponse = apiClient.getRestaurants()
      RestaurantsPage(restaurantListResponse)
    }

}