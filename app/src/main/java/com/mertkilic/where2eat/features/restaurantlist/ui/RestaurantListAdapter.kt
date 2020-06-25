package com.mertkilic.where2eat.features.restaurantlist.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mertkilic.where2eat.R
import com.mertkilic.where2eat.features.restaurantlist.data.Restaurant
import com.mertkilic.where2eat.features.restaurantlist.data.SortType
import kotlinx.android.synthetic.main.item_restaurant_list.view.*

class RestaurantListAdapter(
  private val restaurantFavouriteListener: RestaurantFavouriteListener,
  viewModel: RestaurantListViewModel
) :
  ListAdapter<Restaurant, RestaurantListAdapter.RestaurantListViewHolder>(RestaurantItemDiffCallback()) {

  var sortValue: String = viewModel.selectedSortType
  set(value) {
    field = value
    notifyDataSetChanged()
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
    RestaurantListViewHolder(
      LayoutInflater.from(parent.context)
        .inflate(R.layout.item_restaurant_list, parent, false)
    )

  override fun onBindViewHolder(holder: RestaurantListViewHolder, position: Int) {
    holder.bind(getItem(position))
  }

  inner class RestaurantListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(restaurant: Restaurant) = with(itemView) {
      restaurantNameTextView.text = restaurant.name
      sortingValueTextView.text = "$sortValue : ${restaurant.getSelectedSortingValue(sortValue)}"
      with(statusTextView) {
        setTextColor(ContextCompat.getColor(itemView.context, restaurant.status.resColor))
        text = restaurant.status.value.capitalize()
      }

      with(favoriteButton) {
        favoriteButton.isChecked = restaurant.isFavorite

        setOnCheckedChangeListener { checkBox, isChecked ->
          if (!checkBox.isPressed) {
            return@setOnCheckedChangeListener
          }
          if (isChecked)
            restaurantFavouriteListener.onAddedToFavorites(restaurant.name)
          else
            restaurantFavouriteListener.onrRemovedFromFavorites(restaurant.name)
        }
      }
    }
  }

  private fun Restaurant.getSelectedSortingValue(sortValue: String) =
    when (SortType.fromString(sortValue)) {
      SortType.MIN_COST -> sortingValues.minCost
      SortType.DELIVERY_COSTS -> sortingValues.deliveryCosts
      SortType.AVG_PROD_PRICE -> sortingValues.averageProductPrice
      SortType.POPULARITY -> sortingValues.popularity
      SortType.DISTANCE -> sortingValues.distance
      SortType.RATING_AVG -> sortingValues.ratingAverage
      SortType.NEWEST -> sortingValues.newest
      SortType.BEST_MATCH -> sortingValues.bestMatch
    }

   class RestaurantItemDiffCallback : DiffUtil.ItemCallback<Restaurant>() {
    override fun areItemsTheSame(oldItem: Restaurant, newItem: Restaurant) =
      oldItem.name == newItem.name

    override fun areContentsTheSame(oldItem: Restaurant, newItem: Restaurant) =
      oldItem == newItem

  }
}