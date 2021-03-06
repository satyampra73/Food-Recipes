package com.satyampra.foodrecipes;

import android.content.Context;

import com.satyampra.foodrecipes.Listeners.RandomRecipeResponseListener;
import com.satyampra.foodrecipes.Listeners.RecipeDetailsListener;
import com.satyampra.foodrecipes.Listeners.SimilarRecipesListener;
import com.satyampra.foodrecipes.Model.RandomRecipeApiResponse;
import com.satyampra.foodrecipes.Model.RecipeDetailsResponse;
import com.satyampra.foodrecipes.Model.SimilarRecipeResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class RequestManager {
    Context context;
    Retrofit retrofit =new Retrofit.Builder()
            .baseUrl("https://api.spoonacular.com/").addConverterFactory(GsonConverterFactory.create()).build();

    public RequestManager(Context context) {
        this.context = context;
    }

    public void getRandomRecipes(RandomRecipeResponseListener listener,List<String> tags ){
            CallRandomRecipes callRandomRecipes=retrofit.create(CallRandomRecipes.class);
            Call<RandomRecipeApiResponse> call=callRandomRecipes.callRandomRecipes(context.getString(R.string.api_key),"10", tags);
            call.enqueue(new Callback<RandomRecipeApiResponse>() {
                @Override
                public void onResponse(retrofit2.Call<RandomRecipeApiResponse> call, Response<RandomRecipeApiResponse> response) {
                    if(!response.isSuccessful()){
                        listener.didError(response.message());
                        return;
                    }
                    listener.didFetch(response.body(),response.message());
                }

                @Override
                public void onFailure(retrofit2.Call<RandomRecipeApiResponse> call, Throwable t) {
                    listener.didError(t.getMessage());
                }
            });
    }

    public void getRecipeDetails(RecipeDetailsListener listener,int id){
        CallRecipeDetails callRecipeDetails=retrofit.create(CallRecipeDetails.class);
        Call<RecipeDetailsResponse> call=callRecipeDetails.callRecipeDetails(id,context.getString(R.string.api_key));
        call.enqueue(new Callback<RecipeDetailsResponse>() {
            @Override
            public void onResponse(retrofit2.Call<RecipeDetailsResponse> call, Response<RecipeDetailsResponse> response) {
                if(!response.isSuccessful()){
                    listener.didError(response.message());
                    return;
                }
           listener.didFetch(response.body(), response.message());
            }

            @Override
            public void onFailure(retrofit2.Call<RecipeDetailsResponse> call, Throwable t) {
                listener.didError(t.getMessage());
            }
        });
    }

    public void getSimilarRecipes(SimilarRecipesListener listener,int id){
        CallSimilarRecipes callSimilarRecipes =retrofit.create(CallSimilarRecipes.class);
        Call<List<SimilarRecipeResponse>> call=callSimilarRecipes.callSimilarRecipe(id,"6",context.getString(R.string.api_key));
                call.enqueue(new Callback<List<SimilarRecipeResponse>>() {
                    @Override
                    public void onResponse(Call<List<SimilarRecipeResponse>> call, Response<List<SimilarRecipeResponse>> response) {
                        if(!response.isSuccessful()){
                            listener.didError(response.message());
                            return;
                        }
                        listener.didFetch(response.body(), response.message());

                    }

                    @Override
                    public void onFailure(Call<List<SimilarRecipeResponse>> call, Throwable t) {
                        listener.didError(t.getMessage());
                    }
                });
    }

    private interface CallRandomRecipes{
        @GET("recipes/random")
        retrofit2.Call<RandomRecipeApiResponse> callRandomRecipes(
                @Query("apiKey") String apiKey,
                @Query("number") String number,
                @Query("tags") List<String> tags
        );
    }

    private interface CallRecipeDetails{
            @GET("recipes/{id}/information")
            retrofit2.Call<RecipeDetailsResponse> callRecipeDetails(
                @Path("id") int id,
                @Query("apiKey") String apiKey
            );
    }
    private interface CallSimilarRecipes{
        @GET("recipes/{id}/similar")
        Call<List<SimilarRecipeResponse>> callSimilarRecipe(
                @Path("id") int id,
                @Query("number") String number,
                @Query("apiKey") String apiKey
        );
    }
}
