package com.mertkilic.where2eat.features.restaurantlist.data

import androidx.annotation.ColorRes
import com.mertkilic.where2eat.R

enum class Status(val value: String, @ColorRes val resColor: Int) {
  OPEN("open", R.color.restaurant_open),
  ORDER_AHEAD("order ahead", R.color.restaurant_order_ahead),
  CLOSED("closed", R.color.restaurant_closed),
  UNKNOWN("", R.color.restaurant_closed);

  companion object {
    fun fromString(value: String?) =
      when (value) {
        OPEN.value -> OPEN
        CLOSED.value -> CLOSED
        ORDER_AHEAD.value -> ORDER_AHEAD
        else -> UNKNOWN
      }

  }
}