package com.mertkilic.where2eat.data

interface Storage {

  enum class Key constructor(val value: String) {
    SORT_VALUE("SORT_VALUE");

    override fun toString(): String {
      return value
    }
  }

  fun getString(key: Key, defaultValue: String? = null): String?

  fun putString(key: Key, value: String)
}