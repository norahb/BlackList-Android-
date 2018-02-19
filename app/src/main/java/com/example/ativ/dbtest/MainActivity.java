package com.example.ativ.dbtest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText name, reason;
    Button add, show;
    TextView data;
    Blacklist myBlacklist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = (EditText)findViewById(R.id.edtName);
        reason = (EditText)findViewById(R.id.edtReason);

        add = (Button)findViewById(R.id.btnAdd);
        show = (Button)findViewById(R.id.btnShow);

        data = (TextView)findViewById(R.id.txtData);

        myBlacklist = new Blacklist(this);
    }

    public void addRecord (View v)
    {
        String nameStr = name.getText().toString();
        String resStr = reason.getText().toString();

        if(name.length()!= 0 && reason.length()!= 0){
            myBlacklist.addRecord(nameStr,resStr);
            name.setText("");
            reason.setText("");
        }
        else{
            Toast.makeText(MainActivity.this, "You must put something in the text field!", Toast.LENGTH_LONG).show();
        }

    }

    public void showData (View v)
    {
       /* String dbString = myBlacklist.databaseToString();
        data.setText(dbString);*/
        Intent i = new Intent(this, list.class);
        startActivity(i);
    }
}
