package com.mertkilic.where2eat.features.restaurantlist.data

import javax.inject.Inject

class RestaurantListRepository @Inject constructor(
  private val dao: RestaurantDao,
  private val remoteDataSource: RestaurantListDataSource
) {

  suspend fun observeRestaurantsFromDb(){
    //TODO fetch restaurants from DB
  }

  suspend fun observeRestaurantsFromNetwork() =
    remoteDataSource.fetchRestaurantList()

  fun observeRestaurants(){
    //TODO implement database first approach
  }

}