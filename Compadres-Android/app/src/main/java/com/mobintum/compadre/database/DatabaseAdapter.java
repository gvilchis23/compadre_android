package com.mobintum.compadre.database;

import android.database.sqlite.SQLiteDatabase;
import com.mobintum.compadre.application.AppController;

public class DatabaseAdapter {

    private static DatabaseHelper mDbHelper;
    private static SQLiteDatabase mDb;

    public static boolean openDB(){
        if(mDbHelper != null)
            mDbHelper.close();
        mDbHelper = new DatabaseHelper(AppController.getInstance().getApplicationContext());

        try {
            mDb = mDbHelper.getWritableDatabase();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public static SQLiteDatabase getDB(){

        if(mDb == null)
            openDB();

        return mDb;

    }

}



