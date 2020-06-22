package com.mertkilic.where2eat.features.restaurantlist.ui

import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.mertkilic.where2eat.R
import com.mertkilic.where2eat.base.BaseActivity
import com.mertkilic.where2eat.data.Result
import com.mertkilic.where2eat.databinding.ActivityRestaurantListBinding
import javax.inject.Inject

class RestaurantListActivity : BaseActivity() {

  @Inject lateinit var viewModel: RestaurantListViewModel

  private lateinit var binding: ActivityRestaurantListBinding
  private lateinit var adapter: RestaurantListAdapter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = DataBindingUtil.setContentView(this, R.layout.activity_restaurant_list)
    adapter = RestaurantListAdapter {
      //TODO implement restaurant add to favourite feature
    }
    binding.restaurantsRecyclerView.adapter = adapter
    subscribeUI()
  }

  private fun subscribeUI() {
    viewModel.restaurants.observe(this, Observer { result ->
      when (result.status) {
        Result.Status.LOADING -> binding.swipeRefreshLayout.isRefreshing = true
        Result.Status.SUCCESS -> {
          binding.swipeRefreshLayout.isRefreshing = false
          adapter.submitList(result.data?.restaurants)
        }
        Result.Status.ERROR -> Log.d("ERROR", "ERROR")//TODO show snack bar error message

      }
    })
  }
}