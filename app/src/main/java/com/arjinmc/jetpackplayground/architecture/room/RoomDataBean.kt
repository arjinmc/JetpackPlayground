package com.arjinmc.jetpackplayground.architecture.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

/**
 * Created by Eminem Lo on 12/2/21
 * email: arjinmc@hotmail.com
 *
 * auto migration support
 * @DeleteColumn
 * @DeleteTable
 * @RenameColumn
 * @RenameTable
 */
@Entity(tableName = "room_data")
class RoomDataBean {

    @PrimaryKey(autoGenerate = true)
    var id: Long? = null

    @ColumnInfo(name = "content")
    var content: String? = null

    //use it when db version=1
    @Ignore
    var last_update: Long? = null
}