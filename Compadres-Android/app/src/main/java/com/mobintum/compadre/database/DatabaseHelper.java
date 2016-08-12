package com.mobintum.compadre.database;

import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Rick on 16/05/16.
 * email: ricardo.centeno@mobintum.com
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "compadre.db";
    private static final Integer VER_1 = 1;
    private static final Integer DATABASE_VERSION = VER_1;
    private Context context;


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE City (" +
                "   idCity integer  NOT NULL PRIMARY KEY ," +
                "   name varchar(250)  NOT NULL," +
                "   description text," +
                "   state varchar(250)  NOT NULL," +
                "   latitude double," +
                "   longitude double," +
                "   pathMainPhoto integer  NOT NULL" +
                ");");



        db.execSQL("INSERT INTO City (idCity, name, state, pathMainPhoto) VALUES (1, 'Mexico City','Ciudad de Mexico','img_mexico_city')");
        db.execSQL("INSERT INTO City (idCity, name, state, pathMainPhoto) VALUES (2, 'Guadalajara','Jalisco','img_gdl')");
        db.execSQL("INSERT INTO City (idCity, name, state, pathMainPhoto) VALUES (3, 'Monterrey','Nuevo Leon','img_monterrey')");
        db.execSQL("INSERT INTO City (idCity, name, state, pathMainPhoto) VALUES (4, 'Hermosillo','Sonora','img_hermosillo')");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        if(oldVersion != DATABASE_VERSION){
            db.execSQL("DROP TABLE IF EXIST City");
        }
    }

    public ArrayList<Cursor> getData(String Query){
        //get writable database
        SQLiteDatabase sqlDB = this.getWritableDatabase();
        String[] columns = new String[] { "mesage" };
        //an array list of cursor to save two cursors one has results from the query
        //other cursor stores error message if any errors are triggered
        ArrayList<Cursor> alc = new ArrayList<Cursor>(2);
        MatrixCursor Cursor2= new MatrixCursor(columns);
        alc.add(null);
        alc.add(null);


        try{
            String maxQuery = Query ;
            //execute the query results will be save in Cursor c
            Cursor c = sqlDB.rawQuery(maxQuery, null);


            //add value to cursor2
            Cursor2.addRow(new Object[] { "Success" });

            alc.set(1,Cursor2);
            if (null != c && c.getCount() > 0) {


                alc.set(0,c);
                c.moveToFirst();

                return alc ;
            }
            return alc;
        } catch(SQLException sqlEx){
            Log.d("printing exception", sqlEx.getMessage());
            //if any exceptions are triggered save the error message to cursor an return the arraylist
            Cursor2.addRow(new Object[] { ""+sqlEx.getMessage() });
            alc.set(1,Cursor2);
            return alc;
        } catch(Exception ex){

            Log.d("printing exception", ex.getMessage());

            //if any exceptions are triggered save the error message to cursor an return the arraylist
            Cursor2.addRow(new Object[] { ""+ex.getMessage() });
            alc.set(1,Cursor2);
            return alc;
        }


    }
}
