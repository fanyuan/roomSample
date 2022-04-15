package com.room.sample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.room.sample.dao.ComplexUserDataBaseHelper;
import com.room.sample.dao.UserDao;
import com.room.sample.dao.UserDataBase;
import com.room.sample.databinding.ActivityRemoveOldItemBinding;
import com.room.sample.entrys.ComplexUser;
import com.room.sample.entrys.User;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RemoveOldItemActivity extends AppCompatActivity {
    private ExecutorService executor;
    private ActivityRemoveOldItemBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_remove_old_item);
        executor = Executors.newSingleThreadExecutor();
    }

    public void query(View view){
        executor.execute(()->{
            List<User> list = UserDataBase.getInstance(this).userDao().queryAll();
            Gson gson = new Gson();
            runOnUiThread(()->{
                clear(null);
                for (User user:list){
                    binding.tvDisplay.append(gson.toJson(user) + " \n\n");
                }
            });
        });
    }
    public void queryAllDesc(View view) {
        executor.execute(()->{
            List<User> list = UserDataBase.getInstance(this).userDao().queryAllDesc();
            Gson gson = new Gson();
            runOnUiThread(()->{
                clear(null);
                for (User user:list){
                    binding.tvDisplay.append(gson.toJson(user) + " \n\n");
                }
            });
        });
    }
    public void clear(View view) {
        binding.tvDisplay.setText("");
    }
    public void getBaseId(View view) {
        int id = UserDataBase.getInstance(this).userDao().topIdBySize(6);
        //int size = UserDataBase.getInstance(this).userDao().size();  + " size = " + size
        Toast.makeText(this,"baseId = " + id ,Toast.LENGTH_SHORT).show();
    }

    public void removeOldData(View view) {
        UserDao dao = UserDataBase.getInstance(this).userDao();
        int id = dao.topIdBySize(6);
        dao.deleteBeforeId(id);
    }

    public void insertAll(View view) {
        int size = 10;
        User[] arr = new User[size];
        for (int i = 0;i < size;i++){
            arr[i] = getUser();
        }
        UserDataBase.getInstance(this).userDao().insertUserAll(arr);
    }
    private User getUser() {
        User user = new User();
        user.setName("测试" + new Random().nextInt(100));
        user.setAge(new Random().nextInt(100));
        user.setNickName("NickName" + new Random().nextInt(100));
        return user;
    }
}