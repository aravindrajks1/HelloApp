package com.example.aravind.dialerapp.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by aravind on 15-03-2017.
 */

public class DataStore extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "DialerApp.db";
    public static final int DATABASE_VERSION = 1;


    public static final String TABLE_NAME = "Userdetails";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String EMAIL = "email";
    public static final String CONTACT = "Contact";
    public static final String TABLE_CREATE = "CREATE TABLE Userdetails (\n" +
            "    id    INTEGER PRIMARY KEY\n" +
            "                  NOT NULL,\n" +
            "    Name  TEXT,\n" +
            "    Phone NUMERIC UNIQUE,\n" +
            "    Email STRING  UNIQUE\n" +
            ");";

    public DataStore(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.i("DatabasePath : ", context.getDatabasePath(DATABASE_NAME).toString());
        Log.i("DatabasePath File : ", context.getDatabasePath("UserInfo.db").getPath());
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DataStore.TABLE_CREATE);
        Log.i("CREATE_TABLE Query : ", TABLE_CREATE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF NOT EXISTS " + TABLE_NAME);
    }
}
