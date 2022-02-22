package com.room.sample.dao

import android.app.Application
import android.util.Log
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

object UserDataBaseHelper {
    private val databseName: String = "userdatabase"
    private var dataBase: UserDataBase? = null
       private get() {
           return field
       }
       private set

    @JvmStatic
    fun getDb(app:Application):UserDataBase{
        if (dataBase == null) {
            //throw RuntimeException("请先init database")
            initDb(app)
        }
        return dataBase!!;
    }

    fun initDb(app:Application){
        Log.d("log_print","---initDb start---")
        if(dataBase != null){
            return
        }
        dataBase = Room.databaseBuilder(app,UserDataBase::class.java, databseName)
            //.addMigrations(MIGRATION_1_2) //添加升级规则
            .allowMainThreadQueries()
            .build();
        Log.d("log_print","---initDb end---")
    }

    //升级规则
    val MIGRATION_1_2: Migration = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL(
                "CREATE TABLE `Fruit` (`id` INTEGER, "
                        + "`name` TEXT, PRIMARY KEY(`id`))"
            )
        }
    }

    val MIGRATION_2_3: Migration = object : Migration(2, 3) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL(
                ("ALTER TABLE Book "
                        + " ADD COLUMN pub_year INTEGER")
            )
        }
    }
}