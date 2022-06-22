package com.arjinmc.jetpackplayground.architecture.room

import androidx.room.*
import kotlinx.coroutines.flow.Flow

/**
 * Created by Eminem Lo on 6/22/22
 * email: arjinmc@hotmail.com
 */
@Dao
interface RoomDataForeignDao {

    @Query("SELECT * FROM room_foreign")
    fun getList(): Flow<MutableList<RoomDataForeignDataBean>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun add(roomDataBean: RoomDataForeignDataBean)

    @Update
    fun update(roomDataBean: RoomDataForeignDataBean)

    @Query("DELETE FROM room_foreign WHERE id=:id")
    fun delete(id: Long)

    @Delete
    fun delete(roomDataBean: RoomDataForeignDataBean)
}