package com.example.list3.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity
public class User {

    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "idCategory")
    public String idCategory;

    @ColumnInfo(name = "strCategory")
    public String strCategory;

    @ColumnInfo(name = "strCategoryThumb")
    public String strCategoryThumb;

    @ColumnInfo(name = "strCategoryDescription")
    public String strCategoryDescription;
}
