package com.mertkilic.where2eat.features.restaurantlist.data

import androidx.lifecycle.LiveData
import androidx.sqlite.db.SimpleSQLiteQuery
import com.mertkilic.where2eat.data.BaseDataSource
import com.mertkilic.where2eat.data.Storage
import javax.inject.Inject

class RestaurantLocalDataSource @Inject constructor(
  private val dao: RestaurantDao,
  private val sharedPrefStorage: Storage
) : BaseDataSource() {

  var sortType: String
    get() =
      sharedPrefStorage.getString(Storage.Key.SORT_VALUE, "Popularity")!!
    set(value) =
      sharedPrefStorage.putString(Storage.Key.SORT_VALUE, value)

  fun getRestaurantsSortedBy(query: String? = null): LiveData<List<Restaurant>> {
    val orderByParams =
      SortType.getSortTypeWithOrder(SortType.fromString(sortType).value)
    val restaurantsQueryString = query?.let { "SELECT * FROM restaurants WHERE name LIKE '%$it%'" }
      ?: "SELECT * FROM restaurants"
    val sqLiteQuery =
      SimpleSQLiteQuery("$restaurantsQueryString ORDER BY isFavorite DESC, status ASC, $orderByParams")
    return dao.getBySortingValue(sqLiteQuery)
  }

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