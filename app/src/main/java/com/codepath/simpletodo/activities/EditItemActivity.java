package com.codepath.simpletodo.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.codepath.simpletodo.R;

public class EditItemActivity extends AppCompatActivity {

    EditText editText;
    String text;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);
        text = getIntent().getStringExtra("text");
        id = getIntent().getIntExtra("id", 0);
        editText = (EditText) findViewById(R.id.editText);
        populateForm();
    }

    private void populateForm() {
        editText.setText(text);
        editText.setSelection(editText.getText().length());
    }

    public void onSave(View v) {
        Intent i = new Intent();
        i.putExtra("text", editText.getText().toString());
        i.putExtra("id", id);
        setResult(RESULT_OK, i);
        finish();
    }
}
