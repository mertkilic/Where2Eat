package com.mertkilic.where2eat.features.restaurantlist.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.mertkilic.where2eat.data.Result
import javax.inject.Inject

class RestaurantListRepository @Inject constructor(
  private val localDataSource: RestaurantLocalDataSource,
  private val remoteDataSource: RestaurantNetworkDataSource
) {

  fun observeRestaurantsFromDb(): LiveData<List<Restaurant>> =
    localDataSource.getRestaurantsSortedBy()

  fun sortRestaurants(sortType: String): LiveData<Result<List<Restaurant>>> {
    localDataSource.sortType = sortType

    return observeRestaurantsFromDb().map {
      Result.success(it)
    }
  }

  fun searchRestaurants(query: String) =
    localDataSource.getRestaurantsSortedBy(query).map {
      Result.success(it)
    }

  fun getSelectedSortType() = localDataSource.sortType

  suspend fun observeRestaurantsFromNetwork() =
    remoteDataSource.fetchRestaurantList()

  suspend fun saveRestaurants(restaurants: List<Restaurant>) =
    localDataSource.saveRestaurants(restaurants)

  suspend fun addToFavorites(restaurantName: String) =
    localDataSource.addToFavorites(Favorite(restaurantName))

  suspend fun removeFromFavorites(restaurantName: String) =
    localDataSource.removeFromFavorites(Favorite(restaurantName))

}