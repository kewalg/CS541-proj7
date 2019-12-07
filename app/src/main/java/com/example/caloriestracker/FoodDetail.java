package com.example.caloriestracker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import data.DatabaseHelper;
import model.Food;

public class FoodDetail extends AppCompatActivity {


    private TextView foodName, calories, dateTaken;
    Button delete_btn;
    private int foodID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);


        foodName = findViewById(R.id.tv_foodname);
        calories = findViewById(R.id.tv_foodcal);
        dateTaken = findViewById(R.id.tv_date);
        delete_btn = findViewById(R.id.btn_delete);

        Food food = (Food) getIntent().getSerializableExtra("userObj");
        foodName.setText(food.getFoodName());
        calories.setText(String.valueOf(food.getCalories()));
        dateTaken.setText(food.getRecordDate());
        foodID = food.getFoodId();

        delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(FoodDetail.this);
                alert.setTitle("Delete?");
                alert.setMessage("Want to delete this?");
                alert.setNegativeButton("No", null);
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DatabaseHelper dba = new DatabaseHelper(getApplicationContext());
                        dba.deleteFood(foodID);
                        Toast.makeText(FoodDetail.this, "Deleted", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(FoodDetail.this, FoodList.class));
                        FoodDetail.this.finish();
                    }
                });
                alert.show();
            }
        });
    }
}
