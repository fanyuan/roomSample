package com.room.sample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.room.sample.dao.UserDataBaseHelper;
import com.room.sample.databinding.ActivityRoomBaseBinding;
import com.room.sample.databinding.ActivityRoomPagingBinding;
import com.room.sample.entrys.User;

import java.util.Random;
import java.util.concurrent.Executors;

import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;

public class RoomPagingActivity extends AppCompatActivity {
    ActivityRoomPagingBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_room_paging);

        MyAdapter adapter = new MyAdapter();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        binding.list.setAdapter(adapter);
        binding.list.setLayoutManager(layoutManager);

        LiveData pagedListLiveData = new LivePagedListBuilder<>(UserDataBaseHelper.getDb(getApplication()).userDao().queryAllForPaging(), 2).build();
        pagedListLiveData.observeForever(new Observer<PagedList<User>>() {
            @Override
            public void onChanged(PagedList<User> users) {
                Log.d("log_print","pagedListLiveData---onChanged");
                //提交数据,更新界面
                adapter.submitList(users);

                users.addWeakCallback(null,new PagedList.Callback(){
                    @Override
                    public void onChanged(int position, int count) {
                        Log.d("log_print","onChanged: position :  "+position+" count : "+count);
                    }

                    @Override
                    public void onInserted(int position, int count) {
                        Log.d("log_print","onInserted: position :  "+position+" count : "+count);
                    }

                    @Override
                    public void onRemoved(int position, int count) {
                        Log.d("log_print","onRemoved: position :  "+position+" count : "+count);
                    }
                });
            }
        });
    }

    public void insertAll(View view) {
        Executors.newSingleThreadExecutor().execute(()->{
            for(int i = 0;i < 10;i++){
                UserDataBaseHelper.getDb(getApplication()).userDao().insertUserAll(getUser());
            }
        });
    }

    private User getUser() {
        User user = new User();
        user.setName( "测试" + new Random().nextInt(100));
        user.setAge(new Random().nextInt(100));
        user.setNickName("abc" + new  Random().nextInt(100));
        return user;
    }
}