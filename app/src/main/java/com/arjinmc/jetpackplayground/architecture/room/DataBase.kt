package com.arjinmc.jetpackplayground.architecture.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

/**
 * Created by Eminem Lo on 12/2/21
 * email: arjinmc@hotmail.com
 * detail: https://developer.android.com/training/data-storage/room
 */

//@Database(entities = [RoomDataBean::class], version = 2)
//use AutoMigration, this part not working yet
//@Database(
//    version = 2,
//    entities = [RoomDataBean::class],
//    autoMigrations = {
//        @AutoMigration(from = 1, to = 2, spec = RoomDataBean::class)
//    }
//            exprotSchema = true
//)
@Database(entities = [RoomDataBean::class], version = 1)
abstract class DataBase : RoomDatabase() {

    abstract fun dataDao(): RoomDataDao

    companion object {
        private val DB_NAME = "Room.db"
        private var INSTANCE: DataBase? = null
        fun getInstance(context: Context): DataBase {

            @Synchronized
            if (INSTANCE == null) {
                INSTANCE =
                    Room.databaseBuilder(context.applicationContext, DataBase::class.java, DB_NAME)
                        //use when db version>1
//                        .addMigrations(MIGRATION_VERSION_2)
                        .build()

            }
            return INSTANCE as DataBase
        }

        private val MIGRATION_VERSION_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL(
                    "ALTER TABLE room_data ADD COLUMN last_update INTEGER DEFAULT 0"
                )
            }
        }
    }


}