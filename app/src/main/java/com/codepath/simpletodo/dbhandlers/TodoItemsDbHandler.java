package com.codepath.simpletodo.dbhandlers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.codepath.simpletodo.models.TodoItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nedim on 9/25/16.
 */
public class TodoItemsDbHandler extends DatabaseHandler {

    public TodoItemsDbHandler(Context context) {
        super(context);
    }

    public boolean create(TodoItem item) {

        ContentValues values = new ContentValues();

        values.put("text", item.text);
        values.put("position", item.position);

        SQLiteDatabase db = this.getWritableDatabase();

        boolean success = db.insert(TABLE_TODO_ITEMS, null, values) > 0;
        db.close();

        return success;
    }

    public List<TodoItem> read() {

        List<TodoItem> recordsList = new ArrayList<TodoItem>();

        String sql = "SELECT * FROM " + TABLE_TODO_ITEMS + " ORDER BY id DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {
                int id = Integer.parseInt(cursor.getString(cursor.getColumnIndex("id")));
                String text = cursor.getString(cursor.getColumnIndex("text"));
                int position = Integer.parseInt(cursor.getString(cursor.getColumnIndex("position")));

                TodoItem item = new TodoItem();
                item.id = id;
                item.text = text;
                item.position = position;

                recordsList.add(item);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return recordsList;
    }

    public boolean update(TodoItem item) {

        ContentValues values = new ContentValues();

        values.put("text", item.text);

        String where = "id = ?";

        String[] whereArgs = { Integer.toString(item.id) };

        SQLiteDatabase db = this.getWritableDatabase();

        boolean success = db.update(TABLE_TODO_ITEMS, values, where, whereArgs) > 0;
        db.close();

        return success;
    }

    public boolean delete(int id) {

        SQLiteDatabase db = this.getWritableDatabase();
        boolean success = db.delete(TABLE_TODO_ITEMS, "id ='" + id + "'", null) > 0;
        db.close();

        return success;
    }
}
