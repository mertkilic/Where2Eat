package com.mertkilic.where2eat.features.restaurantlist.data

import androidx.annotation.ColorRes
import com.mertkilic.where2eat.R

enum class Status(val value: String, @ColorRes val resColor: Int) {
  OPEN("Open", R.color.restaurant_open),
  CLOSED("Closed", R.color.restaurant_closed),
  ORDER_AHEAD("Order Ahead", R.color.restaurant_order_ahead),
  UNKNOWN("", R.color.snackbar_error);

  companion object {
    fun fromString(value: String?) =
      when (value) {
        "open" -> OPEN
        "closed" -> CLOSED
        "order ahead" -> ORDER_AHEAD
        else -> UNKNOWN
      }

  }
}