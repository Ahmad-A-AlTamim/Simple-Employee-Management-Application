package com.example.databasehomewoek;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    SQLiteDatabase db;
    Button insertPageBtn,deletePageBtn,searchPageBtn,displayPageBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db=openOrCreateDatabase("Company",MODE_PRIVATE,null);
        db.execSQL("create table if not exists emp (id integer primary key, name text, sex char,baseSalary REAL, totalSales REAL,commissionRate REAL)");
        insertPageBtn=findViewById(R.id.insetBtnPage);
        deletePageBtn=findViewById(R.id.deleteBtnPage);
        searchPageBtn=findViewById(R.id.searchBtnPage);
        displayPageBtn=findViewById(R.id.displayBtnPage);
        displayPageBtn.setOnClickListener(this);
        searchPageBtn.setOnClickListener(this);
        deletePageBtn.setOnClickListener(this);
        insertPageBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v==displayPageBtn){
            Intent x=new Intent(this,displayPage.class);
            startActivity(x);
        } else if (v==searchPageBtn) {
            Intent x=new Intent(this,searchPage.class);
            startActivity(x);
        }else if (v==deletePageBtn) {
            Intent x=new Intent(this,deletePage.class);
            startActivity(x);
        }else if (v==insertPageBtn) {
            Intent x=new Intent(this,insertPage.class);
            startActivity(x);
        }
    }
}