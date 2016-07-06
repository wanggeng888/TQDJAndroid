package com.wenc.tltd.utils.database;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.wenc.tltd.entity.Exam;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WenC on 2016-06-12.
 */
public class SQLiteHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1; // 数据库版本
    private SQLiteDatabase db;

    public SQLiteHelper(Context context, String dbName) {
        super(context, dbName, null, DB_VERSION);
        db = super.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        createDB();
    }

    public void createDB() {
        db.execSQL("CREATE TABLE IF NOT EXISTS exam(id INTEGER PRIMARY KEY AUTOINCREMENT, title VARCHAR, option1 VARCHAR, option2 VARCHAR, option3 VARCHAR, option4 VARCHAR, answer int)");
    }

    public void create(List<Exam> examList) {
        db.beginTransaction();
        try {
            for (Exam exam : examList) {
                db.execSQL("INSERT INTO exam(title, option1, option2, option3, option4, answer) VALUES(?,?,?,?,?,?)", new Object[]{exam.getTitle(), exam.getOption1(), exam.getOption2(), exam.getOption3(), exam.getOption4(), exam.getAnswer()});
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    public void delete() {
        db.beginTransaction();
        try {
            db.execSQL("DROP TABLE IF EXISTS exam");
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    public boolean isDBEmpty() {
        createDB();
        Cursor c = db.rawQuery("SELECT count(id) as count FROM exam", null);
        int count = 0;
        c.moveToFirst();
        count = c.getInt(c.getColumnIndex("count"));
        if (count > 0) {
            return false;
        }
        return true;
    }

    public List<Exam> searchExam() {
        createDB();
        Cursor c = db.rawQuery("SELECT * FROM exam", null);
        List<Exam> examList = new ArrayList<Exam>();
        while (c.moveToNext()) {
            Exam exam = new Exam(c.getInt(c.getColumnIndex("id")), c.getString(c.getColumnIndex("title")), c.getString(c.getColumnIndex("option1")), c.getString(c.getColumnIndex("option2")), c.getString(c.getColumnIndex("option3")), c.getString(c.getColumnIndex("option4")));
            examList.add(exam);
        }
        c.close();
        return examList;
    }

    public List<Exam> searchAllExam() {
        createDB();
        Cursor c = db.rawQuery("SELECT * FROM exam", null);
        List<Exam> examList = new ArrayList<Exam>();
        while (c.moveToNext()) {
            Exam exam = new Exam(c.getInt(c.getColumnIndex("id")), c.getString(c.getColumnIndex("title")), c.getString(c.getColumnIndex("option1")), c.getString(c.getColumnIndex("option2")), c.getString(c.getColumnIndex("option3")), c.getString(c.getColumnIndex("option4")), c.getInt(c.getColumnIndex("answer")));
            examList.add(exam);
        }
        c.close();
        return examList;
    }

}
