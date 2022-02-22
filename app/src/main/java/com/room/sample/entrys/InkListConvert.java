package com.room.sample.entrys;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class InkListConvert {
    @TypeConverter
    public ArrayList<Ink> revert(String jsonString) {
        try {
            Type type = new TypeToken<ArrayList<Ink>>() {}.getType();
            ArrayList<Ink> list = new Gson().fromJson(jsonString,type);
            return list;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    @TypeConverter
    public String convert(ArrayList<Ink> inks){
        return new Gson().toJson(inks);
    }
}
