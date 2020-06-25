package com.mertkilic.where2eat.features.restaurantlist.data

import com.mertkilic.where2eat.data.BaseDataSource
import javax.inject.Inject

class RestaurantLocalDataSource @Inject constructor(
  private val dao: RestaurantDao
) : BaseDataSource() {

  fun getRestaurants() = dao.getAll()

  suspend fun saveRestaurants(restaurants: List<Restaurant>) {
    val favorites = dao.getFavorites()
    val restaurantsWithFavorites = restaurants.map {
      if (favorites.contains(Favorite(it.name)))
        it.isFavorite = true
      it
    }
    dao.insertOrUpdate(restaurantsWithFavorites)
  }

  suspend fun addToFavorites(favorite: Favorite) = getResult { dao.addToFavorites(favorite) }

  suspend fun removeFromFavorites(favorite: Favorite) =
    getResult { dao.removeFromFavorites(favorite) }
}