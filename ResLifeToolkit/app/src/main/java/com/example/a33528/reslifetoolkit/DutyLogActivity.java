package com.example.a33528.reslifetoolkit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class DutyLogActivity extends AppCompatActivity {

    private TextView dutyLogTitle;
    private EditText raOnDuty;
    private DutyLog inputLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.duty_log);

        dutyLogTitle = (TextView) findViewById(R.id.dutyLogDateTV);
        raOnDuty = (EditText) findViewById(R.id.raOnDutyET);
     }

    public void eightRound(View v)
    {
        Log.d("FindMePlz", "Button Pushed");
    }

    public void tenRound(View v)
    {
        Log.d("FindMePlz", "Button Pushed");
    }

    public void twelveRound(View v)
    {
        Log.d("FindMePlz", "Button Pushed");
    }

    public void twoRound(View v)
    {
        Log.d("FindMePlz", "Button Pushed");
    }

    public void dayRound(View v)
    {
        Log.d("FindMePlz", "Button Pushed");
    }

    public void launchDoc(View v)
    {
        Log.d("FindMePlz", "Button Pushed");
    }

    public void launchWO(View v)
    {
        Log.d("FindMePlz", "Button Pushed");
    }



}
