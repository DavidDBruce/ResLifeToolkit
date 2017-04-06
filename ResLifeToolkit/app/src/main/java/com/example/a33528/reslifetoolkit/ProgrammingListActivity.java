package com.example.a33528.reslifetoolkit;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProgrammingListActivity extends AppCompatActivity {

    public static final String PREFS = "prefs";

    ProgrammingForm testForm;
    ProgrammingForm testForm2;
    ProgrammingForm testForm3;
    ArrayList<ProgrammingForm> formsList;
    ProgrammingAdapter programmingListLA;
    FloatingActionButton addFormsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.general_list);

        //Test input for application testing

//        testForm = new ProgrammingForm("David Bruce", "South Complex", 2, "Smash Night", "Fun", "Playing Smash Bros", "Flyers and Emails", 0.0, 12, "yes", true);
//        testForm2 = new ProgrammingForm("Cheyanne Whorton", "South Complex", 2, "Sleep", "Floor needs to learn to sleep.", "Bulletin board with points built in.", "", 0.0, 0, "", false);
//        testForm3 = new ProgrammingForm("Sadie Moore", "South Complex", 3, "Hot Seat", "Fun", "Playing Hot Seat after floor meeting.", "Flyers and Emails", 0.0, 12, "yes", true);
        formsList = new ArrayList<ProgrammingForm>();
//        formsList.add(testForm);
//        formsList.add(testForm2);
//        formsList.add(testForm3);
        addFormsButton = (FloatingActionButton) findViewById(R.id.addFormFAB);

