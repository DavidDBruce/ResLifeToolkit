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
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

public class ProgrammingFormActivity extends AppCompatActivity {

    ProgrammingForm inputForm;
    EditText formTitle;
    EditText raName;
    EditText floor;
    EditText why;
    EditText description;
    EditText publicity;
    EditText attendees;
    EditText cost;
    EditText goals;
    Switch formSwitch;

    Spinner hallSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.programming_form);
        Intent intent = getIntent();
        inputForm = (ProgrammingForm)intent.getSerializableExtra("formSelected");
        formTitle = (EditText) findViewById(R.id.eventTitleET);
        raName = (EditText) findViewById(R.id.raNameET);
        floor = (EditText) findViewById(R.id.floorET);
        why = (EditText) findViewById((R.id.whyET));
        description = (EditText) findViewById(R.id.descET);
        publicity = (EditText) findViewById(R.id.pubET);
        attendees = (EditText) findViewById(R.id.attendeesET);
        cost = (EditText) findViewById(R.id.costET);
        goals = (EditText) findViewById(R.id.goalsET);
        formSwitch = (Switch) findViewById(R.id.formSwitch);

        formTitle.setText(inputForm.getEventTitle());
        raName.setText(inputForm.getRaName());
        floor.setText("" + inputForm.getHallFloor());
        why.setText(inputForm.getEventReason());
        description.setText(inputForm.getEventDescription());
        publicity.setText(inputForm.getEventPublicity());
        attendees.setText("" + inputForm.getAttendees());
        cost.setText("" + inputForm.getCost());
        goals.setText(inputForm.getGoals());
        if(inputForm.getIsEvent())
        {
            formSwitch.setChecked(false);
        }
        else
        {
            formSwitch.setChecked(true);
        }

        hallSpinner = (Spinner) findViewById(R.id.hallSPN);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.halls_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        hallSpinner.setAdapter(adapter);

        String[] halls = getResources().getStringArray(R.array.halls_array);

        for(int i = 0; i < halls.length; i++)
        {
            if(inputForm.getHallName().equals(halls[i]))
            {
                hallSpinner.setSelection(i);
            }
        }

    }

    public void formSave(View v)
    {
        Scanner s = new Scanner(attendees.getText().toString());
        inputForm.setAttendees(s.nextInt());
        s = new Scanner(cost.getText().toString());
        inputForm.setCost(s.nextDouble());
        inputForm.setIsEvent(!formSwitch.isActivated());
        inputForm.setEventDescription(description.getText().toString());
        inputForm.setEventPublicity(publicity.getText().toString());
        inputForm.setEventReason(why.getText().toString());
        inputForm.setEventTitle(formTitle.getText().toString());
        inputForm.setGoals(formTitle.getText().toString());
        s = new Scanner(floor.getText().toString());
        inputForm.setHallFloor(s.nextInt());
        inputForm.setHallName(hallSpinner.getSelectedItem().toString());
        inputForm.setRaName(raName.getText().toString());
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        inputForm.setModDate(formatter.format(Calendar.getInstance().getTime()));
    }

    public void formCancel(View v)
    {
        finish();
    }

    public void formDelete(View v)
    {
        Toast.makeText(this, "Implement Delete", Toast.LENGTH_SHORT).show();
    }

}
