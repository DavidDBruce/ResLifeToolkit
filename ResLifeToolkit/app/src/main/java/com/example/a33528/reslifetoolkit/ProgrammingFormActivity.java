package com.example.a33528.reslifetoolkit;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
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

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.StringUtils;
import com.itextpdf.text.pdf.codec.Base64;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class ProgrammingFormActivity extends AppCompatActivity {

    Intent intent;
    Intent output = new Intent();
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
    EditText eventDate;
    Switch formSwitch;

    Spinner hallSpinner;

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
        inputForm.setEventDate(eventDate.getText().toString());
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        inputForm.setModDate(formatter.format(Calendar.getInstance().getTime()));

        //Log.d("FindThisPlz", "called formSave(View v)");

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
        inputForm.setEventDate(eventDate.getText().toString());
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        inputForm.setModDate(formatter.format(Calendar.getInstance().getTime()));

        //Log.d("FindThisPlz", "called formSave(View v)");

        output.putExtra("returnForm", inputForm);
        setResult(RESULT_OK, output);
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
        if(formSwitch.isActivated())
        {
            i.putExtra(Intent.EXTRA_SUBJECT, raName.getText().toString() + " -- " + "Passive");
        }
        else
        {
            i.putExtra(Intent.EXTRA_SUBJECT, raName.getText().toString() + " -- " + "Event" + " -- " + inputForm.getEventDate());
        }
        i.putExtra(Intent.EXTRA_TEXT,buildForm());
        startActivity(i.createChooser(i,"Send form..."));
    }

    public String buildForm()
    {
        String completedForm = "";
        if(formSwitch.isActivated()) {
            completedForm += raName.getText().toString() + " -- " + "Passive" + '\n' + '\n';
        }
        else
        {
            completedForm += raName.getText().toString() + " -- " + "Event" + " -- " + inputForm.getEventDate() + '\n' + '\n';
        }

        completedForm += "Title: " + formTitle.getText().toString() + " -- " + hallSpinner.getSelectedItem().toString()+ " Floor " + floor.getText().toString() + '\n' + '\n';
        if(formSwitch.isActivated())
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
