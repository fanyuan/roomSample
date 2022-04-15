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
     * 删除所有指定index前的user对象
     */
    @Query("delete from User where autoId < :baseId")
    fun deleteBeforeId(baseId:Int)
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
     * 查询所有user对象,按降序排列
     */
    @Query("select * from user  ORDER BY autoId DESC")
    fun queryAllDesc():List<User>
    /**
     * 查询所有user对象
     */
    @Query("select * from user WHERE autoId < :startId+1  ORDER BY autoId DESC LIMIT  :size   OFFSET 0")
    fun queryRange(startId:Int,size:Int):List<User>
    /**
     * 查询最顶上的对象
     */
    @Query("select * from user  ORDER BY autoId DESC LIMIT 1")
    fun top():User
    /**
     * 查询最顶上的对象的id
     */
    @Query("select autoId from user  ORDER BY autoId DESC LIMIT 1")
    fun topId():Int
    /**
     * 查询从上到下数第指定序号对象的id
     */
    @Query("select autoId from (select autoId from user  ORDER BY autoId DESC LIMIT :size) ORDER BY autoId ASC LIMIT 1")
    fun topIdBySize(size: Int):Int
    /**
     * 查询数据库条目数量
     */
    @Query("select count(*) from user")
    fun size():Int
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