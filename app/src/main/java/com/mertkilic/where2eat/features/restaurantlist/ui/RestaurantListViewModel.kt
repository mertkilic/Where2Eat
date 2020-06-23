package com.mertkilic.where2eat.features.restaurantlist.ui

import android.util.Log
import androidx.lifecycle.distinctUntilChanged
import com.mertkilic.where2eat.base.BaseViewModel
import com.mertkilic.where2eat.data.Result
import com.mertkilic.where2eat.data.resultLiveData
import com.mertkilic.where2eat.features.restaurantlist.data.RestaurantListRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

const val TAG = "RestaurantListViewModel"

class RestaurantListViewModel @Inject constructor(
  private val repository: RestaurantListRepository,
  scope: CoroutineScope
) : BaseViewModel(scope) {

  val restaurants by lazy {
    resultLiveData(
      databaseQuery = { repository.observeRestaurantsFromDb() },
      networkCall = { repository.observeRestaurantsFromNetwork() },
      saveCallResult = { repository.saveRestaurants(it.restaurants) }
    ).distinctUntilChanged()
  }

  fun addToFavorites(restaurantName: String) =
    coroutineScope.launch {
      val result = repository.addToFavorites(restaurantName)
      if (result.status == Result.Status.ERROR) {
        Log.d(TAG, result.message!!)
      }
    }

  fun removeFromFavorites(restaurantName: String){
    coroutineScope.launch {
      repository.removeFromFavorites(restaurantName)
      Log.d(TAG, "")
    }
  }

}