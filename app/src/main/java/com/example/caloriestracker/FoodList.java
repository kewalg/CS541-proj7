package com.example.caloriestracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.ArrayList;

import data.CustomListViewAdapter;
import data.DatabaseHelper;
import model.Food;
import util.Utils;

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

        String formattedValue = Utils.formatNumber(calsValue);
        String formattedItems = Utils.formatNumber(totalItems);

        totalCals.setText("Total Calories: " + formattedValue);
        totalFoods.setText("Total Food: " + formattedItems);


        for (int i = 0; i < foodsFromDB.size(); i++) {
            String name = foodsFromDB.get(i).getFoodName();
            String dateText = foodsFromDB.get(i).getRecordDate();
            int cals = foodsFromDB.get(i).getCalories();
            int foodID = foodsFromDB.get(i).getFoodId();

            Log.v("foods id:", String.valueOf(foodID));


            myFood = new Food();
            myFood.setFoodName(name);
            myFood.setRecordDate(dateText);
            myFood.setCalories(cals);
            myFood.setFoodId(foodID);

            dbFoods.add(myFood);
        }

        dba.close();

        foodAdapter = new CustomListViewAdapter(FoodList.this, R.layout.activity_custom_list_view, dbFoods);
        listView.setAdapter(foodAdapter);
        foodAdapter.notifyDataSetChanged();

    }
}
