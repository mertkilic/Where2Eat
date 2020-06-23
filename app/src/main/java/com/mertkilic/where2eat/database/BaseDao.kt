package com.mertkilic.where2eat.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Transaction
import androidx.room.Update

@Dao
abstract class BaseDao<T> {
  /**
   * @return row id of the item inserted
   */
  @Insert(onConflict = OnConflictStrategy.IGNORE)
  abstract suspend fun insert(obj: T): Long

  /**
   * @return list of row ids of the items inserted
   */
  @Insert(onConflict = OnConflictStrategy.IGNORE)
  abstract suspend fun insert(obj: List<T>): List<Long>

  /**
   * @return number of rows affected by update/delete
   */
  @Update
  abstract suspend fun update(obj: T): Int

  /**
   * @return number of rows affected by update/delete
   */
  @Update
  abstract suspend fun update(obj: List<T>): Int

  @Transaction
  open suspend fun insertOrUpdate(obj: T): Int {
    val rowId = insert(obj)
    return if (rowId == -1L)
      update(obj)
    else 0
  }

  @Transaction
  open suspend fun insertOrUpdate(objList: List<T>): Int {
    val rowIds = insert(objList)

    val updateList = mutableListOf<T>()

    for (i in rowIds.indices) {
      if (rowIds[i] == -1L) updateList.add(objList[i])
    }

    return if (updateList.isNotEmpty())
      update(updateList)
    else
      0
  }
}