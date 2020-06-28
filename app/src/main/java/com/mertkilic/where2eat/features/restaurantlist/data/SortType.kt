package com.mertkilic.where2eat.features.restaurantlist.data

import com.mertkilic.where2eat.R
import com.mertkilic.where2eat.di.Dagger

enum class SortType(val value: String) {
  MIN_COST("minCost"),
  DELIVERY_COSTS("deliveryCosts"),
  AVG_PROD_PRICE("averageProductPrice"),
  POPULARITY("popularity"),
  DISTANCE("distance"),
  RATING_AVG("ratingAverage"),
  NEWEST("newest"),
  BEST_MATCH("bestMatch");

  companion object {
    fun fromString(value: String) : SortType{
      val context = Dagger.applicationComponent.context()
      return when (value) {
        context.getString(R.string.minimum_cost) -> MIN_COST
        context.getString(R.string.delivery_costs) -> DELIVERY_COSTS
        context.getString(R.string.avg_product_price) -> AVG_PROD_PRICE
        context.getString(R.string.popularity) -> POPULARITY
        context.getString(R.string.distance) -> DISTANCE
        context.getString(R.string.rating_average) -> RATING_AVG
        context.getString(R.string.newest) -> NEWEST
        context.getString(R.string.best_match) -> BEST_MATCH
        else -> POPULARITY
      }
    }

    fun getSortTypeWithOrder(value: String?): String {
      return when (value) {
        MIN_COST.value,
        DELIVERY_COSTS.value,
        AVG_PROD_PRICE.value,
        DISTANCE.value -> "$value ASC"
        POPULARITY.value,
        BEST_MATCH.value,
        RATING_AVG.value,
        NEWEST.value -> "$value DESC"
        else -> "popularity DESC"
      }
    }
  }
}