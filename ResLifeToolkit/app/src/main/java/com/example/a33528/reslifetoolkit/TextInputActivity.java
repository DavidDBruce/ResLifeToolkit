package com.example.a33528.reslifetoolkit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class TextInputActivity extends AppCompatActivity {

    Intent inputIntent;
    TextView formTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.general_form);
        inputIntent = getIntent();
        formTitle = (TextView) findViewById(R.id.formTitleTV);
        formTitle.setText(inputIntent.getStringExtra("inputTitle"));
    }
}
