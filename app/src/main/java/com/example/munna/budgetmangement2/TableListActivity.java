package com.example.munna.budgetmangement2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class TableListActivity extends AppCompatActivity {

    ListView lst;
    ArrayList<String> tableList;
    TableOfMonths db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_list);

        db=new TableOfMonths(getApplicationContext());

        lst=(ListView)findViewById(R.id.TableList);
        tableList=new ArrayList<>();
        tableList=db.TableName();
        final ListAdapter newAdaptor=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,tableList);
        lst.setAdapter(newAdaptor);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }
}
