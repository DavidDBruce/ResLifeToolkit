package com.example.a33528.reslifetoolkit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class DutyLogActivity extends AppCompatActivity {

    private Intent inputIntent;
    private Intent outputIntent = new Intent();
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

        dutyLogTitle.setText("Duty Log for " + inputLog.getCreateDate());
        raOnDuty.setText(inputLog.getRaOnDuty());
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
        Intent intent = new Intent(getApplicationContext(), DocumentationListActivity.class);
        intent.putExtra("inputDocList", inputLog.getDocumentations());
        startActivityForResult(intent, 44);
    }

    public void launchWO(View v)
    {
        Intent intent = new Intent(getApplicationContext(), DocumentationListActivity.class);
        intent.putExtra("inputDocList", inputLog.getWorkOrders());
        startActivityForResult(intent, 44);
    }
}
