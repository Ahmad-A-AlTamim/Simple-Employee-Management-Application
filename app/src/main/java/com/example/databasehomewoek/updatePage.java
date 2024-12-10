package com.example.databasehomewoek;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class updatePage extends AppCompatActivity implements View.OnClickListener{
    SQLiteDatabase db;
    Button updateBtn;
    EditText updatenameEditText,updatetotalSalesEditText,updatecommissionEditText,updatebaseSalaryEditText;
    RadioButton updatem,updatef;
    Cursor rs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_page);
        db=openOrCreateDatabase("Company",MODE_PRIVATE,null);
        updateBtn=findViewById(R.id.updateBtn);
        updatenameEditText=findViewById(R.id.updatenameEditText);
        updatetotalSalesEditText=findViewById(R.id.updatetotalSalesEditText);
        updatecommissionEditText=findViewById(R.id.updatecommissionEditText);
        updatebaseSalaryEditText=findViewById(R.id.updatebaseSalaryEditText);
        updatem=findViewById(R.id.updatemaleRadioButton);
        updatef=findViewById(R.id.updatefemaleRadioButton);
        rs=db.rawQuery("SELECT * FROM emp WHERE id= "+getIntent().getStringExtra("id"),new String[]{});
        Log.i("atx","SELECT * FROM emp WHERE id="+getIntent().getStringExtra("id"));
     if(   rs.moveToNext()) {
         updatenameEditText.setText(rs.getString(1));
         updatetotalSalesEditText.setText(rs.getString(4));
         updatecommissionEditText.setText(rs.getString(5));
         updatebaseSalaryEditText.setText(rs.getString(3));
         if (rs.getString(2).equals( "M")) {
             updatem.setChecked(true);
         } else {
             updatef.setChecked(true);
         }
     }
     updateBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v==updateBtn){
            if (updatenameEditText.getText().toString().isEmpty()||updatetotalSalesEditText.getText().toString().isEmpty()||updatecommissionEditText.getText().toString().isEmpty()||updatebaseSalaryEditText.getText().toString().isEmpty()){
                Toast.makeText(this,"invalid input (empty field)",Toast.LENGTH_SHORT).show();
            }
            else {

                    SQLiteStatement prep = db.compileStatement("UPDATE emp SET name = ? , sex = ? ,baseSalary =?, totalSales=?,commissionRate=? WHERE id ="+getIntent().getStringExtra("id"));
                    prep.bindAllArgsAsStrings(new String[]{updatenameEditText.getText().toString(),(updatem.isChecked()?"M":"F"),updatebaseSalaryEditText.getText().toString(),updatetotalSalesEditText.getText().toString(),updatecommissionEditText.getText().toString()});
                    Log.i("atx",prep.toString());
                    prep.execute();
                    Toast.makeText(this,"Done",Toast.LENGTH_SHORT).show();
                    finish();





            }

        }

    }
}