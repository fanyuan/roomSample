package com.room.sample.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.room.sample.entrys.User

/**
 * 定义数据库中数据表  版本号
 */
@Database(entities = [User::class],version = 2)
abstract class UserDataBase : RoomDatabase(){
    abstract fun userDao():UserDao
}