package com.room.sample.entrys;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class BaseListConvert<T> {
    @TypeConverter
    public ArrayList<T> revert(String jsonString) {
        try {
            Type type = new TypeToken<ArrayList<T>>() {}.getType();
            ArrayList<T> list = new Gson().fromJson(jsonString,type);
            return list;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    @TypeConverter
    public String convert(ArrayList<T> items){
        return new Gson().toJson(items);
    }
}
