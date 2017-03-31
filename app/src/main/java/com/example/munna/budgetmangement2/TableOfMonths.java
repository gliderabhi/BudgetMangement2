package com.example.munna.budgetmangement2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Munna on 3/1/2017.
 */

public class TableOfMonths extends SQLiteOpenHelper {

    public static  final String DATABASE_NAME="TableList";
    public static  final String TABLE_NAME="TableList";

    Context ctontext;
    int i=0;

    public TableOfMonths(Context ctx){
        super(ctx,DATABASE_NAME,null,1);
        ctontext=ctx;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try{
        db.execSQL("create table "+ TABLE_NAME+ " (_id int AUTO_INCREMENT,TableName String,Initial_Balance int,PresentValue int)");
    }catch (Exception e){
            Toast.makeText(ctontext,e.getMessage().toString(),Toast.LENGTH_LONG).show();

        }
    }


    public boolean tableCreate(String tableName,int balance){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("TableName",tableName);
        cv.put("Initial_Balance",balance);
        cv.put("PresentValue",1);
        changePresentTableToOnline(tableName);

        DbHelper db=new DbHelper(ctontext);
        db.addTable(tableName);

        sqLiteDatabase.insert(TABLE_NAME,null,cv);
        return true;
    }

    private void changePresentTableToOnline(String table) {
        SQLiteDatabase sq=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("PresentValue",0);
        String where="TableName!= "+ table;
        try {
            sq.update(TABLE_NAME,cv,where,null);
        }catch (Exception e){
            AlertDialog.Builder bd=new AlertDialog.Builder(ctontext);
            bd.setTitle("Error");
            bd.setMessage(e.getMessage().toString());
            bd.create();
            bd.show();
         //Toast.makeText(ctontext,e.getMessage().toString(),Toast.LENGTH_LONG).show();
        }

    }
    public ArrayList<String> TableName(){

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

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
