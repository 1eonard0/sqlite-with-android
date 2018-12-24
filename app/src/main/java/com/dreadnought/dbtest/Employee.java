package com.dreadnought.dbtest;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Employee {
    //the model class
    private int id;
    private String name;
    private String surname;

    public Employee(int id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    public static boolean addEmployee(String employee_name, String employee_lastName, SQLiteDatabase sqlitedb){
        //We received the emplyee name, surname and a SQLITEDATABASE from the method getWritabledatabase in the mainActivity.
        //Then we Insert a new employee
        SQLiteDatabase sqLiteDatabase = sqlitedb;

        ContentValues contentValues = new ContentValues();
        contentValues.put("name",employee_name);
        contentValues.put("surname",employee_lastName);

        long resultSet = sqLiteDatabase.insert("employee",null,contentValues);

        //depende of the number returned from insert method we return true or false.
        if (resultSet == -1){
            return false;
        }

        return true;
    }

    public static Cursor getAllEmployee(SQLiteDatabase sqlitedb){

        SQLiteDatabase sqLiteDatabase = sqlitedb;//getReadableDatabase();
        return sqLiteDatabase.rawQuery("select \n" +
                "    id,\n" +
                "    name,\n" +
                "    surname \n" +
                "from\n" +
                "    employee", null);

    }

    public static boolean updateEmployee(int id, String employee_name, String employee_lastName,SQLiteDatabase sqlitedb){
        //update employee passing your id in the clause where
        SQLiteDatabase sqLiteDatabase = sqlitedb;//getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",employee_name);
        contentValues.put("surname",employee_lastName);
        return sqLiteDatabase.update("employee",contentValues,"id=?", new String[]{String.valueOf(id)}) > 0;
    }

    public static boolean deleteEmployee(int id, SQLiteDatabase sqlitedb){
        SQLiteDatabase sqLiteDatabase = sqlitedb;//getWritableDatabase();
        return sqLiteDatabase.delete("employee","id=?", new String[]{String.valueOf(id)}) > 0;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }
}
