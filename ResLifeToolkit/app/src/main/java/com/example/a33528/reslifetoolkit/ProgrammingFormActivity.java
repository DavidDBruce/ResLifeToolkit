package com.example.a33528.reslifetoolkit;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TimePicker;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class ProgrammingFormActivity extends AppCompatActivity {

    private Intent outputIntent = new Intent();
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
    //private TimePickerDialog.OnTimeSetListener time;
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
        eventDate = (EditText) findViewById(R.id.eventDateET);
        eventTime = (EditText) findViewById(R.id.eventTimeET);
        formSwitch = (Switch) findViewById(R.id.formSwitch);

        formTitle.setText(inputForm.getEventTitle());
        raName.setText(inputForm.getRaName());
        floor.setText(inputForm.getHallFloor());
        why.setText(inputForm.getEventReason());
        description.setText(inputForm.getEventDescription());
        publicity.setText(inputForm.getEventPublicity());
        attendees.setText(inputForm.getAttendees());
        cost.setText(inputForm.getCost());
        goals.setText(inputForm.getGoals());
        eventDate.setText(inputForm.getEventDate());
        eventTime.setText(inputForm.getEventTime());
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
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateDateLabel();
            }

        };

        eventDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new DatePickerDialog(ProgrammingFormActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();

            }
        });

        eventTime.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(ProgrammingFormActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        if(selectedHour == 0) {
                            eventTime.setText( 12 + ":" + String.format(Locale.US,"%02d",selectedMinute) + " AM");
                        }else if(selectedHour < 12) {
                            eventTime.setText(selectedHour + ":" + String.format(Locale.US,"%02d",selectedMinute) + " AM");
                        }else if(selectedHour == 12){
                            eventTime.setText( 12 + ":" + String.format(Locale.US,"%02d",selectedMinute) + " PM");
                        }else{
                            eventTime.setText( selectedHour - 12 + ":" + String.format(Locale.US,"%02d",selectedMinute) + " PM");
                        }
                    }
                }, hour, minute, false);
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

    public void formSave(View v) {

        save();
        outputIntent.putExtra("returnForm", inputForm);
        setResult(RESULT_OK, outputIntent);
        finish();
    }

    public void formSend(View v) {
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

        outputIntent.putExtra("returnForm", inputForm);
        setResult(RESULT_OK, outputIntent);
        finish();
    }

    public void formCancel(View v)
    {
        finish();
    }

    public void formDelete(View v) {
        new AlertDialog.Builder(ProgrammingFormActivity.this)
            .setTitle("Delete Duty Log")
            .setMessage("Are you sure you want to delete this programming form?")
            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener(){
                public void onClick(DialogInterface dialog, int which)
                {
                    outputIntent.putExtra("returnForm", inputForm);
                    setResult(RESULT_CANCELED, outputIntent);
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

    public void save() {
        inputForm.setAttendees(attendees.getText().toString());
        inputForm.setCost(cost.getText().toString());
        inputForm.setIsEvent(!formSwitch.isChecked());
        inputForm.setEventDescription(description.getText().toString());
        inputForm.setEventPublicity(publicity.getText().toString());
        inputForm.setEventReason(why.getText().toString());
        inputForm.setEventTitle(formTitle.getText().toString());
        inputForm.setGoals(formTitle.getText().toString());
        inputForm.setHallFloor(floor.getText().toString());
        inputForm.setHallName(hallSpinner.getSelectedItem().toString());
        inputForm.setRaName(raName.getText().toString());
        inputForm.setEventDate(eventDate.getText().toString());
        inputForm.setEventTime(eventTime.getText().toString());
        inputForm.setGoals(goals.getText().toString());
    }

    public String buildForm() {
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
            completedForm += "-- $" + cost.getText().toString() + '\n' + '\n';
            completedForm += "How many resident came to this event?";
            completedForm += "-- " + attendees.getText().toString() + '\n' + '\n';
            completedForm += "Did you accomplish your goals?";
            completedForm += "-- " + goals.getText().toString() + '\n' + '\n';
        }
        return completedForm;
    }
}
