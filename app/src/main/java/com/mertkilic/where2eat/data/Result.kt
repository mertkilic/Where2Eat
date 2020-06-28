package com.mertkilic.where2eat.data

import androidx.annotation.StringRes

/**
 * A generic class that holds a value with its status.
 * This is the recommended approach to deal with responses in MVVM from Google
 * Result is usually created by the Repository classes then it's received
 * in `ViewModel` wrapped with `LiveData<Result<T>>` to pass back the latest data
 * to the UI with its fetch status.
 * */
data class Result<out T>(val status: Status, val data: T?, val errorStringId: Int?) {

  enum class Status {
    SUCCESS,
    ERROR,
    LOADING
  }

  companion object {
    fun <T> success(data: T): Result<T> {
      return Result(
        Status.SUCCESS,
        data,
        null
      )
    }

    fun <T> error(stringId: Int, data: T? = null): Result<T> {
      return Result(
        Status.ERROR,
        data,
        stringId
      )
    }

    fun <T> loading(data: T? = null): Result<T> {
      return Result(
        Status.LOADING,
        data,
        null
      )
    }
  }
}