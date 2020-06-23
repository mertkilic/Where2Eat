package com.mertkilic.where2eat.features.restaurantlist.data

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import com.mertkilic.where2eat.data.BaseDataSource
import javax.inject.Inject

class RestaurantLocalDataSource @Inject constructor(
  private val dao: RestaurantDao
) : BaseDataSource() {

  fun getRestaurants() = dao.getAll().switchMap { restaurants ->
    dao.getFavorites().switchMap { favorites ->
      val restaurantsWithFavorites = restaurants.map {
        if (favorites.contains(Favorite(it.name)))
          it.isFavorite = true
        it
      }
      MutableLiveData(restaurantsWithFavorites)
    }
  }

  suspend fun saveRestaurants(restaurants: List<Restaurant>) = dao.insertOrUpdate(restaurants)

  suspend fun addToFavorites(favorite: Favorite) = getResult { dao.addToFavorites(favorite) }

  suspend fun removeFromFavorites(favorite: Favorite) = getResult { dao.removeFromFavorites(favorite) }
}