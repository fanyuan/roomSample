package com.room.sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
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

    fun clear(view: View) {
        binding.tvDisplay.text = "RoomBaseActivity"
    }

    fun insert(view: View) {
        var u = getUser()//User()
        u.name = "测试${Random().nextInt(100)}"
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
        user.name = "测试${Random().nextInt(100)}"
        user.age = Random().nextInt(100)
        user.nickName = "abc${Random().nextInt(100)}"
        return user;
    }


}