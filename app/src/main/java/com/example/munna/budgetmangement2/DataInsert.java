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

public class DataInsert extends AppCompatActivity {
    EditText description,value,date;
    String desc;
    String TableTOShow,selectedValue;
    SharedPreferences sharedPreferences;
    Spinner AreasOfexpense;
    String AreasOfExpenses[]={"Food","Clothing","Study Material","Borrowed","Money Transferred","Vehicle Management","Money Returned"};
    int values;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_insert);
        description=(EditText)findViewById(R.id.ExpensesDescriptionText);
        value=(EditText)findViewById(R.id.ExpenseValueText);

        AreasOfexpense=(Spinner)findViewById(R.id.MoneyHandleList);
        ArrayAdapter<String> myAdaptor=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,AreasOfExpenses);
        myAdaptor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        AreasOfexpense.setAdapter(myAdaptor);
        AreasOfexpense.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedValue=parent.getItemAtPosition(position).toString();
            }
        });
        sharedPreferences=getSharedPreferences(getString(R.string.Search), Context.MODE_PRIVATE);
        TableTOShow= sharedPreferences.getString(getString(R.string.Search),null);

    }
    public void AddValues(View v){
        DbHelper db;
        desc=description.getText().toString();
        values=Integer.valueOf(value.getText().toString());
        if (selectedValue=="Money Transferred"|| selectedValue=="Money Returned"){
            values=values*(-1);
        }

        db=new DbHelper(this);
        db.insertExpenditure(selectedValue,desc,values,TableTOShow);
        changeToview();
    }

    public void changeToview(){
        Intent i=new Intent(DataInsert.this,ViewTable.class);
        startActivity(i);
    }
}
