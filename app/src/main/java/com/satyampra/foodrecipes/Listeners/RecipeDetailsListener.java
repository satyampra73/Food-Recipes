package com.satyampra.foodrecipes.Listeners;

import com.satyampra.foodrecipes.Model.RecipeDetailsResponse;

public interface RecipeDetailsListener {
     void didFetch(RecipeDetailsResponse response,String message);
     void didError(String message);
}
