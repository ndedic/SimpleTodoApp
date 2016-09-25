package com.codepath.simpletodo.dbhandlers;

/**
 * Created by nedim on 9/25/16.
 */
public class TodoItemsDbHandler {
    private static TodoItemsDbHandler ourInstance = new TodoItemsDbHandler();

    public static TodoItemsDbHandler getInstance() {
        return ourInstance;
    }

    private TodoItemsDbHandler() {
    }
}
