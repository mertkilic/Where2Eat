package com.mertkilic.where2eat.features.restaurantlist.ui

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.mertkilic.where2eat.base.BaseActivity
import com.mertkilic.where2eat.data.Result
import javax.inject.Inject

class RestaurantListActivity: BaseActivity() {

  @Inject lateinit var viewModel: RestaurantListViewModel

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    viewModel.restaurants.observe(this, Observer { result->
      if(result.status == Result.Status.SUCCESS){
        Log.d("","")
      } else {
        Log.d("","")
      }
    })
  }
}