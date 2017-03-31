package com.example.munna.budgetmangement2;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ChangeTableActivity extends AppCompatActivity {

    EditText tableName,balance;
    String name;
    int balanceNum;
    TableOfMonths tb;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_table);

        tableName=(EditText)findViewById(R.id.TableName);
        balance=(EditText)findViewById(R.id.InitialBalanceValue);
       tb=new TableOfMonths(ChangeTableActivity.this);
    }
   public void Create_Table(View v){
       name=tableName.getText().toString();
       balanceNum=Integer.valueOf(balance.getText().toString());
     // for getting current table
       sharedPreferences=getSharedPreferences(getString(R.string.TableName), Context.MODE_PRIVATE);
       SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(getString(R.string.TableName),name );
       editor.commit();
       //For Searching purpose
       sharedPreferences=getSharedPreferences(getString(R.string.Search),Context.MODE_PRIVATE);
       editor=sharedPreferences.edit();
       editor.putString(getString(R.string.Search),name);

       Toast.makeText(getApplicationContext(),name+String.valueOf(balanceNum),Toast.LENGTH_LONG).show();
      tb.tableCreate(name,balanceNum);
   }
}
