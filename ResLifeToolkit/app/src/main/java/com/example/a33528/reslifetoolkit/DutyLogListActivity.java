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

public class DutyLogListActivity extends AppCompatActivity {

    private DutyLogAdapter dutyLogLA;
    private ArrayList<DutyLog> logList;
    private DutyLog testLog1;
    private DutyLog testLog2;
    private DutyLog testLog3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.general_list);

        testLog1 = new DutyLog("Peeps","Quiet","Quiet","","","David Bruce", new ArrayList<Documentation>(), new ArrayList<WorkOrder>());
        testLog2 = new DutyLog("Persons","Mixtape","Fire Alarm","Ducks","Nothing","Cheyanne Whorton", new ArrayList<Documentation>(), new ArrayList<WorkOrder>());
        testLog3 = new DutyLog("People","Stuff","Priyanka's Room","","","Mersadie Moore", new ArrayList<Documentation>(), new ArrayList<WorkOrder>());

        logList.add(testLog1);
        logList.add(testLog2);
        logList.add(testLog3);

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
                        .setMessage("Are you sure you want to delete the duty log for " + logList.get(position).getCreateDate() + "?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog, int which)
                            {
                                logList.remove(removePosition);
                                dutyLogLA.notifyDataSetChanged();
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
}


class DutyLogAdapter extends ArrayAdapter<DutyLog> {
    //ArrayAdapter to set the correct information in a ListView for DutyLogs

    ArrayList<DutyLog> inputForms = new ArrayList<DutyLog>();

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

        date.setText(curLog.getCreateDate());

        return view;
    }
}