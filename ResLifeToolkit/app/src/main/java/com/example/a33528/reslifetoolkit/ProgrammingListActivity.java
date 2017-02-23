package com.example.a33528.reslifetoolkit;

import android.database.DataSetObserver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

import java.util.ArrayList;

public class ProgrammingListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.generic_list);
        ProgrammingForm testForm = new ProgrammingForm("David","South",2,"Smash Night","Fun","Playing Smash Bros", "Flyers and Emails", 0.0, 12, "yes");
        ArrayList<ProgrammingForm> formsList = new ArrayList<ProgrammingForm>();
        formsList.add(testForm);
        ListAdapter programmingList = new ArrayAdapter<ProgrammingForm>(this, R.layout.programming_list_item, formsList);

    }
}



