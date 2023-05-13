package com.vo.vo_kiosk.DB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.vo.vo_kiosk.DTO.TokenSave
import com.vo.vo_kiosk.NetWork.UserDao

@Database(entities = [TokenSave::class], version = 1)
abstract class DataBase : RoomDatabase(){

    abstract fun UserDao(): UserDao

    companion object {
        private var INSTANCE: DataBase? = null
        fun getDBInstance(context: Context): DataBase? {

            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    DataBase::class.java,
                    "User_Token"
                )
                    .allowMainThreadQueries()
                    .build()
            }
            return INSTANCE
        }
    }
}