package com.example.munna.budgetmangement2;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ViewTable extends AppCompatActivity {
    CustomAdaptor adaptor;
    DbHelper db;
    ArrayList<String> descr,date,categories;
    ArrayList<Integer> values,expenses;
    TextView remaining,txt;
    Integer r,spent=0;
    Spinner options;
    ArrayAdapter myAdaptor;
    String option[]={"All Categories","Food","Clothing","Study Material","Borrowed","Money Transferred","Vehicle Management","Money Returned"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_table);
        options=(Spinner)findViewById(R.id.TableViewSpinner);
        myAdaptor=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,option);
        myAdaptor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        options.setAdapter(myAdaptor);

        CreateList();
    }
    // Function to populate expenditure list
    public void CreateList() {
        db = new DbHelper(this);
        int p = db.checkTable();
        if (p == 0) {
            date = new ArrayList<String>();
            descr = new ArrayList<String>();
            values = new ArrayList<Integer>();
            categories = new ArrayList<String>();

            descr = db.description();
            values = db.Values();
            date = db.DateTime();
            expenses = db.TotalExpense();
            categories=db.Categories();

            for (int i = 0; i < expenses.size(); ++i) {
                r = expenses.get(i);
                if (r > 0) {
                    spent += r;
                }
            }

            ArrayList<BudgetClass> buget = new ArrayList<>();
            BudgetClass bc;
            for (int i = 0; i < descr.size(); ++i) {
                bc = new BudgetClass();
                bc.setDesc(descr.get(i));
                bc.setValues(values.get(i));
                bc.setDate(date.get(i));
                bc.setCategory(categories.get(i));
                buget.add(bc);
            }
            ListView lst = (ListView) findViewById(R.id.ListDesc);
            adaptor = new CustomAdaptor(buget, getApplicationContext());
            lst.setAdapter(adaptor);

            int rem=db.TotalExpenses().getInt(0)*-1;
            remaining = (TextView) findViewById(R.id.RemainingValue);
            remaining.setText(String.valueOf(rem));

            txt = (TextView) findViewById(R.id.TotalValue);
            txt.setText(String.valueOf(spent));

        }else{
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setMessage("Please enter some values before proceeding");
                    alertDialogBuilder.setPositiveButton("yes",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface arg0, int arg1) {
                                    Intent i=new Intent(getApplicationContext(),DataInsert.class);
                                    startActivity(i);
                                }
                            });



            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }
    }









        public void AddMore(View v){
            Intent i=new Intent(getApplicationContext(),DataInsert.class);
            startActivity(i);
        }



}
