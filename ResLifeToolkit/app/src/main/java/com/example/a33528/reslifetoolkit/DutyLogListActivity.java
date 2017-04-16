package com.example.a33528.reslifetoolkit;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DutyLogListActivity extends AppCompatActivity {

    private DutyLogAdapter dutyLogLA;
    private ArrayList<DutyLog> logList;
    private MySQLiteHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.general_list);

        logList = new ArrayList<>();

        mDbHelper = new MySQLiteHelper(getApplicationContext());

        logList = mDbHelper.getAllDutyLogs();

        buildLV();
    }

    public void buildLV() {
        dutyLogLA = new DutyLogAdapter(getApplicationContext(), R.layout.duty_log_list_item, R.id.dutyLogDateTV, logList);
        ListView dutyLogLV = (ListView) findViewById(R.id.genericLV);
        dutyLogLV.setAdapter(dutyLogLA);

        dutyLogLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DutyLog item = (DutyLog) parent.getItemAtPosition(position);
                item.setPositionForDelete(position);
                Intent intent = new Intent(getApplicationContext(), DutyLogActivity.class);
                intent.putExtra("logSelected", item);
                startActivityForResult(intent, 43);
            }

        });

        dutyLogLV.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final int removePosition = position;
                new AlertDialog.Builder(DutyLogListActivity.this)
                        .setTitle("Delete Duty Log")
                        .setMessage("Are you sure you want to delete the duty log for " + logList.get(position).getLogDate() + "?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog, int which)
                            {
                                mDbHelper.deleteDutyLog(logList.get(removePosition));
                                logList.clear();
                                logList = mDbHelper.getAllDutyLogs();
                                dutyLogLA.clear();
                                dutyLogLA.addAll(logList);
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
        DutyLog item = new DutyLog();
        item.setPositionForDelete(0);
        mDbHelper.addDutyLog(item);
        logList = mDbHelper.getAllDutyLogs();
        dutyLogLA.clear();
        dutyLogLA.addAll(logList);
        Intent intent = new Intent(getApplicationContext(), DutyLogActivity.class);
        intent.putExtra("logSelected", logList.get(logList.size()-1));
        startActivityForResult(intent, 43);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if(requestCode == 43 && resultCode == RESULT_OK && data != null) {
            DutyLog returnLog = (DutyLog) data.getSerializableExtra("returnLog");
            mDbHelper.updateDutyLog(returnLog);
            logList = mDbHelper.getAllDutyLogs();
            dutyLogLA.clear();
            dutyLogLA.addAll(logList);
            dutyLogLA.notifyDataSetChanged();
        }
        else if(requestCode == 43 && resultCode == RESULT_CANCELED && data != null) {
            DutyLog returnLog = (DutyLog) data.getSerializableExtra("returnLog");
            mDbHelper.deleteDutyLog(returnLog);
            logList = mDbHelper.getAllDutyLogs();
            dutyLogLA.clear();
            dutyLogLA.addAll(logList);
            dutyLogLA.notifyDataSetChanged();
        }
        else {
            if(logList.get(0).getRaOnDuty().equals("") && logList.get(0).getLogDate().equals("")) {
                mDbHelper.deleteDutyLog(logList.get(0));
            }
            dutyLogLA.notifyDataSetChanged();
        }
    }
}


class DutyLogAdapter extends ArrayAdapter<DutyLog> {
    //ArrayAdapter to set the correct information in a ListView for DutyLogs

    //ArrayList<DutyLog> inputForms = new ArrayList<DutyLog>();

    public DutyLogAdapter(Context context, int resource, int textViewResourceId, List<DutyLog> objects) {
        super(context, resource, textViewResourceId, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = super.getView(position,convertView,parent);

        //Retrieving TextViews from the list item XML, then setting them to their corresponding values.

        TextView date = (TextView) view.findViewById(R.id.dutyLogDateTV);
        TextView month = (TextView) view.findViewById(R.id.dutyLogMonthTV);


        DutyLog curLog = getItem(position);

        Scanner s = new Scanner(curLog.getLogDate());
        s.useDelimiter("/");
        int monthInt;
        try {
            monthInt = s.nextInt();
            date.setText(curLog.getLogDate());
            if (monthInt == 1) {
                month.setText("January");
            } else if (monthInt == 2) {
                month.setText("February");
            } else if (monthInt == 3) {
                month.setText("March");
            } else if (monthInt == 4) {
                month.setText("April");
            } else if (monthInt == 5) {
                month.setText("May");
            } else if (monthInt == 6) {
                month.setText("June");
            } else if (monthInt == 7) {
                month.setText("July");
            } else if (monthInt == 8) {
                month.setText("August");
            } else if (monthInt == 9) {
                month.setText("September");
            } else if (monthInt == 10) {
                month.setText("October");
            } else if (monthInt == 11) {
                month.setText("November");
            } else if (monthInt == 12) {
                month.setText("December");
            } else {
                month.setText("Error");
            }
        }
        catch(Exception e) {
            month.setText("No Date");
            date.setText("No Date");
        }

        return view;
    }
}