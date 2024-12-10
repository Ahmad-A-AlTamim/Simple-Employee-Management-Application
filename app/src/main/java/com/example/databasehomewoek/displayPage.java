package com.example.databasehomewoek;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class displayPage extends AppCompatActivity implements View.OnClickListener {
    SQLiteDatabase db;
    RecyclerView rv;
    Cursor rs;

    List<item> list;
    CustomAdapter ca;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_page);
        db=openOrCreateDatabase("Company",MODE_PRIVATE,null);
        rs=db.rawQuery("SELECT * FROM emp ",new String[]{});

        dispalyItems();

    }

    private void dispalyItems() {
        rv=findViewById(R.id.rv);
        rv.setHasFixedSize(true);
        list=new ArrayList<>();
        while (rs.moveToNext()) {
            list.add(new item(makeRec()));
        }
        rv.setLayoutManager(new LinearLayoutManager(this));

        ca=new CustomAdapter(this,list);
        rv.setAdapter(ca);
    }
    String makeRec(){
        String temp="ID : "+rs.getString(0);
        temp+="\n";
        temp+="Name : "+rs.getString(1);
        temp+="\n";
        temp+="Sex : "+(rs.getString(2).equals("M")?"Male":"Female");
        temp+="\n";
        temp+="Base Salary : "+rs.getString(3);
        temp+="\n";
        temp+="Total Sales : "+rs.getString(4);
        temp+="\n";
        temp+="Commission Rate : "+rs.getString(5)+" %";
        temp+="\n";
        temp+="Net Salary : "+(rs.getFloat(3)+(rs.getFloat(4)*(rs.getFloat(5)/100.0)))+" ";
        Log.i("atx",temp);
        return temp;
    }
    @Override
    public void onClick(View view) {

    }
}