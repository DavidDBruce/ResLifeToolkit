package com.example.a33528.reslifetoolkit;

import android.content.Context;
import android.database.DataSetObserver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ProgrammingListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.generic_list);

        ProgrammingForm testForm = new ProgrammingForm("David","South",2,"Smash Night","Fun","Playing Smash Bros", "Flyers and Emails", 0.0, 12, "yes");
        ArrayList<ProgrammingForm> formsList = new ArrayList<ProgrammingForm>();
        formsList.add(testForm);

        //String[] names = {"David","Cheyanne","Sadie","Priyanka","Addi"};

        ListAdapter programmingListLA = new ArrayAdapter<ProgrammingForm>(this,R.layout.generic_list,R.id.formTypeTV,formsList);
        //ListAdapter programmingListLA = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,names);
        ListView programmingLV = (ListView) findViewById(R.id.genericLV);
        programmingLV.setAdapter(programmingListLA);


    }
}

class ProgrammingAdapter extends ArrayAdapter<ProgrammingForm>
{

    ArrayList<ProgrammingForm> inputForms = new ArrayList<ProgrammingForm>();

    public ProgrammingAdapter(Context context, int resource, int textViewResourceId, ArrayList<ProgrammingForm> objects) {
        super(context, resource, textViewResourceId, objects);

        inputForms = objects;
    }


    @Override
    public int getCount() {
        return inputForms.size();
    }

    @Override
    public ProgrammingForm getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ProgrammingForm curForm = inputForms.get(position);

        TextView date = (TextView) convertView.findViewById(R.id.formDateTV);
        TextView type = (TextView) convertView.findViewById(R.id.formTypeTV);

        if(curForm.getIsEvent())
        {
            type.setText("Event");
        }
        else
        {
            type.setText("Passive");
        }

        date.setText(curForm.getCreateDate());

        return null;
    }
}



