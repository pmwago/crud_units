package com.example.crud_units;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(Context context) {
        super(context, "Semester.db", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create Table Units(Unit_code Text primary key,Unit_name Text,Semester_stage Float,Year Text,Lecturer Text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists Units");
    }

    //add methods to perform crud operations

    public Boolean insertData(String Unit_code,String Unit_name,Float Semester_stage,String Year,String Lecturer){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("Unit_code",Unit_code);
        cv.put("Unit_name",Unit_name);
        cv.put("Semester_stage",Semester_stage);
        cv.put("Year",Year);
        cv.put("Lecturer",Lecturer);

        long result=db.insert("Units",null,cv);

        if(result==-1){
            return false;
        }
        else{
            return true;
        }
    }
    public Boolean updateData(String Unit_code,String Unit_name,Float Semester_stage,String Year,String Lecturer){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();

        cv.put("Unit_name",Unit_name);
        cv.put("Semester_stage",Semester_stage);
        cv.put("Year",Year);
        cv.put("Lecturer",Lecturer);

        Cursor cursor=db.rawQuery("Select * from Units where Unit_code=?",new String[] {Unit_code});
        if (cursor.getCount()>0){
            long result=db.update("Units",cv,"Unit_code=?",new String[] {Unit_code});
            if(result==-1){
                return false;
            }
            else{
                return true;
            }
        }
        else{
            return false;
        }
    }
    public Cursor readData(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("Select * from Units",null);
        return cursor;
    }

    public Boolean deleteData(String Unit_code){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor= db.rawQuery("delete from Units where Unit_code=?",new String[] {Unit_code});
        if (cursor.getCount()>0){
            long result= db.delete("Units","Unit_code=?",new String[] {Unit_code});
            if (result==-1){
                return false;
            }
            else {
                return true;
            }
        }
        else{
            return false;
        }

    }
}
