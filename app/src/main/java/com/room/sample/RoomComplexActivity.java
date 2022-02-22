package com.room.sample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;

import com.google.gson.Gson;
import com.room.sample.dao.ComplexUserDataBaseHelper;
import com.room.sample.databinding.ActivityRoomComplexBinding;
import com.room.sample.entrys.Address;
import com.room.sample.entrys.Book;
import com.room.sample.entrys.ComplexUser;
import com.room.sample.entrys.Ink;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RoomComplexActivity extends AppCompatActivity {
    private ActivityRoomComplexBinding binding;
    private Handler handler = new Handler(Looper.getMainLooper());
    private ExecutorService executor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_room_complex);
        executor = Executors.newSingleThreadExecutor();
    }

    public void query(View view) {
        executor.execute(()->{
            List<ComplexUser> list = ComplexUserDataBaseHelper.getDb(getApplication()).userDao().queryAll();
            Gson gson = new Gson();
            handler.post(()->{
                clear(null);
                for (ComplexUser user:list){
                    binding.tvDisplay.append(gson.toJson(user) + " \n\n");
                }
            });

        });
    }

    public void clear(View view) {
        binding.tvDisplay.setText("");
    }

    public void insert(View view) {
        ComplexUserDataBaseHelper.getDb(getApplication()).userDao().insertUserAll(getComplexUser());
    }

    public void insertAll(View view) {
        int size = 5;
        ComplexUser[] users = new ComplexUser[size];
        ArrayList<ComplexUser> list = new ArrayList<>();
        for(int i = 0; i < size;i ++ ){
            list.add(getComplexUser());
        }
        users = list.toArray(users);
        ComplexUserDataBaseHelper.getDb(getApplication()).userDao().insertUserAll(users);
    }

    public void deleteAllRange(View view) {
        executor.execute(()->{
            List<ComplexUser> list = ComplexUserDataBaseHelper.getDb(getApplication()).userDao().queryAll();
            ComplexUser[] users = new ComplexUser[list.size()];
            list.toArray(users);
            ComplexUserDataBaseHelper.getDb(getApplication()).userDao().delete(users);
        });
    }
    public void deleteAll(View view) {
        ComplexUserDataBaseHelper.getDb(getApplication()).userDao().deleteAll();
    }
    private ComplexUser getComplexUser() {
        ComplexUser user = new ComplexUser();
        Random random = new Random();
        int num = random.nextInt(1000000);
        user.setName("测试" + num + "_" + random.nextInt());
        user.setAge(random.nextInt(100));
        user.setNickName("nickName" + num);
        Address address = new Address("address" + num,"detail addr " + num);
        user.setAddress(address);
        user.setInks(getInks());
        user.setBooks(getBooks());
        return user;
    }

    private ArrayList<Book> getBooks() {
        ArrayList<Book> list = new ArrayList<>();
        for(int i = 0;i < 3 ; i ++){
            Book book = new Book();
            book.name = "book_" + i;
            book.type = "type" + i;
            list.add(book);
        }
        return list;
    }

    private ArrayList<Ink> getInks() {
        ArrayList<Ink> list = new ArrayList<>();
        for(int i = 0;i<5;i++){
            Ink ink = new Ink();
            ink.color = "颜色" + i;
            ink.name = "墨水" + i;
            list.add(ink);
        }
        return list;
    }

}