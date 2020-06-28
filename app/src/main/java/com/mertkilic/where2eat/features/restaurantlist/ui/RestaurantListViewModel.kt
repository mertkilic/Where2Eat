package com.mertkilic.where2eat.features.restaurantlist.ui

import android.util.Log
import androidx.lifecycle.distinctUntilChanged
import androidx.lifecycle.liveData
import androidx.lifecycle.map
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

  val selectedSortType by lazy {
    repository.getSelectedSortType()
  }

  fun sortRestaurants(selectedSortType: String) =
    liveData(coroutineScope.coroutineContext) {
      emit(Result.loading())
      val source = repository.sortRestaurants(selectedSortType)
      emitSource(source)
    }

  fun searchRestaurants(query: String) =
    liveData(coroutineScope.coroutineContext) {
      emit(Result.loading())
      val source = repository.searchRestaurants(query)
      emitSource(source)
    }

  fun resetView() =
    liveData(coroutineScope.coroutineContext) {
      emit(Result.loading())
      val source = repository.observeRestaurantsFromDb().map { Result.success(it) }
      emitSource(source)
    }

  fun addToFavorites(restaurantName: String) =
    coroutineScope.launch {
      val result = repository.addToFavorites(restaurantName)
      if (result.status == Result.Status.ERROR) {
        Log.d(TAG, "Add to favorites failed")
      }
    }

  fun removeFromFavorites(restaurantName: String) {
    coroutineScope.launch {
      repository.removeFromFavorites(restaurantName)
    }
  }

}