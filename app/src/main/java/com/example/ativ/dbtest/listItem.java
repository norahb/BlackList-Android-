package com.example.ativ.dbtest;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class listItem extends AppCompatActivity {

    String itemName;
    Blacklist myBlacklist;
    TextView name, reason;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_item);

        myBlacklist = new Blacklist(this);
        name = (TextView)findViewById(R.id.txtName);
        reason = (TextView)findViewById(R.id.txtReason);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if(extras != null){
            itemName = extras.getString("uName");
        }

        Cursor c = myBlacklist.getSelectedItem(itemName);

        name.append(" "+c.getString(1));
        reason.append(" "+c.getString(2));
    }


}
