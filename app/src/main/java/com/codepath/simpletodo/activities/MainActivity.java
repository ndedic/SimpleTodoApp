package com.codepath.simpletodo.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.codepath.simpletodo.R;
import com.codepath.simpletodo.adapters.TodoItemAdapter;
import com.codepath.simpletodo.dbhandlers.TodoItemsDbHandler;
import com.codepath.simpletodo.models.TodoItem;

public class MainActivity extends AppCompatActivity {

    ListView lvItems;
    TodoItemsDbHandler dbHandler;

    private final int REQUEST_CODE = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHandler = new TodoItemsDbHandler(this);
        lvItems = (ListView) findViewById(R.id.lvItems);

        resetListView();
        setupListViewListener();
    }

    public void onAddItem(View v) {
        EditText etNewItem = (EditText) findViewById(R.id.etNewItem);
        String itemText = etNewItem.getText().toString().trim();

        if (itemText.isEmpty()) {
            return;
        }

        TodoItem item = new TodoItem();
        item.text = itemText;

        dbHandler.create(item);

        etNewItem.setText("");
        resetListView();
    }

    private void setupListViewListener() {
        lvItems.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapter, View view, int pos, long id) {
                        TodoItem item = (TodoItem) lvItems.getItemAtPosition(pos);
                        dbHandler.delete(item.id);
                        resetListView();
                        return true;
                    }
                }
        );

        lvItems.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapter, View view, int pos, long arg) {
                        Intent i = new Intent(MainActivity.this, EditItemActivity.class);
                        TodoItem item = (TodoItem) lvItems.getItemAtPosition(pos);
                        i.putExtra("text", item.text);
                        i.putExtra("id", item.id);
                        startActivityForResult(i, REQUEST_CODE);
                    }
                }
        );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent i) {
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
            TodoItem item = new TodoItem();
            item.text = i.getStringExtra("text");
            item.id = i.getIntExtra("id", 0);
            dbHandler.update(item);
            resetListView();
        }
    }

    private void resetListView() {
        TodoItemAdapter adapter = new TodoItemAdapter(this, R.layout.listview_item_row, dbHandler.read());
        lvItems.setAdapter(adapter);
    }
}
