package com.example.a33528.reslifetoolkit;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    public static final String PREFS = "prefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);

        SharedPreferences prefs = getSharedPreferences(PREFS,MODE_PRIVATE);
        String tmp = prefs.getString("PF  raName","Nothing Found");
        Log.d("FindMePlz", tmp);
    }
    public void programmingForms(View view)
    {
        Intent intent = new Intent(this,ProgrammingListActivity.class);
        startActivity(intent);
    }
    public void dutyLogs(View view)
    {
        Intent intent = new Intent(this,DutyLogListActivity.class);
        startActivity(intent);
    }
}
