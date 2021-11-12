package com.example.crud_units;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //initializing widgets
EditText txtUnitCode,txtUnitName,txtSem,txtYear,txtLec;
Button add,show,delete,update,cancel;
SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //binding
        txtUnitCode=findViewById(R.id.txtUnitCode);
        txtUnitName=findViewById(R.id.txtUnitName);
        txtSem=findViewById(R.id.txtSem);
        txtYear=findViewById(R.id.txtYear);
        txtLec=findViewById(R.id.txtLec);

        add=findViewById(R.id.btnAdd);
        show=findViewById(R.id.btnShow);
        update=findViewById(R.id.btnUpdate);
        delete=findViewById(R.id.btnDelete);
        cancel=findViewById(R.id.btnCancel);

        //create an object for the dataBaseHelper
        DatabaseHelper db=new DatabaseHelper(MainActivity.this);

        //add onClick handlers for buttons

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Unit_code = txtUnitCode.getText().toString();
                String Unit_name = txtUnitName.getText().toString();
                Float Semester_stage = Float.parseFloat(txtSem.getText().toString());
                String Year = txtYear.getText().toString();
                String Lecturer = txtLec.getText().toString();

                Boolean insert = db.insertData(Unit_code, Unit_name, Semester_stage, Year, Lecturer);
                
                    if (insert == true) {

                        Toast.makeText(MainActivity.this, "Data inserted", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Data not inserted!!", Toast.LENGTH_SHORT).show();
                    }

            }
        });
      show.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Cursor cursor=db.readData();
              if (cursor.getCount()==0){
                  Toast.makeText(MainActivity.this, "No entries to display", Toast.LENGTH_SHORT).show();
              }
              else{
                  StringBuffer buffer=new StringBuffer();
                  while (cursor.moveToNext()){
                      buffer.append("Unit Code : "+cursor.getString(0)+"\n");
                      buffer.append("Unit Name : "+cursor.getString(1)+"\n");
                      buffer.append("Semester  : "+cursor.getFloat(2)+"\n");
                      buffer.append("Year : "+cursor.getString(0)+"\n");
                      buffer.append("Lecturer : "+cursor.getString(0)+"\n\n\n");
                  }

                  AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                  builder.setTitle("UNIT DETAILS");
                  builder.setCancelable(true);
                  builder.setMessage(buffer.toString());
                  builder.show();
              }
          }
      });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Unit_code=txtUnitCode.getText().toString();
                String Unit_name=txtUnitName.getText().toString();
                Float Semester_stage=Float.parseFloat(txtSem.getText().toString());
                String Year=txtYear.getText().toString();
                String Lecturer=txtLec.getText().toString();

                Boolean update=db.updateData(Unit_code, Unit_name,Semester_stage,Year,Lecturer);

                if (update==true){
                    Toast.makeText(MainActivity.this, "Data updated", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(MainActivity.this, "Data not updated!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String Unit_code=txtUnitCode.getText().toString();
                Boolean update=db.deleteData(Unit_code);
                if (update==true){
                    Toast.makeText(MainActivity.this, "Data deleted", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(MainActivity.this, "Data not deleted!!", Toast.LENGTH_SHORT).show();
                }
            }

        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                System.exit(0);
            }
        });

    }
}