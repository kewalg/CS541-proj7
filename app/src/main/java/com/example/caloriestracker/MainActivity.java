package com.example.caloriestracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import data.DatabaseHelper;
import model.Food;

public class MainActivity extends AppCompatActivity {

    private EditText foodName;
    private EditText foodCals;
    Button btn_submit;
    private DatabaseHelper dba;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dba = new DatabaseHelper(MainActivity.this);
        foodName = findViewById(R.id.edt_food);
        foodCals = findViewById(R.id.edt_calories);
        btn_submit = findViewById(R.id.submit_btn);


        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveDatatoDB();
            }
        });
    }

    private void saveDatatoDB() {

        Food food = new Food();
        String name = foodName.getText().toString().trim();
        String calsString = foodCals.getText().toString().trim();
        int cals = Integer.parseInt(calsString);
        if (name.equals("") || calsString.equals("")) {
            Toast.makeText(this, "No empty fields allowed", Toast.LENGTH_SHORT).show();

        } else {
            food.setFoodName(name);
            food.setCalories(cals);
            dba.addFood(food);
            dba.close();


            foodName.setText("");
            foodCals.setText("");

            startActivity(new Intent(MainActivity.this, FoodList.class));
        }
    }
}
