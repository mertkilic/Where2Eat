package com.mertkilic.where2eat.base

import android.view.View
import com.google.android.material.snackbar.Snackbar
import com.mertkilic.where2eat.R
import dagger.android.support.DaggerAppCompatActivity

abstract class BaseActivity : DaggerAppCompatActivity() {

  fun showErrorMessage(errorMessage: String) {
    val contentView = window.findViewById<View>(android.R.id.content)
    val snackbar = Snackbar.make(contentView, errorMessage, Snackbar.LENGTH_LONG)
    snackbar.view.setBackgroundResource(R.color.snackbar_error)
    snackbar.show()
  }

}