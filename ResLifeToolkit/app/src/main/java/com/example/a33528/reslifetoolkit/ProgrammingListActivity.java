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

    ArrayList<ProgrammingForm> formsList;
    ProgrammingAdapter programmingListLA;
    FloatingActionButton addFormsButton;
    MySQLiteHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.general_list);

        mDbHelper = new MySQLiteHelper(getApplicationContext());

        formsList = new ArrayList<ProgrammingForm>();

        addFormsButton = (FloatingActionButton) findViewById(R.id.addFormFAB);

        formsList = mDbHelper.getAllProgrammingForms();

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
                                mDbHelper.deleteProgrammingForm(formsList.get(removePosition));
                                formsList = mDbHelper.getAllProgrammingForms();
                                programmingListLA.clear();
                                programmingListLA.addAll(formsList);
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
        mDbHelper.addProgrammingForm(item);
        formsList = mDbHelper.getAllProgrammingForms();
        programmingListLA.clear();
        programmingListLA.addAll(formsList);
        Intent intent = new Intent(getApplicationContext(), ProgrammingFormActivity.class);
        intent.putExtra("formSelected", formsList.get(formsList.size()-1));
        startActivityForResult(intent, 42);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if(requestCode == 42 && resultCode == RESULT_OK && data != null) {
            ProgrammingForm returnForm = (ProgrammingForm) data.getSerializableExtra("returnForm");
            mDbHelper.updateProgrammingForm(returnForm);
            formsList = mDbHelper.getAllProgrammingForms();
            programmingListLA.clear();
            programmingListLA.addAll(formsList);
            programmingListLA.notifyDataSetChanged();
        }
        else if(requestCode == 42 && resultCode == RESULT_CANCELED && data != null) {
            ProgrammingForm returnForm = (ProgrammingForm) data.getSerializableExtra("returnForm");
            mDbHelper.deleteProgrammingForm(returnForm);
            formsList = mDbHelper.getAllProgrammingForms();
            programmingListLA.clear();
            programmingListLA.addAll(formsList);
            programmingListLA.notifyDataSetChanged();
        }
        else {
            if(formsList.get(0).getEventTitle().equals("No Title") && formsList.get(0).getEventDescription().equals("")) {
                mDbHelper.deleteProgrammingForm(formsList.get(0));
            }
            programmingListLA.notifyDataSetChanged();
        }
    }
}

class ProgrammingAdapter extends ArrayAdapter<ProgrammingForm> {

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



