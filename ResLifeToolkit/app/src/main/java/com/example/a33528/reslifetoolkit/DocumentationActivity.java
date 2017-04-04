package com.example.a33528.reslifetoolkit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class DocumentationActivity extends AppCompatActivity {

    private EditText docMessage;
    private TextView docDate;
    private Documentation inputDoc;
    private Intent inputIntent;
    private Intent outputIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.general_form);

        docMessage = (EditText) findViewById(R.id.formBodyET);
        docDate = (TextView) findViewById(R.id.formTimeTV);

        inputIntent = getIntent();
        inputDoc = (Documentation) inputIntent.getSerializableExtra("docSelected");

        docMessage.setText(inputDoc.getDocMessage());
        docDate.setText(inputDoc.getDocCreateDate() + " - " + inputDoc.getDocCreateTime());
    }

    public void saveDoc(View v)
    {
        save();
        outputIntent.putExtra("returnDoc",inputDoc);
        setResult(RESULT_OK,outputIntent);
        finish();
    }

    public void cancelDoc(View v)
    {
        finish();
    }

    public void deleteDoc(View v)
    {
        outputIntent.putExtra("returnDoc",inputDoc);
        setResult(RESULT_CANCELED,outputIntent);
        finish();
    }

    public void save()
    {
        inputDoc.setDocMessage(docMessage.getText().toString());
        inputDoc.setDocModTime();
        inputDoc.setDocModDate();
    }

}
