package com.example.a33528.reslifetoolkit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class TextInputActivity extends AppCompatActivity {

    Intent inputIntent;
    Intent outputIntent;
    TextView formTitle;
    EditText formBodyET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.general_form);
        inputIntent = getIntent();
        formTitle = (TextView) findViewById(R.id.formTitleTV);
        formTitle.setText(inputIntent.getStringExtra("inputTitle"));
        formBodyET = (EditText) findViewById(R.id.formBodyET);
    }

    public void saveLogEntry(View v)
    {
        outputIntent.putExtra("returnText", formBodyET.getText().toString());
        setResult(RESULT_OK, outputIntent);
        finish();
    }

    public void cancelLogEntry(View v)
    {
        setResult(RESULT_CANCELED, null);
        finish();
    }


}
