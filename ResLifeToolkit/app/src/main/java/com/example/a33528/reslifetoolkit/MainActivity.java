package com.example.a33528.reslifetoolkit;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);

        MySQLiteHelper mDbHelper = new MySQLiteHelper(getApplicationContext());

//        SharedPreferences prefs = getSharedPreferences(PREFS,MODE_PRIVATE);
//        String tmp = prefs.getString("PF 0 raName","Nothing Found");
//        Log.d("FindMePlz", tmp);
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



