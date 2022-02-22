package com.room.sample.entrys;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.TypeConverters;

@Entity
public class Address {
    @ColumnInfo(name = "addr_name")
    public String name;
    public String addr;

    public Address(String name, String addr) {
        this.name = name;
        this.addr = addr;
    }
}
