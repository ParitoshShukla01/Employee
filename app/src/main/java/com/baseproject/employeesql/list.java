package com.baseproject.employeesql;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class list extends AppCompatActivity {

    List<Employee> employeeList;
    SQLiteDatabase mDatabase;
    ListView listViewEmployees;
    EmployeeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        listViewEmployees=(ListView)findViewById(R.id.listViewEmployees);
        employeeList= new ArrayList<>();

        mDatabase = openOrCreateDatabase(Dbhelper.DATABASE_NAME, MODE_PRIVATE, null);
        showEmployeesFromDatabase();
    }

    public void showEmployeesFromDatabase(){
        Cursor cursorEmployees = mDatabase.rawQuery("SELECT * FROM employeetable", null);

        if (cursorEmployees.moveToFirst()) {

            do {
                employeeList.add(new Employee(
                        cursorEmployees.getInt(0),
                        cursorEmployees.getString(1),
                        cursorEmployees.getString(2),
                        cursorEmployees.getInt(3)
                ));
            } while (cursorEmployees.moveToNext());
        }
        cursorEmployees.close();
        adapter=new EmployeeAdapter(this,R.layout.viewlist,employeeList,mDatabase);
        listViewEmployees.setAdapter(adapter);

    }
}
