package com.arjinmc.jetpackplayground.architecture.room

import androidx.room.*
import kotlinx.coroutines.flow.Flow

/**
 * Created by Eminem Lo on 12/7/21
 * email: arjinmc@hotmail.com
 * callback detail:https://developer.android.com/training/data-storage/room/async-queries#kotlin
 */
@Dao
interface RoomDataDao {

    @Query("SELECT * FROM room_data")
    fun getList(): Flow<List<RoomDataBean>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun add(roomDataBean: RoomDataBean)

    @Update
    fun update(roomDataBean: RoomDataBean)

    @Query("DELETE FROM room_data WHERE id=:id")
    fun delete(id: Long)

    @Delete
    fun delete(roomDataBean: RoomDataBean)
}