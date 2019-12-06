package com.example.caloriestracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import model.Food;

public class FoodDetail extends AppCompatActivity {


    private TextView foodName, calories, dateTaken;
    private int foodID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);


        foodName = findViewById(R.id.tv_foodname);
        calories = findViewById(R.id.tv_foodcal);
        dateTaken = findViewById(R.id.tv_date);

        Food food = (Food) getIntent().getSerializableExtra("userObj");
        foodName.setText(food.getFoodName());
        calories.setText(String.valueOf(food.getCalories()));
        dateTaken.setText(food.getRecordDate());
        foodID = food.getFoodId();


    }
}
