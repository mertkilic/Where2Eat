package com.mertkilic.where2eat.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel

abstract class BaseViewModel (val coroutineScope: CoroutineScope): ViewModel() {

  override fun onCleared() {
    super.onCleared()
    coroutineScope.cancel()
  }
}