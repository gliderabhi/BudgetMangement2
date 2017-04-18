package com.example.munna.budgetmangement2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class DataInsert extends AppCompatActivity {
    EditText description;
    EditText value;
    String date;
    String desc;
    String selectedValue;
    Spinner AreasOfexpense,Dayspinner,monthspinner,yearspinner;
    String AreasOfExpenses[]={"Food","Clothing","Study Material","Borrowed","Money Transferred","Vehicle Management","Money Returned"};
    String day[]={"1","2","3","4","5",
                  "6","7","8","9","10",
                  "11","12","13","14","15",
                  "16","17","18","19","20",
                  "21","22","23","24","25",
                  "26","27","28","29","30","31"};
    String month[]={"1","2","3","4","5","6","7","8","9","10","11","12"};
    String year[]={"2016","2017","2018","2019"};
    int values;
    ArrayAdapter<String> myAdaptor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_insert);
        description=(EditText)findViewById(R.id.ExpensesDescriptionText);
        value=(EditText)findViewById(R.id.ExpenseValueText);

       date=null;

        AreasOfexpense=(Spinner)findViewById(R.id.MoneyHandleList);
         myAdaptor=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,AreasOfExpenses);
        myAdaptor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        AreasOfexpense.setAdapter(myAdaptor);


        Dayspinner=(Spinner)findViewById(R.id.DateSpinner);
        myAdaptor=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,day);
        myAdaptor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Dayspinner.setAdapter(myAdaptor);

        monthspinner=(Spinner)findViewById(R.id.MonthSpinner);
         myAdaptor=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,month);
        myAdaptor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        monthspinner.setAdapter(myAdaptor);

        yearspinner=(Spinner)findViewById(R.id.YearSpinner);
        myAdaptor=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,year);
        myAdaptor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        yearspinner.setAdapter(myAdaptor);

    }
    public void AddValues(View v){
        DbHelper db;
        desc=description.getText().toString();
        if(!desc.matches("")){
        selectedValue=AreasOfexpense.getSelectedItem().toString();
        if(value.getText().toString().matches("")){
            Toast.makeText(getApplicationContext(),"Please enter some value",Toast.LENGTH_LONG).show();
        }else {
            values = Integer.valueOf(value.getText().toString());

            date = Dayspinner.getSelectedItem().toString() + "/"
                    + monthspinner.getSelectedItem().toString() + "/"
                    + yearspinner.getSelectedItem().toString();
            Toast.makeText(getApplicationContext(), date, Toast.LENGTH_LONG).show();
            if (selectedValue == "Money Transferred" || selectedValue == "Money Returned") {
                values = values * (-1);
            }
        }

        db=new DbHelper(this);
        db.insertExpenditure(selectedValue,desc,date,values);
        changeToview();
        }else{
            Toast.makeText(getApplicationContext(),"Please enter some description",Toast.LENGTH_LONG).show();
        }
    }

    public void changeToview(){
        Intent i=new Intent(DataInsert.this,ViewTable.class);
        startActivity(i);
    }
}
