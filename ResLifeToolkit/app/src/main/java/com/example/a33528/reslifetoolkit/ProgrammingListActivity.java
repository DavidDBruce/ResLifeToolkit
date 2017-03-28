package com.example.a33528.reslifetoolkit;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static java.security.AccessController.getContext;

public class ProgrammingListActivity extends AppCompatActivity {

    ProgrammingForm testForm;
    ProgrammingForm testForm2;
    ProgrammingForm testForm3;
    ArrayList<ProgrammingForm> formsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.programming_form_list);

        //Test input for application testing

        testForm = new ProgrammingForm("David Bruce","South",2,"Smash Night","Fun","Playing Smash Bros", "Flyers and Emails", 0.0, 12, "yes", true);
        testForm2 = new ProgrammingForm("Cheyanne Whorton","South",2,"Sleep","Floor needs to learn to sleep.","Bulletin board with points built in.", "", 0.0, 0, "", false);
        testForm3 = new ProgrammingForm("Sadie Moore","South",3,"Hot Seat","Fun","Playing Hot Seat after floor meeting.", "Flyers and Emails", 0.0, 12, "yes", true);
        formsList = new ArrayList<ProgrammingForm>();
        formsList.add(testForm);
        formsList.add(testForm2);
        formsList.add(testForm3);

        buildLV();
    }

    public void buildLV()
    {
        ProgrammingAdapter programmingListLA = new ProgrammingAdapter(getApplicationContext(),R.layout.programming_list_item,R.id.formDateTV,formsList);
        ListView programmingLV = (ListView) findViewById(R.id.genericLV);
        programmingLV.setAdapter(programmingListLA);

        programmingLV.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ProgrammingForm item = (ProgrammingForm) parent.getItemAtPosition(position);

                Intent intent = new Intent(getApplicationContext(),ProgrammingFormActivity.class);
                intent.putExtra("formSelected", item);
                startActivity(intent);
            }

        });

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

        date.setText(curForm.getCreateDate());
        title.setText(curForm.getEventTitle());

        return view;
    }
}



