package com.satyampra.foodrecipes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.satyampra.foodrecipes.Adapters.IngredientsAdapter;
import com.satyampra.foodrecipes.Listeners.RecipeDetailsListener;
import com.satyampra.foodrecipes.Model.RecipeDetailsResponse;
import com.squareup.picasso.Picasso;

public class RecipeDetailsActivity extends AppCompatActivity {
    int id;
    TextView textView_meal_name,textView_meal_source,textView_meal_summery;
    RecyclerView recycler_meal_Ingredients;
    ImageView imageView_meal_image;
    RequestManager manager;
    ProgressDialog dialog;
    IngredientsAdapter ingredientsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);
        
        findViews();
        id=Integer.parseInt(getIntent().getStringExtra("id"));
        manager=new RequestManager(this);
        manager.getRecipeDetails(recipeDetailslistener,id);
        dialog=new ProgressDialog(this);
        dialog.setTitle("Loading Title");
        dialog.show();
    }

    private void findViews() {
        textView_meal_name=findViewById(R.id.textView_meal_name);
        textView_meal_source=findViewById(R.id.textView_meal_source);
        textView_meal_summery=findViewById(R.id.textView_meal_summery);
        recycler_meal_Ingredients=findViewById(R.id.recycler_meal_Ingredients);
        imageView_meal_image=findViewById(R.id.imageView_meal_image);
    }

    private final RecipeDetailsListener recipeDetailslistener=new RecipeDetailsListener() {
        @Override
        public void didFetch(RecipeDetailsResponse response, String message) {
            dialog.dismiss();
            textView_meal_name.setText(response.title);
            textView_meal_source.setText(response.sourceName);
            textView_meal_summery.setText(response.summary);
            Picasso.get().load(response.image).into(imageView_meal_image);

            recycler_meal_Ingredients.setHasFixedSize(true);
            recycler_meal_Ingredients.setLayoutManager(new LinearLayoutManager(RecipeDetailsActivity.this, LinearLayoutManager.HORIZONTAL,false));
            ingredientsAdapter=new IngredientsAdapter(RecipeDetailsActivity.this,response.extendedIngredients);
            recycler_meal_Ingredients.setAdapter(ingredientsAdapter);
        }

        @Override
        public void didError(String message) {
            Toast.makeText(RecipeDetailsActivity.this, message, Toast.LENGTH_SHORT).show();

        }
    };
}