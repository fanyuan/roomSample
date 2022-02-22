package com.room.sample.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.room.sample.entrys.ComplexUser
import com.room.sample.entrys.InkListConvert
import com.room.sample.entrys.User

/**
 * 定义数据库中数据表  版本号
 */
@Database(entities = [ComplexUser::class],version = 2)
abstract class ComplexUserDataBase : RoomDatabase(){
    abstract fun userDao():ComplexUserDao
}