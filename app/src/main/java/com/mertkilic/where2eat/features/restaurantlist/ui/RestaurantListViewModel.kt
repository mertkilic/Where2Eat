package com.mertkilic.where2eat.features.restaurantlist.ui

import androidx.lifecycle.ViewModel
import com.mertkilic.where2eat.data.resultLiveData
import com.mertkilic.where2eat.features.restaurantlist.data.RestaurantListRepository
import javax.inject.Inject

class RestaurantListViewModel @Inject constructor(
  private val repository: RestaurantListRepository
) : ViewModel() {

  val restaurants = resultLiveData {
    repository.observeRestaurantsFromNetwork()
  }

}