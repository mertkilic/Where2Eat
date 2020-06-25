package com.mertkilic.where2eat.database

import androidx.room.TypeConverter
import com.mertkilic.where2eat.features.restaurantlist.data.Status

class Converters {

  @TypeConverter
  fun toStatus(ordinal: Int) = Status.values()[ordinal]

  @ExperimentalStdlibApi
  @TypeConverter
  fun toStatusInt(status: Status) = status.ordinal

}