package com.satyampra.foodrecipes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class RecipeDetailsActivity extends AppCompatActivity {
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);

        id=Integer.parseInt(getIntent().getStringExtra("id"));
    }
}