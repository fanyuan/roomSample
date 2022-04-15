package com.room.sample.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.room.sample.entrys.User

/**
 * 定义数据库中数据表  版本号
 */
@Database(entities = [User::class],version = 2)
abstract class UserDataBase : RoomDatabase(){
    companion object{
        const val DATABSE_NAME: String = "userdatabase"

        var instance: UserDataBase? = null;
        @JvmStatic
        fun getInstance(context: Context):UserDataBase{
            if (instance == null) {
                synchronized(UserDataBase::class.java) {
                    if (instance == null) {
                        instance =
                            Room.databaseBuilder(
                                context.applicationContext,
                                UserDataBase::class.java,
                                DATABSE_NAME
                            ) //.addMigrations() 添加升级规则
                                .allowMainThreadQueries()
                                .build()
                    }
                }
            }
            return instance!!;
        }
    }

    abstract fun userDao():UserDao


}