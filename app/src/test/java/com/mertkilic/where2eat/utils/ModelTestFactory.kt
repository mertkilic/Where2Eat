package com.mertkilic.where2eat.utils

import com.mertkilic.where2eat.features.restaurantlist.data.Restaurant
import com.mertkilic.where2eat.features.restaurantlist.data.SortingValues
import com.mertkilic.where2eat.features.restaurantlist.data.Status

object ModelTestFactory {
  fun restaurants() = listOf(
    Restaurant(
      "Sushi Place",
      Status.fromString("open"),
      SortingValues(
        305, 73, 0.0f, 2880, 0, 838, 0, 0
      ), false
    ),Restaurant(
      "Tommis Burger",
      Status.fromString("closed"),
      SortingValues(
        305, 73, 0.0f, 2880, 0, 838, 0, 0
      ), false
    )

  )
}