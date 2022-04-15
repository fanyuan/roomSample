package com.room.sample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.View
import com.google.gson.Gson
import com.room.sample.dao.UserDataBaseHelper
import com.room.sample.entrys.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun roomBase(view: View) {
        startActivity(Intent(this,RoomBaseActivity::class.java))
    }

    fun RoomPaging(view: View) {
        startActivity(Intent(this,RoomPagingActivity::class.java))
    }

    fun complex(view: View) {
        startActivity(Intent(this,RoomComplexActivity::class.java))
    }

    fun removeOldData(view: View) {
        startActivity(Intent(this,RemoveOldItemActivity::class.java))
    }
}