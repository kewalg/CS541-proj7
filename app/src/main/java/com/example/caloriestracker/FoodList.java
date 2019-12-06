package com.example.caloriestracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

import data.CustomListViewAdapter;
import data.DatabaseHelper;
import model.Food;

public class FoodList extends AppCompatActivity {

    private DatabaseHelper dba;
    private ArrayList<Food> dbFoods = new ArrayList<>();
    private CustomListViewAdapter foodAdapter;
    private ListView listView;
    private Food myFood;
    private TextView totalCals, totalFoods;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        listView = findViewById(R.id.lv_fooditems);
        totalCals = findViewById(R.id.total_calories);
        totalFoods = findViewById(R.id.total_fooditems);
        refreshData();

    }

    private void refreshData() {
        dbFoods.clear();
        dba = new DatabaseHelper(getApplicationContext());
        ArrayList<Food> foodsFromDB = dba.getFoods();
        int calsValue = dba.totalCalories();
        int totalItems = dba.getTotalItems();
    }
}
