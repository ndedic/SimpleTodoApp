package com.codepath.simpletodo.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.simpletodo.R;
import com.codepath.simpletodo.models.TodoItem;

import java.util.List;

/**
 * Created by nedim on 9/25/16.
 */
public class TodoItemAdapter extends ArrayAdapter<TodoItem> {

    public Context context;
    public int layoutResourceId;
    public List<TodoItem> data = null;

    public TodoItemAdapter(Context context, int layoutResourceId, List<TodoItem> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        TodoItemHolder holder = null;

        if (row == null) {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new TodoItemHolder();
            holder.txtText = (TextView)row.findViewById(R.id.txtText);

            row.setTag(holder);
        } else {
            holder = (TodoItemHolder)row.getTag();
        }

        TodoItem item = data.get(position);
        holder.txtText.setText(item.text);

        return row;
    }

    static class TodoItemHolder {
        TextView txtText;
    }
}

