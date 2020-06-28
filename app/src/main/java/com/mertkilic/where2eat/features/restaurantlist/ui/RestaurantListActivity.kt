package com.mertkilic.where2eat.features.restaurantlist.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.view.forEach
import androidx.core.view.get
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.mertkilic.where2eat.R
import com.mertkilic.where2eat.base.BaseActivity
import com.mertkilic.where2eat.data.Result
import com.mertkilic.where2eat.databinding.ActivityRestaurantListBinding
import com.mertkilic.where2eat.features.restaurantlist.data.Restaurant
import com.mertkilic.where2eat.uitoolbox.DebouncingQueryTextListener
import javax.inject.Inject

class RestaurantListActivity : BaseActivity() {

  @Inject
  lateinit var viewModel: RestaurantListViewModel

  private lateinit var binding: ActivityRestaurantListBinding
  private lateinit var adapter: RestaurantListAdapter

  private val restaurantListObserver = Observer<Result<List<Restaurant>>> { result ->
    when (result.status) {
      Result.Status.LOADING -> binding.swipeRefreshLayout.isRefreshing = true
      Result.Status.SUCCESS -> {
        binding.swipeRefreshLayout.isRefreshing = false
        adapter.submitList(result.data)
      }
      Result.Status.ERROR -> {
        binding.swipeRefreshLayout.isRefreshing = false
        showErrorMessage(getString(result.errorStringId!!))
      }
    }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = DataBindingUtil.setContentView(this, R.layout.activity_restaurant_list)

    initRestaurantList()
    initSearchView()
    subscribeUI()
  }

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.menu_sort, menu)
    val subMenu = menu?.get(0)?.subMenu
    subMenu?.forEach {
      if (it.title == viewModel.selectedSortType) {
        it.isChecked = true
      }
    }
    return true
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    if (item.groupId == R.id.sorting_group && !item.isChecked) {
      item.isChecked = true

      val sortValue = item.title.toString()

      viewModel.sortRestaurants(sortValue).observe(this, restaurantListObserver)
      adapter.sortValue = sortValue
      return true
    }
    return super.onOptionsItemSelected(item)
  }

  private fun initRestaurantList() {
    adapter = RestaurantListAdapter(viewModel)
    adapter.registerAdapterDataObserver(
      object : RecyclerView.AdapterDataObserver() {
        override fun onItemRangeMoved(fromPosition: Int, toPosition: Int, itemCount: Int) {
          scrollToTop()
        }

        override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
          scrollToTop()
        }

        override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
          scrollToTop()
        }

        override fun onItemRangeChanged(positionStart: Int, itemCount: Int, payload: Any?) {
          scrollToTop()
        }
      })

    binding.restaurantsRecyclerView.adapter = adapter
    binding.swipeRefreshLayout.setOnRefreshListener {
      val searchQuery = binding.restaurantSearchView.query
      if (searchQuery.isNotBlank()) {
        viewModel.searchRestaurants(searchQuery.toString()).observe(this, restaurantListObserver)
      } else {
        subscribeUI()
      }
    }
  }

  private fun initSearchView() {
    binding.restaurantSearchView.setOnQueryTextListener(DebouncingQueryTextListener(
      this@RestaurantListActivity.lifecycle
    ) { newText ->
      newText?.let {
        if (it.isEmpty()) {
          viewModel.resetView().observe(this, restaurantListObserver)
        } else {
          viewModel.searchRestaurants(it).observe(this, restaurantListObserver)
        }
      }
    })
  }

  private fun subscribeUI() {
    viewModel.getRestaurants().observe(this, restaurantListObserver)
  }

  private fun scrollToTop() {
    binding.restaurantsRecyclerView.scrollToPosition(0)
  }
}