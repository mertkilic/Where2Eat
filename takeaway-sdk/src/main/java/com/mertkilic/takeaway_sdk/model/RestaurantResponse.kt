package com.mertkilic.takeaway_sdk.model

data class RestaurantResponse(
  val name: String?,
  val status: String?,
  val sortingValues: SortingValuesResponse?
)