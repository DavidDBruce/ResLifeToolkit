package com.example.a33528.reslifetoolkit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;

public class ProgrammingFormActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.programming_form);
        Intent intent = getIntent();
        ProgrammingForm inputForm = (ProgrammingForm)intent.getSerializableExtra("formSelected");
        EditText formTitle = (EditText) findViewById(R.id.eventTitleET);
        EditText raName = (EditText) findViewById(R.id.raNameET);
        EditText floor = (EditText) findViewById(R.id.floorET);
        EditText why = (EditText) findViewById((R.id.whyET));
        EditText description = (EditText) findViewById(R.id.descET);
        EditText publicity = (EditText) findViewById(R.id.pubET);
        EditText attendees = (EditText) findViewById(R.id.attendeesET);
        EditText cost = (EditText) findViewById(R.id.costET);
        EditText goals = (EditText) findViewById(R.id.goalsET);
        Switch formSwitch = (Switch) findViewById(R.id.formSwitch);
        //Log.d("FindMePlz", inputForm.getEventTitle());

        formTitle.setText(inputForm.getEventTitle());
        raName.setText(inputForm.getRaName());
        floor.setText("" + inputForm.getHallFloor());
        why.setText(inputForm.getEventReason());
        description.setText(inputForm.getEventDescription());
        publicity.setText(inputForm.getEventPublicity());
        attendees.setText("" + inputForm.getAttendees());
        cost.setText("$" + inputForm.getCost());
        goals.setText(inputForm.getGoals());
        if(inputForm.getIsEvent())
        {
            formSwitch.setChecked(false);
        }
        else
        {
            formSwitch.setChecked(true);
        }

        Spinner spinner = (Spinner) findViewById(R.id.hallSPN);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.halls_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
}
