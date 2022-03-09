package com.room.sample.entrys;

import androidx.room.Entity;
import androidx.room.Index;

@Entity(indices = {@Index(value = {"name"},unique = true)})
public class Book {
    public String name;
    public String type;
}
