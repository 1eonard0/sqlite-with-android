package com.dreadnought.dbtest;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBSQLiteHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "business";

    /**
     * you need pass the context
     * @param context
     */
    public MyDBSQLiteHelper(Context context) {
        //receive the context and the name db. the version is 1
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // I have only one table for the example
        //but de execSQL method can be executed one and another time to create all tables
        String employeeTable =  "CREATE TABLE IF NOT EXISTS employee(\n" +
                                "    id integer PRIMARY KEY AUTOINCREMENT,\n" +
                                "    name text,\n" +
                                "    surname text \n" +
                                ")";
        db.execSQL(employeeTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }




}
