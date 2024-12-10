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

public class insertPage extends AppCompatActivity implements View.OnClickListener{
    SQLiteDatabase db;
    Button insertBtn;
    EditText nameEditText,idEditText,totalSalesEditText,commissionEditText,baseSalaryEditText;
    RadioButton m,f;
    Cursor rs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_page);
        db=openOrCreateDatabase("Company",MODE_PRIVATE,null);
        insertBtn=findViewById(R.id.insertBtn);

        nameEditText=findViewById(R.id.nameEditText);
        idEditText=findViewById(R.id.idEditText);
        totalSalesEditText=findViewById(R.id.totalSalesEditText);
        commissionEditText=findViewById(R.id.commissionEditText);
        baseSalaryEditText=findViewById(R.id.baseSalaryEditText);

        m=findViewById(R.id.maleRadioButton);
        f=findViewById(R.id.femaleRadioButton);

        insertBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v==insertBtn){
            if (nameEditText.getText().toString().isEmpty()||idEditText.getText().toString().isEmpty()||totalSalesEditText.getText().toString().isEmpty()||commissionEditText.getText().toString().isEmpty()||baseSalaryEditText.getText().toString().isEmpty()){
                Toast.makeText(this,"invalid input (empty field)",Toast.LENGTH_SHORT).show();
            }
            else {
                rs=db.rawQuery("SELECT * FROM emp WHERE id= "+idEditText.getText().toString(),new String[]{});
                Log.i("atx","SELECT * FROM emp WHERE id="+idEditText.getText().toString());
                if (rs.moveToFirst() == true) {
                    Toast.makeText(this,"invalid ID (already exists)",Toast.LENGTH_SHORT).show();
                }else{

                    SQLiteStatement prep = db.compileStatement("INSERT INTO emp VALUES(?,?,?,?,?,?)");
                    prep.bindAllArgsAsStrings(new String[]{idEditText.getText().toString(),nameEditText.getText().toString(),(m.isChecked()?"M":"F"),baseSalaryEditText.getText().toString(),totalSalesEditText.getText().toString(),commissionEditText.getText().toString()});
                    Log.i("atx",prep.toString());
                    prep.execute();
                    Toast.makeText(this,"Done",Toast.LENGTH_SHORT).show();
                    idEditText.setText("");
                    nameEditText.setText("");
                    baseSalaryEditText.setText("");
                    totalSalesEditText.setText("");
                    commissionEditText.setText("");
                    m.setChecked(true);


                }

            }

        }

    }
}