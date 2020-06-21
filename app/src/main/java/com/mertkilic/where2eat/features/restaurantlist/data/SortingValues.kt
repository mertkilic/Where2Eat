package com.mertkilic.where2eat.features.restaurantlist.data

import com.mertkilic.takeaway_sdk.model.SortingValuesResponse

data class SortingValues(
  val bestMatch: Int,
  val newest: Int,
  val ratingAverage: Float?,
  val distance: Int,
  val popularity: Int,
  val averageProductPrice: Int,
  val deliveryCosts: Int,
  val minCost: Int
) {
  constructor(sortingValuesResponse: SortingValuesResponse?) : this(
    bestMatch = sortingValuesResponse?.bestMatch ?: 0,
    newest = sortingValuesResponse?.newest ?: 0,
    ratingAverage = sortingValuesResponse?.ratingAverage ?: 0f,
    distance = sortingValuesResponse?.distance ?: 0,
    popularity = sortingValuesResponse?.popularity ?: 0,
    averageProductPrice = sortingValuesResponse?.averageProductPrice ?: 0,
    deliveryCosts = sortingValuesResponse?.deliveryCosts ?: 0,
    minCost = sortingValuesResponse?.minCost ?: 0
  )
}