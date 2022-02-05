package com.satyampra.foodrecipes.Listeners;

import com.satyampra.foodrecipes.Model.RandomRecipeApiResponse;

public interface RandomRecipeResponseListener {
    void didFetch(RandomRecipeApiResponse response, String message);

    void didError(String message);
}
