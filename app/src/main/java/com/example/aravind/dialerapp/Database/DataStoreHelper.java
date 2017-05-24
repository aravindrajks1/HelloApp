package com.example.aravind.dialerapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by aravind on 15-03-2017.
 */

public class DataStoreHelper {

    Context context;
    DataStore dbHelper;
    SQLiteDatabase database;

    public DataStoreHelper(Context context) {
        this.context = context;
        dbHelper = new DataStore(context);
    }

    public void open() {
        try {
            database = dbHelper.getWritableDatabase();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void close() {
        dbHelper.close();
        if (database.isOpen()) {
            database.close();
        }
    }

    //Inserting Data
    public long insertingdata(String Id, String Name, String Email, String Contact) throws Exception {
        open();
        long status = -1;
        try {
            ContentValues values = new ContentValues();
            values.put(DataStore.ID, Id);
            values.put(DataStore.NAME, Name);
            values.put(DataStore.CONTACT, Email);
            values.put(DataStore.EMAIL, Contact);
            return database.insert(DataStore.TABLE_CREATE, null, values);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return status;

    }
   /* public long insertingdata(String Id, String Name, String Email, String Contact) throws Exception {
        open();
        long status = -1;
        SQLiteStatement sqlStmnt = database.compileStatement("insert into " + DataStore.TABLE_CREATE
                + "(" + DataStore.ID + ", "
                + DataStore.NAME + ", "
                + DataStore.EMAIL + ", "
                + DataStore.CONTACT + ", "
                + "values(?, ?, ?, ?,?);");
        sqlStmnt.bindString(1, Id);
        sqlStmnt.bindString(2, Name);
        sqlStmnt.bindString(3, Email);
        sqlStmnt.bindString(4, Contact);
        sqlStmnt.executeInsert();
        sqlStmnt.close();
        close();
        return status;*/
}

