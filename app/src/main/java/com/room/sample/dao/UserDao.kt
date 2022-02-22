package com.room.sample.dao

import androidx.paging.DataSource
import androidx.room.*
import com.room.sample.entrys.User

/**
 * user 对应的dao类
 */
@Dao
interface UserDao {
    /**
     * 插入user对象
     * @param user Array<out User> 一个user或者多个user
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)//有了就替换
    fun insertUserAll(vararg user: User)
    /**
     * 删除user对象
     */
    @Delete
    fun delete(vararg user: User)

    /**
     * 更新user对象
     */
    @Update
    fun update(vararg user: User)

    /**
     * 查询所有user对象
     */
    @Query("select * from user")
    fun queryAll():List<User>

    /**
     * paging需要用到这种模式
     */
    @Query("select * from user")
    fun queryAllForPaging():DataSource.Factory<Int,User>

    /**
     * 根据用户名查询
     */
    @Query("select * from user where name = :name")
    fun queryByName(name:String):User
}