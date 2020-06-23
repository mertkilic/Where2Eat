package com.mertkilic.where2eat.database

import androidx.room.TypeConverter
import com.mertkilic.where2eat.features.restaurantlist.data.Status

class Converters {

  @TypeConverter fun toStatus(statusString: String) =
    Status.fromString(statusString.split(",").first())

  @ExperimentalStdlibApi
  @TypeConverter fun toStatusString(status: Status) =
    "${status.value},${status.resColor}"

}