//        initializePrefs();
        initializeForms();

        buildLV();
    }

    public void buildLV() {
        programmingListLA = new ProgrammingAdapter(getApplicationContext(), R.layout.programming_list_item, R.id.formDateTV, formsList);
        ListView programmingLV = (ListView) findViewById(R.id.genericLV);
        programmingLV.setAdapter(programmingListLA);

        programmingLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ProgrammingForm item = (ProgrammingForm) parent.getItemAtPosition(position);
                item.setPositionForDelete(position);
                Intent intent = new Intent(getApplicationContext(), ProgrammingFormActivity.class);
                intent.putExtra("formSelected", item);
                startActivityForResult(intent, 42);
            }

        });

        programmingLV.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final int removePosition = position;
                new AlertDialog.Builder(ProgrammingListActivity.this)
                        .setTitle("Delete Form")
                        .setMessage("Are you sure you want to delete " + formsList.get(position).getEventTitle() + "?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog, int which)
                            {
                                formsList.remove(removePosition);
                                programmingListLA.notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog, int which)
                            {

                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
                return true;
            }
        });
    }

    public void addForm(View v)
    {
        ProgrammingForm item = new ProgrammingForm();
        item.setPositionForDelete(0);
        Collections.reverse(formsList);
        formsList.add(item);
        Collections.reverse(formsList);
        Intent intent = new Intent(getApplicationContext(), ProgrammingFormActivity.class);
        intent.putExtra("formSelected", item);
        startActivityForResult(intent, 42);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if(requestCode == 42 && resultCode == RESULT_OK && data != null)
        {
            ProgrammingForm returnForm = (ProgrammingForm) data.getSerializableExtra("returnForm");
            formsList.remove(returnForm.getPositionForDelete());
            Collections.reverse(formsList);
            formsList.add(returnForm);
            Collections.reverse(formsList);
            programmingListLA.notifyDataSetChanged();

        }
        else if(requestCode == 42 && resultCode == RESULT_CANCELED && data != null)
        {
            ProgrammingForm returnForm = (ProgrammingForm) data.getSerializableExtra("returnForm");
            formsList.remove(returnForm.getPositionForDelete());
            programmingListLA.notifyDataSetChanged();
        }
        else
        {
            if(formsList.get(0).getEventTitle().equals("No Title") && formsList.get(0).getEventDescription().equals(""))
            {
                formsList.remove(0);
            }
            programmingListLA.notifyDataSetChanged();

        }
    }

    private void initializePrefs()
    {
        SharedPreferences prefs = getSharedPreferences(PREFS,MODE_PRIVATE);
        SharedPreferences.Editor prefEdit = prefs.edit();
        for(int i = 0; i < formsList.size(); i++)
        {
            prefEdit.putBoolean("PF " + i, true);
            prefEdit.putString("PF " + i + " raName", formsList.get(i).getRaName());
            prefEdit.putString("PF " + i + " hallName", formsList.get(i).getHallName());
            prefEdit.putString("PF " + i + " floorNum", formsList.get(i).getHallFloor());
            prefEdit.putString("PF " + i + " eventTitle", formsList.get(i).getEventTitle());
            prefEdit.putString("PF " + i + " eventReason", formsList.get(i).getEventReason());
            prefEdit.putString("PF " + i + " eventDescription", formsList.get(i).getEventDescription());
            prefEdit.putString("PF " + i + " eventPublicity", formsList.get(i).getEventPublicity());
            prefEdit.putString("PF " + i + " eventDate", formsList.get(i).getEventDate());
            prefEdit.putString("PF " + i + " cost", formsList.get(i).getCost()); //find better solution
            prefEdit.putString("PF " + i + " attendees", formsList.get(i).getAttendees());
            prefEdit.putString("PF " + i + " goals", formsList.get(i).getGoals());
            prefEdit.putInt("PF " + i + " positionForDelete", formsList.get(i).getPositionForDelete());
            prefEdit.putBoolean("PF " + i + " isEvent", formsList.get(i).getIsEvent());
            //prefEdit.commit();
            prefEdit.apply();
        }
        for(int i = 0; i < formsList.size(); i++)
        {
            Log.d("FindMePlz",prefs.getString("PF " + i + " raName",null));
            Log.d("FindMePlz","PF " + i + " raName");

        }
    }

    private void initializeForms()
    {
        SharedPreferences prefs = getSharedPreferences(PREFS,MODE_PRIVATE);
        int i = 0;
        while(true)
        {
            if(prefs.getBoolean("PF " + i,false))
            {
                ProgrammingForm pf = new ProgrammingForm();
                pf.setRaName(prefs.getString("PF " + i + "raName", ""));
                pf.setHallName(prefs.getString("PF " + i + " hallName", ""));
                pf.setHallFloor(prefs.getString("PF " + i + " floorNum", ""));
                pf.setEventTitle(prefs.getString("PF " + i + " eventTitle", "Error"));
                pf.setEventReason(prefs.getString("PF " + i + " eventReason", ""));
                pf.setEventDescription(prefs.getString("PF " + i + " eventDescription", ""));
                pf.setEventPublicity(prefs.getString("PF " + i + " eventPublicity", ""));
                pf.setEventDate(prefs.getString("PF " + i + " eventDate", ""));
                pf.setCost(prefs.getString("PF " + i + " cost", ""));
                pf.setAttendees(prefs.getString("PF " + i + " attendees", ""));
                pf.setGoals(prefs.getString("PF " + i + " goals", ""));
                pf.setPositionForDelete(prefs.getInt("PF " + i + " positionForDelete", -1));
                pf.setIsEvent(prefs.getBoolean("PF " + i + " isEvent", true));
                formsList.add(pf);
                i++;
            }
            else
            {
                break;
            }

        }
    }

}

class ProgrammingAdapter extends ArrayAdapter<ProgrammingForm> {
    //ArrayAdapter to set the correct information in a ListView for ProgrammingForms
    Context c;

    ArrayList<ProgrammingForm> inputForms = new ArrayList<ProgrammingForm>();

    public ProgrammingAdapter(Context context, int resource, int textViewResourceId, List<ProgrammingForm> objects) {
        super(context, resource, textViewResourceId, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = super.getView(position,convertView,parent);

        //Retrieving TextViews from the list item XML, then setting them to their corresponding values.

        TextView date = (TextView) view.findViewById(R.id.formDateTV);
        TextView type = (TextView) view.findViewById(R.id.formTypeTV);
        TextView title = (TextView) view.findViewById(R.id.formTitleTV);

        ProgrammingForm curForm = getItem(position);

        if(curForm.getIsEvent())
        {
            type.setText("Event");
        }
        else
        {
            type.setText("Passive");
        }

        date.setText(curForm.getEventDate());
        title.setText(curForm.getEventTitle());

        return view;
    }
}



