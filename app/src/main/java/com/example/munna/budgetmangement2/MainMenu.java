package com.example.munna.budgetmangement2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainMenu extends AppCompatActivity {
    Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

    }
    public void InsertSpent(View v){
         i=new Intent(getApplicationContext(),DataInsert.class);
        startActivity(i);
    }
    public void Total(View v){
         i=new Intent(getApplicationContext(),ViewTable.class);
        startActivity(i);
    }

}
