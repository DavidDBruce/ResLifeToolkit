package com.example.a33528.reslifetoolkit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class DocumentationActivity extends AppCompatActivity {

    private EditText docMessage;
    private TextView docDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.general_form);
    }
}
