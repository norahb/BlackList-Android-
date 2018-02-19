package com.example.ativ.dbtest;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;

public class list extends AppCompatActivity {

    Blacklist myBlacklist;
    ListView myList;
    Cursor data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        myBlacklist = new Blacklist(this);

        myList = (ListView)findViewById(R.id.listData);

        ArrayList<String> theList = new ArrayList<>();

        data = myBlacklist.getDataCursor();

        if(data.getCount() == 0){
            Toast.makeText(this, "There are no contents in this list!",Toast.LENGTH_LONG).show();
        }

        else{
            while(data.moveToNext()){

                theList.add(data.getString(1));//1 is the column index for column name
                ListAdapter listAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,theList);
                        myList.setAdapter(listAdapter);
            }
        }

        // try saving records in

        myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,int position, long id)
            {
                String itemName = (String) myList.getItemAtPosition(position);
                Intent sendIn = new Intent(list.this, listItem.class);
                sendIn.putExtra("uName", itemName);
                startActivity(sendIn);

            }

        });
    }

    public void goToMain(View v)
    {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}
