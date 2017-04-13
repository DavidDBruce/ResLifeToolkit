package com.example.a33528.reslifetoolkit;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Locale;

public class DutyLogActivity extends AppCompatActivity {

    private Intent inputIntent;
    private DutyLog inputLog;
    private TextView dutyLogTitle;
    private EditText raOnDuty;

    private DatePickerDialog.OnDateSetListener date;
    private Calendar myCalendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.duty_log);

        inputIntent = getIntent();
        inputLog = (DutyLog) inputIntent.getSerializableExtra("logSelected");
        dutyLogTitle = (TextView) findViewById(R.id.dutyLogDateTV);
        raOnDuty = (EditText) findViewById(R.id.raOnDutyET);

        dutyLogTitle.setText("Duty Log for " + inputLog.getLogDate());

        date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateDateLabel();
            }

        };

        dutyLogTitle.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(DutyLogActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        raOnDuty.setText(inputLog.getRaOnDuty());
     }

     private void updateDateLabel()
     {
         String myFormat = "MM/dd/yy";
         SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

         dutyLogTitle.setText("Duty Log for" + sdf.format(myCalendar.getTime()));
     }

    public void eightRound(View v)
    {
        Intent intent = new Intent(getApplicationContext(), TextInputActivity.class);
        intent.putExtra("inputTitle","8 O'Clock Rounds");
        startActivityForResult(intent, 50);
    }

    public void tenRound(View v)
    {
        Intent intent = new Intent(getApplicationContext(), TextInputActivity.class);
        intent.putExtra("inputTitle","10 O'Clock Rounds");
        startActivityForResult(intent, 51);
    }

    public void twelveRound(View v)
    {
        Intent intent = new Intent(getApplicationContext(), TextInputActivity.class);
        intent.putExtra("inputTitle","12 O'Clock Rounds");
        startActivityForResult(intent, 52);
    }

    public void twoRound(View v)
    {
        Intent intent = new Intent(getApplicationContext(), TextInputActivity.class);
        intent.putExtra("inputTitle","2 O'Clock Rounds");
        startActivityForResult(intent, 53);
    }

    public void dayRound(View v)
    {
        Intent intent = new Intent(getApplicationContext(), TextInputActivity.class);
        intent.putExtra("inputTitle","Daytime Rounds");
        startActivityForResult(intent, 54);
    }

    public void launchDoc(View v)
    {
        Intent intent = new Intent(getApplicationContext(), TextInputActivity.class);
        intent.putExtra("inputTitle","Documentations");
        startActivityForResult(intent, 55);
    }

    public void launchWO(View v)
    {
        Intent intent = new Intent(getApplicationContext(), TextInputActivity.class);
        intent.putExtra("inputTitle","Work Orders");
        startActivityForResult(intent, 56);
    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data)
//    {
//        if(requestCode == 42 && resultCode == RESULT_OK && data != null) {
//            DutyLog returnLog = (DutyLog) data.getSerializableExtra("returnLog");
//            mDbHelper.updateDutyLog(returnLog);
//            logList = mDbHelper.getAllDutyLogs();
//            dutyLogLA.clear();
//            dutyLogLA.addAll(logList);
//            dutyLogLA.notifyDataSetChanged();
//        }
//        else if(requestCode == 42 && resultCode == RESULT_CANCELED && data != null) {
//            DutyLog returnLog = (DutyLog) data.getSerializableExtra("returnLog");
//            mDbHelper.deleteDutyLog(returnLog);
//            logList = mDbHelper.getAllDutyLogs();
//            dutyLogLA.clear();
//            dutyLogLA.addAll(logList);
//            dutyLogLA.notifyDataSetChanged();
//        }
//        else {
//            if(logList.get(0).getDocumentations().equals("") && logList.get(0).getLogDate().equals("")) {
//                mDbHelper.deleteDutyLog(logList.get(0));
//            }
//            dutyLogLA.notifyDataSetChanged();
//        }
//    }
}
