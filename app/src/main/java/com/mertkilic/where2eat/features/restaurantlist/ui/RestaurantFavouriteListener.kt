package com.mertkilic.where2eat.features.restaurantlist.ui

interface RestaurantFavouriteListener {
  fun onAddedToFavorites(restaurantName: String)
  fun onRemovedFromFavorites(restaurantName: String)
}