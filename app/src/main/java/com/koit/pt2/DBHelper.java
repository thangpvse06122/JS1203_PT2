package com.koit.pt2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String create_category= "create table Category(" +
                "id integer primary key autoincrement," +
                "name text)";

        String create_product = "create table Product(" +
                "id integer primary key autoincrement," +
                "name text," +
                "price float," +
                "id_cat integer)";

        sqLiteDatabase.execSQL(create_category);
        sqLiteDatabase.execSQL(create_product);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        if(i1 > i){
            sqLiteDatabase.execSQL("drop table Category");
            sqLiteDatabase.execSQL("drop table Product");
            onCreate(sqLiteDatabase);
        }

    }
}
