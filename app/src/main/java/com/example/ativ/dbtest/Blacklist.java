package com.example.ativ.dbtest;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;
import static android.R.attr.id;

/**
 * Created by Ativ on 10/19/2017.
 */

public class Blacklist extends SQLiteOpenHelper {

    private static final int  DB_Version = 1;
    private static final String DB_Name ="blacklist.db";

    public static final String Table_Name = "tblBlack";
    public static final String Cul_recID = "recID"; //index 0
    public static final String Cul_Name = "name";// index 1
    public static final String Cul_Reason = "reason";

    public Blacklist(Context context)
    {
        super(context,DB_Name, null, DB_Version );
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String sql = "CREATE TABLE "+ Table_Name + "("+
                Cul_recID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"+
                Cul_Name + " TEXT, "+
                Cul_Reason + " TEXT "+");";

            db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,
    int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS "+ Table_Name );
        onCreate(db);
    }


    public String databaseToString ()
    {
        String dbData="";

        SQLiteDatabase db = getWritableDatabase();

        String query = "SELECT * FROM "+ Table_Name ;

        Cursor c = db.rawQuery(query,null);

        c.moveToFirst();

        while(!c.isAfterLast())
        {
            dbData += c.getString(c.getColumnIndex(Cul_Name));
            dbData += " || "+ c.getString(c.getColumnIndex(Cul_Reason));
            dbData += "\n";

            c.moveToNext();
        }
        db.close();
        return dbData;
    }

    public void addRecord(String nameStr, String resStr)
    {
        SQLiteDatabase db = getWritableDatabase();

        db.execSQL("INSERT INTO "+ Table_Name + "( " +
                Cul_Name + " , " + Cul_Reason +" ) VALUES (?,?)",
                new String[] {nameStr,resStr});
        db.close();
    }


    public Cursor getDataCursor(){
        SQLiteDatabase db = getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + Table_Name, null);
        return data;
    }

    public Cursor getSelectedItem(String itemName){

        SQLiteDatabase db = getWritableDatabase();

        String query = "SELECT * FROM " + Table_Name
                + " where " + Cul_Name + " = ?";

        //Cursor point to a location in your result
        Cursor c = db.rawQuery(query, new String[] {itemName});

        c.moveToFirst();
        return c;
    }
}
