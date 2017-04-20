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
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    MySQLiteHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);

        mDbHelper = new MySQLiteHelper(getApplicationContext());
    }
    public void programmingForms(View v)
    {
        Intent intent = new Intent(this,ProgrammingFormListActivity.class);
        startActivity(intent);
    }
    public void dutyLogs(View v)
    {
        Intent intent = new Intent(this,DutyLogListActivity.class);
        startActivity(intent);
    }

    public void handBook(View v)
    {
        try {
            Intent intent = getPackageManager().getLaunchIntentForPackage("com.instructure.candroid");
            startActivity(intent);
        }
        catch(Exception e)
        {
            Toast.makeText(this,"You need to install the Canvas app.", Toast.LENGTH_SHORT).show();
        }
    }
}



