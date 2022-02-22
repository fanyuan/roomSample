package com.room.sample.entrys

import androidx.room.*

/**
 * user 实体类定义
 *  indices = [Index(value = ["name"], unique = true)]  = name 唯一约束
 */
@Entity(indices = [Index(value = ["name"],unique = true)])
@TypeConverters(InkListConvert::class,BookListConvert::class)
class ComplexUser {
    constructor(){}
    @PrimaryKey(autoGenerate = true)
    var autoId:Int = 0
    @ColumnInfo(name = "name")
    var name:String? = ""
    var nickName:String? = null
    var age:Int = 0
    @Embedded
    var address:Address? = null
    //@TypeConverters(InkListConvert::class)
    var inks:ArrayList<Ink> = arrayListOf()
    //@TypeConverters(BookListConvert::class)
    var books:ArrayList<Book> = arrayListOf()
    @TypeConverters(StringListConvert::class)
    var tags:ArrayList<String> = arrayListOf()
}