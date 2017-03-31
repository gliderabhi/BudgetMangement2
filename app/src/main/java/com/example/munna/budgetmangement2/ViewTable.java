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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ViewTable extends AppCompatActivity {
    CustomAdaptor adaptor;
    DbHelper db;
    ArrayList<String> descr,date;
    ArrayList<Integer> values;
    TextView remaining;
    EditText input;
    Integer balance;
    String TableTOShow;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_table);
        sharedPreferences=getSharedPreferences(getString(R.string.Search), Context.MODE_PRIVATE);
        TableTOShow= sharedPreferences.getString(getString(R.string.Search),null);

        CreateList();
    }
    // Function to populate expenditure list
    public void CreateList(){
        db=new DbHelper(this);
        date=new ArrayList<String>();
        descr=new ArrayList<String>();
        values=new ArrayList<Integer>();
        descr=db.description(TableTOShow);
        values=db.Values(TableTOShow);
        date=db.DateTime(TableTOShow);
        remaining=(TextView)findViewById(R.id.RemainingValue);
        ArrayList<BudgetClass> buget=new ArrayList<>();
        BudgetClass bc;
        for(int i=0;i<descr.size();++i){
            bc=new BudgetClass();
            bc.setDesc(descr.get(i));
            bc.setValues(values.get(i));
            bc.setDate(date.get(i));
            buget.add(bc);
        }
        ListView lst=(ListView)findViewById(R.id.ListDesc);
        adaptor= new CustomAdaptor(buget,getApplicationContext());
        lst.setAdapter(adaptor);
        TextView txt=(TextView)findViewById(R.id.TotalValue);
        int r=db.TotalExpenses(TableTOShow).getInt(0);
        remaining.setText(String.valueOf(balance-r));
        txt.setText(String.valueOf(db.TotalExpenses(TableTOShow).getInt(0)));
        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                AlertDialog.Builder bd=new AlertDialog.Builder(ViewTable.this);
                input=new EditText(ViewTable.this);
                input.setInputType(InputType.TYPE_CLASS_NUMBER);
                bd.setView(input);
                bd.setTitle("Please enter a new value for "+descr.get(i)+"or cancel this commentbox");
                bd.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getApplicationContext(),descr.get(i),Toast.LENGTH_SHORT).show();
                    }
                });
                bd.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                bd.show();

            }
        });
    }






        public void AddMore(View v){
            Intent i=new Intent(getApplicationContext(),DataInsert.class);
            startActivity(i);
        }



}
