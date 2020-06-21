package com.mertkilic.where2eat.data

/**
 * Abstract Base Data source class with error handling
 */
abstract class BaseDataSource {

  protected suspend fun <T> getResult(call: suspend () -> T): Result<T> {
    return try {
      val response = call()
      Result.success(response)
    } catch (e: Exception) {
      error(e.message ?: e.toString())
    }
  }

  private fun <T> error(errorMessage: String): Result<T> {
    return Result.error(
      "This will be replaced with presentable error: $errorMessage"
    )
  }

}