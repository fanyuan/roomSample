package com.room.sample.entrys;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

/**
 * 墨水
 */
@Entity()
public class Ink {
    @ColumnInfo(name = "ink_name")
    public String name;
    public String color;
}
