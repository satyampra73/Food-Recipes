package com.satyampra.foodrecipes.Listeners;
import com.satyampra.foodrecipes.Model.SimilarRecipeResponse;

import java.util.List;

public interface SimilarRecipesListener {
    void didFetch(List<SimilarRecipeResponse> response, String message);

    void didError(String message);
}
