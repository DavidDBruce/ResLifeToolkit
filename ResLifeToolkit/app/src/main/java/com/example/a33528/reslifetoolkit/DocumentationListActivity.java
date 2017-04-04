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

public class DocumentationListActivity extends AppCompatActivity {

    Intent inputIntent;
    DocumentationAdapter documentationLA;
    ArrayList<Documentation> inputDocList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.general_list);

        inputIntent = getIntent();
        inputDocList = (ArrayList<Documentation>)inputIntent.getSerializableExtra("inputDocList");

        buildLV();
    }

    public void buildLV(){

        documentationLA = new DocumentationAdapter(getApplicationContext(), R.layout.doc_wo_list_item, R.id.docDateTV, inputDocList);
        ListView documentationLV = (ListView) findViewById(R.id.genericLV);
        documentationLV.setAdapter(documentationLA);

        documentationLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Documentation item = (Documentation) parent.getItemAtPosition(position);
                item.setPositionForDelete(position);
                Intent intent = new Intent(getApplicationContext(), DocumentationActivity.class);
                intent.putExtra("docSelected", item);
                startActivityForResult(intent, 45);
            }

        });

        documentationLV.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final int removePosition = position;
                new AlertDialog.Builder(DocumentationListActivity.this)
                        .setTitle("Delete Documentation")
                        .setMessage("Are you sure you want to delete the documentation for " + inputDocList.get(position).getDocCreateDate() + "?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog, int which)
                            {
                                inputDocList.remove(removePosition);
                                documentationLA.notifyDataSetChanged();
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

class DocumentationAdapter extends ArrayAdapter<Documentation> {

    ArrayList<Documentation> inputForms = new ArrayList<Documentation>();

    public DocumentationAdapter(Context context, int resource, int textViewResourceId, List<Documentation> objects) {
        super(context, resource, textViewResourceId, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = super.getView(position,convertView,parent);

        TextView date = (TextView) view.findViewById(R.id.docDateTV);
        TextView time = (TextView) view.findViewById(R.id.docTimeTV);

        Documentation curDoc = getItem(position);

        date.setText(curDoc.getDocCreateDate());
        time.setText(curDoc.getDocCreateTime());

        return view;
    }
}
