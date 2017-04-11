package com.example.a33528.reslifetoolkit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class DutyLogActivity extends AppCompatActivity {

    private Intent inputIntent;
    private DutyLog inputLog;
    private TextView dutyLogTitle;
    private EditText raOnDuty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.duty_log);

        inputIntent = getIntent();
        inputLog = (DutyLog) inputIntent.getSerializableExtra("logSelected");
        dutyLogTitle = (TextView) findViewById(R.id.dutyLogDateTV);
        raOnDuty = (EditText) findViewById(R.id.raOnDutyET);

        dutyLogTitle.setText("Duty Log for " + inputLog.getLogDate());
        raOnDuty.setText(inputLog.getRaOnDuty());
     }

    public void eightRound(View v)
    {
        Intent intent = new Intent(getApplicationContext(), TextInputActivity.class);
        startActivityForResult(intent, 50);
    }

    public void tenRound(View v)
    {
        Intent intent = new Intent(getApplicationContext(), TextInputActivity.class);
        startActivityForResult(intent, 51);
    }

    public void twelveRound(View v)
    {
        Intent intent = new Intent(getApplicationContext(), TextInputActivity.class);
        startActivityForResult(intent, 52);
    }

    public void twoRound(View v)
    {
        Intent intent = new Intent(getApplicationContext(), TextInputActivity.class);
        startActivityForResult(intent, 53);
    }

    public void dayRound(View v)
    {
        Intent intent = new Intent(getApplicationContext(), TextInputActivity.class);
        startActivityForResult(intent, 54);
    }

    public void launchDoc(View v)
    {
        Intent intent = new Intent(getApplicationContext(), TextInputActivity.class);
        startActivityForResult(intent, 55);
    }

    public void launchWO(View v)
    {
        Intent intent = new Intent(getApplicationContext(), TextInputActivity.class);
        startActivityForResult(intent, 56);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {

    }
}
