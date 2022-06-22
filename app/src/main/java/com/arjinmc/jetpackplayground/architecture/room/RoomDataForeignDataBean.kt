package com.arjinmc.jetpackplayground.architecture.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

/**
 * Created by Eminem Lo on 6/22/22
 * email: arjinmc@hotmail.com
 */
@Entity(
    tableName = "room_foreign",
    foreignKeys = [ForeignKey(
        entity = RoomDataBean::class,
        parentColumns = ["id"],
        childColumns = ["dataId"],
        onDelete = ForeignKey.CASCADE
    )]
)
class RoomDataForeignDataBean {

    @PrimaryKey(autoGenerate = true)
    var id: Long? = null

    @ColumnInfo
    var content: String? = null

    var dataId: Long? = null
}