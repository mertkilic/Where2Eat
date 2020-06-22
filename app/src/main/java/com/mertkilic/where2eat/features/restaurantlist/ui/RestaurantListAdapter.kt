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
import kotlinx.android.synthetic.main.item_restaurant_list.view.*

class RestaurantListAdapter(
  private val restaurantFavouriteListener: (restaurant: Restaurant) -> Unit
) :
  ListAdapter<Restaurant, RestaurantListAdapter.RestaurantListViewHolder>(RestaurantItemDiffCallback()) {

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
      //sortingValueTextView.text = //TODO Show selected sort type with restaurant's value
      with(statusTextView) {
        setTextColor(ContextCompat.getColor(itemView.context, restaurant.status.resColor))
        text = restaurant.status.value
      }

      with(favoriteButton) {
        //TODO favoriteButton.isChecked = restaurant.isFavourite

        setOnCheckedChangeListener { checkBox, isChecked ->
          if (!checkBox.isPressed) {
            return@setOnCheckedChangeListener
          }
          //restaurant.isFavourite = isChecked
          restaurantFavouriteListener.invoke(restaurant)
        }
      }

      setOnClickListener {
      }
    }
  }

  class RestaurantItemDiffCallback : DiffUtil.ItemCallback<Restaurant>() {
    override fun areItemsTheSame(oldItem: Restaurant, newItem: Restaurant) =
      oldItem.name == newItem.name

    override fun areContentsTheSame(oldItem: Restaurant, newItem: Restaurant) =
      oldItem == newItem

  }
}