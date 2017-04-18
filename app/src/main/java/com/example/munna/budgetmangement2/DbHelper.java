package com.example.munna.budgetmangement2;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;


public class DbHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "MonthlyManagement.db";

    String TABLE_NAME="FirstTable";


    Context ctx;
    int i=0;

    public DbHelper(Context context) {

        super(context, DATABASE_NAME, null, 1);
        ctx=context;
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table "+TABLE_NAME + "(Category String," +
                                                             " Description String," +
                                                              " Money_Spent int," +
                                                              " DateTime String)");
    }



    public boolean insertExpenditure(String selectedValue, String desc, String date,int spent) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Category",selectedValue);
        contentValues.put("Description",desc);
        contentValues.put("Money_Spent",spent );
        contentValues.put("DateTime",date);
        db.insert(TABLE_NAME, null, contentValues);
        return  true;
    }
public int checkTable(){
    SQLiteDatabase db = this.getWritableDatabase();
    String count = "SELECT count(*) FROM "+TABLE_NAME;
    Cursor mcursor = db.rawQuery(count, null);
    mcursor.moveToFirst();
    int icount = mcursor.getInt(0);
    if(icount>0){
        return 0;
    }
    else{return 1;
     }
}
    public Cursor TotalExpenses(){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor res=db.rawQuery("Select sum(Money_Spent) from "+TABLE_NAME,null);
        res.moveToFirst();
        return res;

    }
    public ArrayList<Integer> TotalExpense(){

        String selectQuery = "SELECT Money_Spent FROM " + TABLE_NAME;
        SQLiteDatabase db  = this.getReadableDatabase();
        Cursor cursor      = db.rawQuery(selectQuery, null);
        ArrayList<Integer> data=new ArrayList<Integer>();
        if (cursor.moveToFirst()) {
            do {
                data.add(cursor.getInt(0));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return data;
    }
    public ArrayList<String> description(){

        String selectQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db  = this.getReadableDatabase();
        Cursor cursor      = db.rawQuery(selectQuery, null);
        ArrayList<String> data=new ArrayList<String>();
        if (cursor.moveToFirst()) {
            do {
                data.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return data;
    }
    public ArrayList<Integer> Values(){

        String selectQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db  = this.getReadableDatabase();
        Cursor cursor      = db.rawQuery(selectQuery, null);
        ArrayList<Integer> data=new ArrayList<Integer>();
        if (cursor.moveToFirst()) {
            do {
                data.add(cursor.getInt(2));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return data;
    }
    public ArrayList<String> DateTime(){

        String selectQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db  = this.getReadableDatabase();
        Cursor cursor      = db.rawQuery(selectQuery, null);
        ArrayList<String> data=new ArrayList<String>();
        if (cursor.moveToFirst()) {
            do {
                data.add(cursor.getString(3));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return data;
    }
    public ArrayList<String> Categories(){

        String selectQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db  = this.getReadableDatabase();
        Cursor cursor      = db.rawQuery(selectQuery, null);
        ArrayList<String> data=new ArrayList<String>();
        if (cursor.moveToFirst()) {
            do {
                data.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return data;
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
