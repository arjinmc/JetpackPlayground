package com.arjinmc.jetpackplayground.architecture.room

import androidx.room.*
import kotlinx.coroutines.flow.Flow

/**
 * Created by Eminem Lo on 6/22/22
 * email: arjinmc@hotmail.com
 */
@Dao
interface RoomDataForeignDao {

    @Query(
        "SELECT room_foreign.id AS id , room_foreign.content AS content ," +
                " room_data.content AS foreignContent FROM room_foreign " +
                "INNER JOIN room_data On room_data.id = room_foreign.dataId"
    )
    fun getList(): Flow<MutableList<RoomDataForeignDataBean>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun add(roomDataBean: RoomDataForeignDataBean): Long

    @Update
    fun update(roomDataBean: RoomDataForeignDataBean)

    @Query("DELETE FROM room_foreign WHERE id=:id")
    fun delete(id: Long): Int

    @Delete
    fun delete(roomDataBean: RoomDataForeignDataBean)
}