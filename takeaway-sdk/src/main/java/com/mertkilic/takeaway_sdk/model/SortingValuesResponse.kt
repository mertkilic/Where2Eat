package com.mertkilic.takeaway_sdk.model

data class SortingValuesResponse(
  val bestMatch: Int?,
  val newest: Int?,
  val ratingAverage: Float?,
  val distance: Int?,
  val popularity: Int?,
  val averageProductPrice: Int?,
  val deliveryCosts: Int?,
  val minCost: Int?
)