package com.example.munna.budgetmangement2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;


public class CustomAdaptor extends ArrayAdapter<BudgetClass> implements View.OnClickListener{

    private ArrayList<BudgetClass> dataSet;
    Context mContext;



    public CustomAdaptor(ArrayList<BudgetClass> data, Context context) {
        super(context, R.layout.list_custom, data);
        this.dataSet = data;
        this.mContext=context;

    }

    @Override
    public void onClick(View v) {

        int position=(Integer) v.getTag();
        Object object= getItem(position);
        BudgetClass dataModel=(BudgetClass) object;


    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflator=LayoutInflater.from(getContext());
        View customRow=inflator.inflate(R.layout.list_custom,parent,false);
        String desc=getItem(position).getDesc();
        String value=String.valueOf(getItem(position).getValues());
        String date=String.valueOf(getItem(position).getDate());
        TextView datetext=(TextView)customRow.findViewById(R.id.DateText);
        TextView descr=(TextView)customRow.findViewById(R.id.DescriptionTextList);
        TextView values=(TextView)customRow.findViewById(R.id.ListValue);
        descr.setText(desc);
        values.setText(value);
        datetext.setText(date);
        return customRow;
    }
}