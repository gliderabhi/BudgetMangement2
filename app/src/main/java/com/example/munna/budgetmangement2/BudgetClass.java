package com.example.munna.budgetmangement2;

/**
 * Created by Munna on 1/25/2017.
 */

public class BudgetClass {
    String desc;
    int values;
    String date;
    String category;

    public BudgetClass(){

    }
    public BudgetClass(String category,String desc,int values,String date ){
        this.desc=desc;
        this.category=category;
        this.date=date;
        this.values=values;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getValues() {
        return values;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setValues(int values) {
        this.values = values;
    }
}
