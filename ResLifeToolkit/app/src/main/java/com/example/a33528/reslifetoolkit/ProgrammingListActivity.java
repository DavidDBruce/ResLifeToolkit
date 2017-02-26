package com.example.a33528.reslifetoolkit;

import android.database.DataSetObserver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ProgrammingListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.generic_list);

        ProgrammingForm testForm = new ProgrammingForm("David","South",2,"Smash Night","Fun","Playing Smash Bros", "Flyers and Emails", 0.0, 12, "yes");
        ArrayList<ProgrammingForm> formsList = new ArrayList<ProgrammingForm>();
        formsList.add(testForm);

        String[] names = {"David","Cheyanne","Sadie","Priyanka","Addi"};

        ListAdapter programmingListLA = new ArrayAdapter<String>(this,R.layout.generic_list,R.id.formTypeTV,formsList);
        //ListAdapter programmingListLA = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,names);
        ListView programmingLV = (ListView) findViewById(R.id.genericLV);
        programmingLV.setAdapter(programmingListLA);


    }
}

class ProgrammingAdapter extends BaseAdapter
{

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}



