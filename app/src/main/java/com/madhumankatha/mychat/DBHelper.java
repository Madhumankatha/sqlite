package com.madhumankatha.mychat;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABAS= "student.db";
    public static final String TABLE = "student";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "SURNAME";

    @Override
    public void onCreate(SQLiteDatabase db) {
        String q = "CREATE TABLE " + TABLE + "(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                " NAME TEXT," + " SURNAME TEXT);";

        Log.d("TAG", "onCreate: " + q);
        db.execSQL(q);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public DBHelper(@Nullable Context context) {
        super(context, DATABAS, null, 1);
    }

    public boolean insert(String id,String name,String surname){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,surname);

        return db.insert(TABLE,null,contentValues) > 0;
    }

    public String getAllData(){
        StringBuilder stringBuffer = new StringBuilder();
        SQLiteDatabase dbSqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = dbSqLiteDatabase.rawQuery("SELECT * FROM "+ TABLE,null);
        if (cursor.getCount()==0){
            stringBuffer.append("NO DATA");
        }else {
            while (cursor.moveToNext()){
                stringBuffer.append("ID:").append(cursor.getString(0)).append("\n");
                stringBuffer.append("NAME:").append(cursor.getString(1)).append("\n");
                stringBuffer.append("SURNAME:").append(cursor.getString(2)).append("\n");
            }
        }

        return stringBuffer.toString();
    }

    public boolean update(String id,String name,String surname){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues  contentValues = new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,surname);
        sqLiteDatabase.update(TABLE,contentValues,"ID = ?",new String[] {id});

        return true;
    }

    public boolean delete(String id){
        SQLiteDatabase  db = this.getWritableDatabase();
        return db.delete(TABLE,"ID = ?",new String[] {id}) > 0;
    }
}