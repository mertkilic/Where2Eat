package com.mertkilic.takeaway_sdk.api

import com.squareup.moshi.Moshi
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

object JsonUtils {

  private val moshi = Moshi.Builder().build()

  fun <T> parseObjectFromResources(fileName: String, cls: Class<T>): T {
    val jsonString = loadJSONFromResources(fileName)
    val jsonAdapter = moshi.adapter<T>(cls)
    val parsedObject =  jsonAdapter.fromJson(jsonString)
    return parsedObject!!
  }

  private fun loadJSONFromResources(fileName: String?): String {
    if (fileName == null) {
      throw NullPointerException("File name should not be null")
    }
    val path = "json/$fileName.json"
    val result: String
    try {
      result = readFile(path)
    } catch (e: IOException) {
      throw IllegalStateException("Error reading body: $path", e)
    }

    return result
  }

  private fun readFile(path: String): String {
    val loader = Thread.currentThread().contextClassLoader
    val inputStream = loader.getResourceAsStream(path) ?: throw IllegalStateException("Invalid path: $path")
    val reader = BufferedReader(InputStreamReader(inputStream))
    val out = StringBuilder()
    var read: Int = 0
    while (reader.read().let { read = it; it != -1 }) {
      out.append(read.toChar())
    }
    reader.close()

    return out.toString()
  }
}
