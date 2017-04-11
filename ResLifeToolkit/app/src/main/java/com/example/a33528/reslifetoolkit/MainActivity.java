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

    ProgrammingForm testForm;
    ProgrammingForm testForm2;
    ProgrammingForm testForm3;

    MySQLiteHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);

        mDbHelper = new MySQLiteHelper(getApplicationContext());
//        mDbHelper.clearProgrammingForms();
//
//        testForm = new ProgrammingForm("David Bruce", "South Complex", "2", "Smash Night", "Fun", "Playing Smash Bros", "Flyers and Emails", "0.0", "12", "yes", true);
//        testForm2 = new ProgrammingForm("Cheyanne Whorton", "South Complex", "2", "Sleep", "Floor needs to learn to sleep.", "Bulletin board with points built in.", "", "0.0", "0", "", false);
//        testForm3 = new ProgrammingForm("Sadie Moore", "South Complex", "3", "Hot Seat", "Fun", "Playing Hot Seat after floor meeting.", "Flyers and Emails", "0.0", "12", "yes", true);
//
//        mDbHelper.addProgrammingForm(testForm);
//        mDbHelper.addProgrammingForm(testForm2);
//        mDbHelper.addProgrammingForm(testForm3);
    }
    public void programmingForms(View view)
    {
        Intent intent = new Intent(this,ProgrammingFormListActivity.class);
        startActivity(intent);
    }
    public void dutyLogs(View view)
    {
        Intent intent = new Intent(this,DutyLogListActivity.class);
        startActivity(intent);
    }
}



