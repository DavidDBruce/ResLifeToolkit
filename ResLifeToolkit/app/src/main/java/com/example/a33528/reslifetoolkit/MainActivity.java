package com.example.a33528.reslifetoolkit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);
    }
    public void programmingForms(View view)
    {
        Intent intent = new Intent(this,ProgrammingListActivity.class);
        startActivity(intent);
    }
    public void dutyLogs(View view)
    {
        Intent intent = new Intent(this,DutyLogList.class);
        startActivity(intent);
    }
}
