package com.room.sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.google.gson.Gson
import com.room.sample.dao.UserDataBaseHelper
import com.room.sample.databinding.ActivityRoomBaseBinding
import com.room.sample.entrys.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*
import kotlin.collections.ArrayList

class RoomBaseActivity : AppCompatActivity() {
    lateinit var binding:ActivityRoomBaseBinding;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_room_base)
    }

    fun query(view: View) {
        GlobalScope.launch (Dispatchers.IO){
            //UserDataBaseHelper.initDb(application)
            val list = UserDataBaseHelper.getDb(application).userDao().queryAll()

            withContext(Dispatchers.Main){
                val str = Gson().toJson(list)
                Log.d("log_debug", str)

                list.forEach{user ->
                    binding.tvDisplay.append("${Gson().toJson(user)} \n")
                }

            }
        }
    }
    fun queryRange(view: View) {
        lifecycleScope.launch(Dispatchers.IO){
            val list = UserDataBaseHelper.getDb(application).userDao().queryRange(7,5)
            Log.d("log_debug","RoomBaseActivity queryRange --- ${Thread.currentThread().name}")

            withContext(Dispatchers.Main){
                val str = Gson().toJson(list)
                Log.d("log_debug", "RoomBaseActivity queryRange --- ${Thread.currentThread().name}")

                list.forEach{user ->
                    binding.tvDisplay.append("${Gson().toJson(user)} \n")
                }
            }
        }
    }
    fun top(view: View) {
        lifecycleScope.launch(Dispatchers.IO){
            val user = UserDataBaseHelper.getDb(application).userDao().top()
            Log.d("log_debug","RoomBaseActivity top --- ${Thread.currentThread().name}")

            withContext(Dispatchers.Main){
                val str = Gson().toJson(user)
                Log.d("log_debug", "RoomBaseActivity top --- ${Thread.currentThread().name}")

                binding.tvDisplay.append("${Gson().toJson(user)} \n")
            }
        }
    }
    fun clear(view: View) {
        binding.tvDisplay.text = "RoomBaseActivity\n"
    }

    fun insert(view: View) {
        var u = getUser()//User()
        u.name = "??????${Random().nextInt(100)}"
        u.age = Random().nextInt(100)
        u.nickName = "abc${Random().nextInt(100)}"

        GlobalScope.launch (Dispatchers.IO){
            //UserDataBaseHelper.initDb(application)
            UserDataBaseHelper.getDb(application).userDao().insertUserAll(u)
        }
    }
    fun insertAll(view: View) {
        GlobalScope.launch (Dispatchers.IO){

            val array:Array<User> = Array(10) { getUser() }

            //UserDataBaseHelper.initDb(application)
            UserDataBaseHelper.getDb(application).userDao().insertUserAll(*array)
        }
    }

    fun deleteAll(view: View) {
        GlobalScope.launch (Dispatchers.IO) {
            //UserDataBaseHelper.initDb(application)
            val list:List<User> = UserDataBaseHelper.getDb(application).userDao().queryAll()

            val array:Array<User> = Array(list.size) { index ->
                Log.d("log_debug","index = $index")
                list[index]
            }

            UserDataBaseHelper.initDb(application)
            UserDataBaseHelper.getDb(application).userDao().delete(*array)
        }
    }

    private fun getUser(): User {
        val user = User()
        user.name = "??????${Random().nextInt(100)}"
        user.age = Random().nextInt(100)
        user.nickName = "abc${Random().nextInt(100)}"
        return user;
    }




}