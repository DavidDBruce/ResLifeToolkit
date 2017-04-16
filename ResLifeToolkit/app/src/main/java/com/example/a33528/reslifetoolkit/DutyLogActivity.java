package com.example.a33528.reslifetoolkit;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
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
    private Intent outputIntent = new Intent();
    private DutyLog inputLog;
    private TextView dutyLogTitle;
    private EditText raOnDuty;
    private String logDateOnUpdate = "";
    MySQLiteHelper mDbHelper;

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
        mDbHelper = new MySQLiteHelper(getApplicationContext());

        if(inputLog.getLogDate().equals("")) {
            dutyLogTitle.setText("Tap here to set date.");
        }
        else{
            dutyLogTitle.setText("Duty Log for " + inputLog.getLogDate());
        }

        date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateDateLabel();
            }

        };

        dutyLogTitle.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new DatePickerDialog(DutyLogActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        raOnDuty.setText(inputLog.getRaOnDuty());
     }

    private void updateDateLabel() {
         String myFormat = "MM/dd/yy";
         SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

         logDateOnUpdate = sdf.format(myCalendar.getTime());
         dutyLogTitle.setText("Duty Log for " + logDateOnUpdate);

     }

    public void eightRound(View v) {
        Intent intent = new Intent(getApplicationContext(), TextInputActivity.class);
        intent.putExtra("inputTitle","8 O'Clock Rounds");
        intent.putExtra("inputText",inputLog.getRound8());
        startActivityForResult(intent, 50);
    }

    public void tenRound(View v) {
        Intent intent = new Intent(getApplicationContext(), TextInputActivity.class);
        intent.putExtra("inputTitle","10 O'Clock Rounds");
        intent.putExtra("inputText",inputLog.getRound10());
        startActivityForResult(intent, 51);
    }

    public void twelveRound(View v) {
        Intent intent = new Intent(getApplicationContext(), TextInputActivity.class);
        intent.putExtra("inputTitle","12 O'Clock Rounds");
        intent.putExtra("inputText",inputLog.getRound12());
        startActivityForResult(intent, 52);
    }

    public void twoRound(View v) {
        Intent intent = new Intent(getApplicationContext(), TextInputActivity.class);
        intent.putExtra("inputTitle","2 O'Clock Rounds");
        intent.putExtra("inputText",inputLog.getRound2());
        startActivityForResult(intent, 53);
    }

    public void dayRound(View v) {
        Intent intent = new Intent(getApplicationContext(), TextInputActivity.class);
        intent.putExtra("inputTitle","Daytime Rounds");
        intent.putExtra("inputText",inputLog.getRoundDay());
        startActivityForResult(intent, 54);
    }

    public void launchDoc(View v) {
        Intent intent = new Intent(getApplicationContext(), TextInputActivity.class);
        intent.putExtra("inputTitle","Documentations");
        intent.putExtra("inputText",inputLog.getDocumentations());
        startActivityForResult(intent, 55);
    }

    public void launchWO(View v) {
        Intent intent = new Intent(getApplicationContext(), TextInputActivity.class);
        intent.putExtra("inputTitle","Work Orders");
        intent.putExtra("inputText",inputLog.getWorkOrders());
        startActivityForResult(intent, 56);
    }

    public void saveFullLog(View v){
        inputLog.setRaOnDuty(raOnDuty.getText().toString());
        if(logDateOnUpdate.equals("")){

        }
        else{
            inputLog.setLogDate(logDateOnUpdate);
        }

        outputIntent.putExtra("returnLog",inputLog);
        setResult(RESULT_OK,outputIntent);
        finish();

    }

    public void sendFullLog(View v){
        saveFullLog(v);
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_SUBJECT, inputLog.getEmailSubject());
        i.putExtra(Intent.EXTRA_TEXT,inputLog.getEmailBody());
        startActivity(Intent.createChooser(i,"Send form..."));
        finish();
    }

    public void deleteFullLog(View v){

        new AlertDialog.Builder(DutyLogActivity.this)
                .setTitle("Delete Duty Log")
                .setMessage("Are you sure you want to delete this duty log?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int which)
                    {
                        outputIntent.putExtra("returnLog",inputLog);
                        setResult(RESULT_CANCELED,outputIntent);
                        finish();
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int which)
                    {

                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 50 && resultCode == RESULT_OK && data != null) {
            inputLog.setRound8(data.getStringExtra("returnText"));
            mDbHelper.updateDutyLog(inputLog);
        }
        else if(requestCode == 51 && resultCode == RESULT_OK && data != null) {
            inputLog.setRound10(data.getStringExtra("returnText"));
            mDbHelper.updateDutyLog(inputLog);
        }
        else if(requestCode == 52 && resultCode == RESULT_OK && data != null) {
            inputLog.setRound12(data.getStringExtra("returnText"));
            mDbHelper.updateDutyLog(inputLog);
        }
        else if(requestCode == 53 && resultCode == RESULT_OK && data != null) {
            inputLog.setRound2(data.getStringExtra("returnText"));
            mDbHelper.updateDutyLog(inputLog);
        }
        else if(requestCode == 54 && resultCode == RESULT_OK && data != null) {
            inputLog.setRoundDay(data.getStringExtra("returnText"));
            mDbHelper.updateDutyLog(inputLog);
        }
        else if(requestCode == 55 && resultCode == RESULT_OK && data != null) {
            inputLog.setDocumentations(data.getStringExtra("returnText"));
            mDbHelper.updateDutyLog(inputLog);
        }
        else if(requestCode == 56 && resultCode == RESULT_OK && data != null) {
            inputLog.setWorkOrders(data.getStringExtra("returnText"));
            mDbHelper.updateDutyLog(inputLog);
        }
        else {

        }
    }
}
