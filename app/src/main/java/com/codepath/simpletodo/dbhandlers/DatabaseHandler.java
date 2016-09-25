package com.codepath.simpletodo.dbhandlers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by nedim on 9/25/16.
 */
abstract public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    protected static final String DATABASE_NAME = "simple_todo_app";
    protected static final String TABLE_TODO_ITEMS = "todo_items";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE " + TABLE_TODO_ITEMS +
                " ( id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "text TEXT, " +
                "position INTEGER ) ";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String sql = "DROP TABLE IF EXISTS " + TABLE_TODO_ITEMS;
        db.execSQL(sql);

        onCreate(db);
    }
}
