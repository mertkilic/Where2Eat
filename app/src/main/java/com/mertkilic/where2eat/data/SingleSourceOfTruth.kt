package com.mertkilic.where2eat.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers

/**
 * The database serves as the single source of truth.
 * Therefore UI can receive data updates from database only.
 * Function notify UI about:
 * [Result.Status.SUCCESS] - with data from database
 * [Result.Status.ERROR] - if error has occurred from any source
 * [Result.Status.LOADING]
 */
//TODO add database call later
fun <T> resultLiveData(networkCall: suspend () -> Result<T>): LiveData<Result<T>> =
  liveData(Dispatchers.IO) {
    emit(Result.loading<T>())

    val responseStatus = networkCall()
    if (responseStatus.status == Result.Status.SUCCESS) {
      emitSource(MutableLiveData(responseStatus))
    } else if (responseStatus.status == Result.Status.ERROR) {
      emitSource(MutableLiveData(responseStatus))
    }
  }