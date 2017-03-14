package com.example.a33528.reslifetoolkit;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static java.security.AccessController.getContext;

public class ProgrammingListActivity extends AppCompatActivity {

    ProgrammingForm testForm;
    ArrayList<ProgrammingForm> formsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.programming_form_list);

        //Test input for application testing

        testForm = new ProgrammingForm("David","South",2,"Smash Night","Fun","Playing Smash Bros", "Flyers and Emails", 0.0, 12, "yes");
        formsList = new ArrayList<ProgrammingForm>();
        formsList.add(testForm);

        buildLV();
        //Added a comment



    }

    public void buildLV()
    {
        ListAdapter programmingListLA = new ArrayAdapter<ProgrammingForm>(getApplicationContext(),R.layout.programming_list_item,R.id.formTypeTV,formsList);
        ListView programmingLV = (ListView) findViewById(R.id.genericLV);
        programmingLV.setAdapter(programmingListLA);

    }

}

class ProgrammingAdapter extends ArrayAdapter<ProgrammingForm> {
    //ArrayAdapter to set the correct information in a ListView for ProgrammingForms
    Context c;

    ArrayList<ProgrammingForm> inputForms = new ArrayList<ProgrammingForm>();

    public ProgrammingAdapter(Context context, int resource, int textViewResourceId, List<ProgrammingForm> objects) {
        super(context, resource, textViewResourceId, objects);
        Log.d("FindThis","Made it to this line.");
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = super.getView(position,convertView,parent);

        //Retrieving TextViews from the list item XML, then setting them to their corresponding values.

        TextView date = (TextView) view.findViewById(R.id.formDateTV);
        TextView type = (TextView) view.findViewById(R.id.formTypeTV);

        ProgrammingForm curForm = getItem(position);

        if(curForm.getIsEvent())
        {
            type.setText("Event");
        }
        else
        {
            type.setText("Passive");
        }

        date.setText("Today");

        return view;
    }
}



