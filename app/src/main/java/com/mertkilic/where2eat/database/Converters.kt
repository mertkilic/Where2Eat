package com.mertkilic.where2eat.database

import androidx.room.TypeConverter
import com.mertkilic.where2eat.features.restaurantlist.data.Status

class Converters {
  @TypeConverter
  fun fromString(stringListString: String): List<String> {
    return stringListString.split(",").map { it }
  }

  @TypeConverter
  fun toString(stringList: List<String>): String {
    return stringList.joinToString(separator = ",")
  }

  @TypeConverter fun toStatus(filter: String) =
    Status.fromString(filter)


  @TypeConverter fun toStatusString(status: Status) =
    status.value

}