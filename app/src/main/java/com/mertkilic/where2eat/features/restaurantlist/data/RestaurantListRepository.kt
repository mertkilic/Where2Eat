package com.mertkilic.where2eat.features.restaurantlist.data

import androidx.lifecycle.LiveData
import javax.inject.Inject

class RestaurantListRepository @Inject constructor(
  private val localDataSource: RestaurantLocalDataSource,
  private val remoteDataSource: RestaurantNetworkDataSource
) {

  fun observeRestaurantsFromDb(): LiveData<List<Restaurant>> =
    localDataSource.getRestaurants()

  suspend fun observeRestaurantsFromNetwork() =
    remoteDataSource.fetchRestaurantList()

  suspend fun saveRestaurants(restaurants: List<Restaurant>) =
    localDataSource.saveRestaurants(restaurants)

  suspend fun addToFavorites(restaurantName: String) =
    localDataSource.addToFavorites(Favorite(restaurantName))

  suspend fun removeFromFavorites(restaurantName: String) =
    localDataSource.removeFromFavorites(Favorite(restaurantName))

}