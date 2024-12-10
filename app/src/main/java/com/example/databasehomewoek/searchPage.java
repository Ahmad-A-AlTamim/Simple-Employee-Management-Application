package com.example.databasehomewoek;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class searchPage extends AppCompatActivity implements View.OnClickListener{
    SQLiteDatabase db;
    EditText id_search_EditText;
    Button searchBtn;
    Cursor rs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_page);
        db=openOrCreateDatabase("Company",MODE_PRIVATE,null);
        id_search_EditText=findViewById(R.id.id_search_EditText);
        searchBtn=findViewById(R.id.searchBtn);
        searchBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v==searchBtn){
            if (id_search_EditText.getText().toString().isEmpty()){
                Toast.makeText(this,"invalid input (empty field)",Toast.LENGTH_SHORT).show();
            }
            else {
                rs=db.rawQuery("SELECT * FROM emp WHERE id= "+id_search_EditText.getText().toString(),new String[]{});
                if (rs.moveToFirst() == false) {
                    Toast.makeText(this,"invalid ID (not exists)",Toast.LENGTH_SHORT).show();
                }else{
                    makeRec();
                    AlertDialog.Builder ab=new AlertDialog.Builder(this).setTitle("Update?")
                            .setMessage(makeRec()).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    done();
                                    id_search_EditText.setText("");
                                }
                            }).setNegativeButton("No",null);

                    AlertDialog  x= ab.create();
                    x.show();

                }

            }

        }
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
    public void done(){
        Intent x=new Intent(this,updatePage.class);
        x.putExtra("id",rs.getString(0));
        startActivity(x);
    }

}