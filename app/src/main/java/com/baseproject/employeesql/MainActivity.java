package com.baseproject.employeesql;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Dbhelper mDbhelper;
    EditText empname,empsalary;
    Spinner mSpinner;
    TextView mTextView;
    Button empadd;
    Button viewemp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDbhelper=new Dbhelper(this);
        empname=findViewById(R.id.editTextName);
        empsalary=findViewById(R.id.editTextSalary);
        mSpinner=findViewById(R.id.spinnerDepartment);
        mTextView=findViewById(R.id.textViewViewEmployees);
        empadd=findViewById(R.id.buttonAddEmployee);
        addData(findViewById(R.id.buttonAddEmployee));

    }

    public void addData(View view){
        empadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               boolean isinserted= mDbhelper.insertdata(empname.getText().toString(),
                        mSpinner.getSelectedItem().toString(),
                        empsalary.getText().toString());

               if(isinserted==true)
               {
                   Toast.makeText(MainActivity.this, "Successfully added", Toast.LENGTH_SHORT).show();
               }
               else
               {
                   Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();
               }
            }
        });

        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(MainActivity.this, list.class));

            }
        });

    }
}
