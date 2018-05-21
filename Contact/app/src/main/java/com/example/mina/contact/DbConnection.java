package com.example.mina.contact;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/*
    Data base connection class extends sqliteHelper
 */
public class DbConnection extends SQLiteOpenHelper {

    public final static String DbName = "contacts.db";
    public final static int version = 1;

    public DbConnection(Context context) {


        super(context, DbName, null, version);
    }

    /*
              create database schema
              create table users(name,phone,mail)
     */
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table if not exists users(name TEXT primary key,phone TEXT,mail TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        /*
             drop table users if the database version is changed
         */
        db.execSQL("drop table if exists users ");

        onCreate(db);
    }

    /*
       insert data to table users(name,phone, mail)
     */
    public void InsertRowUser(String name, String phone, String Email) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("phone", phone);
        contentValues.put("mail", Email);
        db.insert("users", null, contentValues);
    }

    /*
    delete the table record from data base
     */
    public  void delete(String name){

        SQLiteDatabase database=this.getWritableDatabase();
        database.execSQL("delete from users where name='"+name+"'");
    }

    /*
    get users data from data base
     */
    public ArrayList getAllRecords(){

        ArrayList<listObject> arr = new ArrayList();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from users", null);
        res.moveToFirst();
        while (res.isAfterLast() == false){

            arr.add(new listObject(res.getString(res.getColumnIndex("phone")),
                    res.getString(res.getColumnIndex("name")),
                    res.getString(res.getColumnIndex("mail"))));
            res.moveToNext();
        }
        return arr;
    }
}
