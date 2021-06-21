package com.example.asm4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DAOTeacher extends SQLiteOpenHelper {
    //Khai bao nhung ten ma su dung nhieu lan
    public static final String TEACHER_TABLE = "TEACHER_TABLE";
    public static final String COLUMN_TEACHER_NAME = "TEACHER_NAME";
    public static final String COLUMN_TEACHER_IMAGE = "TEACHER_IMAGE";
    public static final String COLUMN_ID = "ID";

    //tao constructor DAOTeacher
    public DAOTeacher(@Nullable Context context) {
        super(context, "Teacher.db", null, 1);
    }

    //Noi ma ta tao bang trong day em
    @Override
    public void onCreate(SQLiteDatabase db) {
       // tao bang voi ten TEACHER_TABLE va cot gom co ID ,TEACHER_NAME,TEACHER_IMAGE voi id la khoa chinh va tu dong tang
        String createTableStatement = "CREATE TABLE " + TEACHER_TABLE + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_TEACHER_NAME + " TEXT, " + COLUMN_TEACHER_IMAGE + " TEXT)";
        db.execSQL(createTableStatement);
    }
    //khi update database version thi xoa du lieu
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TEACHER_TABLE);
        onCreate(db);

    }
    //Them teacher vao sqlite
    public boolean insertTeacher(Teacher teacher) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();//tao ContentValues de luu du lieu vao cot trong bang
        contentValues.put(COLUMN_TEACHER_NAME, teacher.getName());
        contentValues.put(COLUMN_TEACHER_IMAGE, teacher.getImg());
        //them teacher moi teacher vao sqlite
        long insert = db.insert(TEACHER_TABLE, null, contentValues);
        if (insert == -1) {
            return false;
        } else return true;
    }
    //update ten va hinh teacher
    public boolean updateTeacher(String id, Teacher teacher) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();//tao ContentValues de luu du lieu vao cot trong bang
        contentValues.put(COLUMN_TEACHER_NAME, teacher.getName());
        contentValues.put(COLUMN_TEACHER_IMAGE, teacher.getImg());
        //update thong tin teacher dua vao id
        sqLiteDatabase.update(TEACHER_TABLE, contentValues, COLUMN_ID + " = ?", new String[]{id});
        return true;
    }
    //xoa thong tin teacher trong sqlite
    public void deleteTeacher(String id) {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(TEACHER_TABLE, COLUMN_ID + " = ?", new String[]{id});

    }
    //lay toan bo thong tin teacher trong sqlite
    public List<Teacher> getAllTeacher() {
        List<Teacher> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " + TEACHER_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);
        if (cursor.moveToFirst()) {
            do {
                String id = cursor.getString(0);
                String teacherName = cursor.getString(1);
                String teacherImage = cursor.getString(2);
                Teacher teacher = new Teacher(id, teacherName, teacherImage);
                returnList.add(teacher);
            } while (cursor.moveToNext());//neu con sqlite con thong tin teacher thi them vao list teacher
        } else {

        }
        return returnList;//tra ve danh sach teacher trong sqlite
    }

}
