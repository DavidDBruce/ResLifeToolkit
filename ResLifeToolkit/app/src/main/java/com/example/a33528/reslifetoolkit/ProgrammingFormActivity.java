package com.example.a33528.reslifetoolkit;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TimePicker;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.EventListener;
import java.util.Locale;
import java.util.Scanner;

public class ProgrammingFormActivity extends AppCompatActivity {

    private Intent intent;
    private Intent output = new Intent();
    private ProgrammingForm inputForm;
    private EditText formTitle;
    private EditText raName;
    private EditText floor;
    private EditText why;
    private EditText description;
    private EditText publicity;
    private EditText attendees;
    private EditText cost;
    private EditText goals;
    private EditText eventDate;
    private EditText eventTime;
    private Switch formSwitch;

    private Spinner hallSpinner;

    private Calendar myCalendar = Calendar.getInstance();
    private DatePickerDialog.OnDateSetListener date;
    private TimePickerDialog.OnTimeSetListener time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.programming_form);
        intent = getIntent();
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
        eventDate = (EditText) findViewById(R.id.eventDateET);
        eventTime = (EditText) findViewById(R.id.eventTimeET);
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
        eventDate.setText(inputForm.getEventDate());
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
            if(inputForm.getHallName()!=null)
            {
                if (inputForm.getHallName().equals(halls[i]))
                {
                    hallSpinner.setSelection(i);
                }
            }
        }


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

        eventDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(ProgrammingFormActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();

            }
        });

        eventTime.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(ProgrammingFormActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        eventTime.setText( selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        });




    }

    private void updateDateLabel() {

        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        eventDate.setText(sdf.format(myCalendar.getTime()));
    }

    public void formSave(View v)
    {

        save();
        output.putExtra("returnForm", inputForm);
        setResult(RESULT_OK, output);
        finish();
    }

    public void save()
    {
        Scanner s = new Scanner(attendees.getText().toString());
        inputForm.setAttendees(s.nextInt());
        s = new Scanner(cost.getText().toString());
        inputForm.setCost(s.nextDouble());
        inputForm.setIsEvent(!formSwitch.isChecked());
        inputForm.setEventDescription(description.getText().toString());
        inputForm.setEventPublicity(publicity.getText().toString());
        inputForm.setEventReason(why.getText().toString());
        inputForm.setEventTitle(formTitle.getText().toString());
        inputForm.setGoals(formTitle.getText().toString());
        s = new Scanner(floor.getText().toString());
        inputForm.setHallFloor(s.nextInt());
        inputForm.setHallName(hallSpinner.getSelectedItem().toString());
        inputForm.setRaName(raName.getText().toString());
        inputForm.setEventDate(eventDate.getText().toString());
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        inputForm.setModDate(formatter.format(Calendar.getInstance().getTime()));
    }

    public void formCancel(View v)
    {
        finish();
    }

    public void formDelete(View v)
    {
        output.putExtra("returnForm", inputForm);
        setResult(RESULT_CANCELED, output);
        finish();
    }

    public void formSend(View v)
    {
        save();
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        if(!inputForm.getIsEvent())
        {
            i.putExtra(Intent.EXTRA_SUBJECT, raName.getText().toString() + " -- " + "Passive");
        }
        else
        {
            i.putExtra(Intent.EXTRA_SUBJECT, raName.getText().toString() + " -- " + "Event" + " -- " + eventDate.getText().toString());
        }
        i.putExtra(Intent.EXTRA_TEXT,buildForm());
        startActivity(Intent.createChooser(i,"Send form..."));

        output.putExtra("returnForm", inputForm);
        setResult(RESULT_OK, output);
        finish();
    }

    public String buildForm()
    {
        String completedForm = "";
        if(!inputForm.getIsEvent()) {
            completedForm += raName.getText().toString() + " -- " + "Passive" + '\n' + '\n';
        }
        else
        {
            completedForm += raName.getText().toString() + " -- " + "Event" + " -- " + inputForm.getEventDate() + '\n' + '\n';
        }

        completedForm += "Title: " + formTitle.getText().toString() + " -- " + hallSpinner.getSelectedItem().toString()+ " Floor " + floor.getText().toString() + '\n' + '\n';
        if(!inputForm.getIsEvent())
        {
            completedForm += "Why are you writing this passive?"+ '\n';
            completedForm += "-- " + why.getText().toString() + '\n' + '\n';
            completedForm += "Describe your passive." + '\n';
            completedForm += "-- " + description.getText().toString() + '\n' +'\n';
        }
        else
        {
            completedForm += "Why are you hosting the event?" + '\n';
            completedForm += "-- " + why.getText().toString() + '\n' + '\n';
            completedForm += "Describe your event." + '\n';
            completedForm += "-- " + description.getText().toString() + '\n' +'\n';
            completedForm += " What publicity did you use?" + '\n';
            completedForm += "-- " + publicity.getText().toString() + '\n' + '\n';
            completedForm += "---- Post Event Info ----" + '\n' + '\n';
            completedForm += "How much did this event cost";
            Scanner s = new Scanner(cost.getText().toString());
            completedForm += "-- $" + String.format("%.2f",s.nextDouble()) + '\n' + '\n';
            completedForm += "How many resident came to this event?";
            completedForm += "-- " + attendees.getText().toString() + '\n' + '\n';
        }
        return completedForm;
    }

}
