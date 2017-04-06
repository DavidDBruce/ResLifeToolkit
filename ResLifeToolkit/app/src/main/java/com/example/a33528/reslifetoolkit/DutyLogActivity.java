package com.example.a33528.reslifetoolkit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class DutyLogActivity extends AppCompatActivity {

    private Intent inputIntent;
    private Intent outputIntent = new Intent();
    private DutyLog inputLog;
    private ArrayList<Documentation> inputDocs;
    private ArrayList<Documentation> inputWorkOrders;
    private TextView dutyLogTitle;
    private EditText raOnDuty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.duty_log);

        inputIntent = getIntent();
        inputLog = (DutyLog) inputIntent.getSerializableExtra("logSelected");
        dutyLogTitle = (TextView) findViewById(R.id.dutyLogDateTV);
        raOnDuty = (EditText) findViewById(R.id.raOnDutyET);

        dutyLogTitle.setText("Duty Log for " + inputLog.getCreateDate());
        raOnDuty.setText(inputLog.getRaOnDuty());
     }

    public void eightRound(View v)
    {
        Log.d("FindMePlz", "Button Pushed");
    }

    public void tenRound(View v)
    {
        Log.d("FindMePlz", "Button Pushed");
    }

    public void twelveRound(View v)
    {
        Log.d("FindMePlz", "Button Pushed");
    }

    public void twoRound(View v)
    {
        Log.d("FindMePlz", "Button Pushed");
    }

    public void dayRound(View v)
    {
        Log.d("FindMePlz", "Button Pushed");
    }

    public void launchDoc(View v)
    {
        Intent intent = new Intent(getApplicationContext(), DocumentationListActivity.class);
        intent.putExtra("inputDocList", inputDocs);
        startActivityForResult(intent, 44);
    }

    public void launchWO(View v)
    {
        Intent intent = new Intent(getApplicationContext(), DocumentationListActivity.class);
        intent.putExtra("inputDocList", inputWorkOrders);
        startActivityForResult(intent, 46);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if(requestCode == 44 && resultCode == RESULT_OK && data != null)
        {
            Documentation returnDoc = (Documentation) data.getSerializableExtra("returnDocList");
            inputDocs.remove(returnDoc.getPositionForDelete());
            Collections.reverse(inputDocs);
            inputDocs.add(returnDoc);
            Collections.reverse(inputDocs);

        }
        else if(requestCode == 44 && resultCode == RESULT_CANCELED && data != null)
        {
            Documentation returnDoc = (Documentation) data.getSerializableExtra("returnDocList");
            inputDocs.remove(returnDoc.getPositionForDelete());
        }
        else if(requestCode == 46 && resultCode == RESULT_OK && data != null)
        {
            Documentation returnWorkOrder = (Documentation) data.getSerializableExtra("returnDocList");
            inputDocs.remove(returnWorkOrder.getPositionForDelete());
            Collections.reverse(inputDocs);
            inputDocs.add(returnWorkOrder);
            Collections.reverse(inputDocs);

        }
        else if(requestCode == 46 && resultCode == RESULT_CANCELED && data != null)
        {
            Documentation returnWorkOrder = (Documentation) data.getSerializableExtra("returnForm");
            inputDocs.remove(returnWorkOrder.getPositionForDelete());
        }
        else
        {
            Toast.makeText(getApplicationContext(),"Documentation or Work Order not successfully written.", Toast.LENGTH_SHORT).show();
        }
    }
}
