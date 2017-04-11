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

import org.w3c.dom.Text;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class DutyLogListActivity extends AppCompatActivity {

    private DutyLogAdapter dutyLogLA;
    private ArrayList<DutyLog> logList = new ArrayList<DutyLog>();
    //private ArrayList<Documentation> docList = new ArrayList<Documentation>();
    //private ArrayList<Documentation> woList = new ArrayList<Documentation>();
    private DutyLog testLog1;
    private DutyLog testLog2;
    private DutyLog testLog3;

    File dutyLogJSON;
    Intent inputIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.general_list);

        inputIntent = getIntent();
        dutyLogJSON = (File) inputIntent.getSerializableExtra("dutyLogFile");


        //docList.add(new Documentation("Alcohol on second floor."));
        //docList.add(new Documentation("Noise violation on first."));
        //docList.add(new Documentation("Fight on third."));

        //woList.add(new Documentation("Sink flooded on third"));
        //woList.add(new Documentation("Light out on first"));
        //woList.add(new Documentation("Door stuck shut in room 115"));

//        testLog1 = new DutyLog("Peeps","Quiet","Quiet","","","David Bruce", docList, woList);
//        testLog2 = new DutyLog("Persons","Mixtape","Fire Alarm","Ducks","Nothing","Cheyanne Whorton", docList, woList);
//        testLog3 = new DutyLog("People","Stuff","Priyanka's Room","","","Mersadie Moore", docList, woList);
//
//        logList.add(testLog1);
//        logList.add(testLog2);
//        logList.add(testLog3);

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

    public void addForm(View v)
    {
        DutyLog item = new DutyLog();
        item.setPositionForDelete(0);
        Collections.reverse(logList);
        logList.add(item);
        Collections.reverse(logList);
        Intent intent = new Intent(getApplicationContext(), DutyLogActivity.class);
        intent.putExtra("logSelected", item);
        startActivityForResult(intent, 43);

    }

    public void addDoc(View v)
    {
        DutyLog item = new DutyLog();
        item.setPositionForDelete(0);
        Collections.reverse(logList);
        logList.add(item);
        Collections.reverse(logList);
        Intent intent = new Intent(getApplicationContext(), DutyLogActivity.class);
        intent.putExtra("logSelected", item);
        startActivityForResult(intent, 43);

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

        Scanner s = new Scanner(curLog.getCreateDate());
        s.useDelimiter("/");
        int monthInt = s.nextInt();

        date.setText(curLog.getCreateDate());
        if(monthInt == 1){
            month.setText("January");
        }else if(monthInt == 2){
            month.setText("February");
        }else if(monthInt == 3){
            month.setText("March");
        }else if(monthInt == 4){
            month.setText("April");
        }else if(monthInt == 5){
            month.setText("May");
        }else if(monthInt == 6){
            month.setText("June");
        }else if(monthInt == 7){
            month.setText("July");
        }else if(monthInt == 8){
            month.setText("August");
        }else if(monthInt == 9){
            month.setText("September");
        }else if(monthInt == 10){
            month.setText("October");
        }else if(monthInt == 11){
            month.setText("November");
        }else if(monthInt == 12){
            month.setText("December");
        }else{
            month.setText("Error");
        }

        return view;
    }
}