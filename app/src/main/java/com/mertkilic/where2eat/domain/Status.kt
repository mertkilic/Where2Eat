package com.mertkilic.where2eat.domain

enum class Status(val value: String) {
  OPEN("open"),
  CLOSED("closed"),
  ORDER_AHEAD("order ahead"),
  UNKNOWN("");

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