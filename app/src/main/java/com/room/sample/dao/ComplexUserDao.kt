package com.room.sample.dao

import androidx.paging.DataSource
import androidx.room.*
import com.room.sample.entrys.ComplexUser
import com.room.sample.entrys.User

/**
 * user 对应的dao类
 */
@Dao
interface ComplexUserDao {
    /**
     * 插入user对象
     * @param user Array<out User> 一个user或者多个user
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertUserAll(vararg user: ComplexUser)
    /**
     * 删除user对象
     */
    @Delete
    fun delete(vararg user: ComplexUser)

    /**
     * 删除所有user对象
     */
    @Query("delete from ComplexUser")
    fun deleteAll()

    /**
     * 更新user对象
     */
    @Update
    fun update(vararg user: ComplexUser)

    /**
     * 查询所有user对象
     */
    @Query("select * from ComplexUser")
    fun queryAll():List<ComplexUser>

    /**
     * paging需要用到这种模式
     */
    @Query("select * from ComplexUser")
    fun queryAllForPaging():DataSource.Factory<Int,User>

    /**
     * 根据用户名查询
     */
    @Query("select * from ComplexUser where name = :name")
    fun queryByName(name:String):User
}