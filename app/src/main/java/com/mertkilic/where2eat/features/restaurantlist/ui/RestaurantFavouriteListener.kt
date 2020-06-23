package com.mertkilic.where2eat.features.restaurantlist.ui

interface RestaurantFavouriteListener {
  fun onAddedToFavorites(restaurantName: String)
  fun onrRemovedFromFavorites(restaurantName: String)
}