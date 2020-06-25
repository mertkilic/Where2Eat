package com.mertkilic.where2eat.features.restaurantlist.data

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
    fun fromString(value: String) = //TODO maybe put this as second parameter
      when (value) {
        //TODO consider getting from strings.xml with context
        "Minimum Cost" -> MIN_COST
        "Delivery Cost" -> DELIVERY_COSTS
        "Average Product Price" -> AVG_PROD_PRICE
        "Popularity" -> POPULARITY
        "Distance" -> DISTANCE
        "Rating Average" -> RATING_AVG
        "Newest" -> NEWEST
        "Best Match" -> BEST_MATCH
        else -> POPULARITY
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