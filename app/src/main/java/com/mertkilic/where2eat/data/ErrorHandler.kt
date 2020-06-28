package com.mertkilic.where2eat.data

import android.database.sqlite.SQLiteException
import com.mertkilic.where2eat.R
import java.net.UnknownHostException

object ErrorHandler {
  fun handleError(e: Exception) =
    when (e) {
      is UnknownHostException -> R.string.error_internet
      is SQLiteException -> R.string.error_database
      else -> R.string.error_generic
    }
}