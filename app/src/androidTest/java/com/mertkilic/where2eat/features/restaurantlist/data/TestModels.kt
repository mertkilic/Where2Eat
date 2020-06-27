package com.mertkilic.where2eat.features.restaurantlist.data

object TestModels {
  fun restaurantsMixed() = listOf(
    Restaurant(
      "Fes",
      Status.fromString("closed"),
      SortingValues(
        305, 73, 0.0f, 100, 0, 838, 0, 0
      ), true
    ),
    Restaurant(
      "Tanoi Sushi",
      Status.fromString("order ahead"),
      SortingValues(
        305, 73, 0.0f, 150, 0, 838, 0, 0
      ), true
    ),
    Restaurant(
      "Pizza Hut",
      Status.fromString("open"),
      SortingValues(
        305, 73, 0.0f, 200, 0, 838, 0, 0
      ), false
    )
  )
}