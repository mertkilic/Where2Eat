package com.mertkilic.where2eat.data

import android.content.Context
import android.content.SharedPreferences
import com.mertkilic.where2eat.di.ApplicationScope
import javax.inject.Inject

@ApplicationScope
class SharedPrefStorage @Inject constructor(
  context: Context
) : Storage {

  private val sharedPreferences: SharedPreferences

  init {
    sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
  }

  override fun getString(key: Storage.Key, defValue: String?): String? {
    return sharedPreferences.getString(key.value, defValue)
  }

  override fun putString(key: Storage.Key, value: String) {
    sharedPreferences.edit().putString(key.value, value).apply()
  }

  companion object {
    const val PREF_NAME = "SharedPrefPersister"
  }

}