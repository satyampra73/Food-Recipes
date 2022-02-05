package com.satyampra.foodrecipes.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.satyampra.foodrecipes.Model.Recipe;
import com.satyampra.foodrecipes.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RandomRecipeAdapter extends RecyclerView.Adapter<RandomRecipeViewHolder> {
    Context context;
    List<Recipe> list;

    public RandomRecipeAdapter(Context context, List<Recipe> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RandomRecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RandomRecipeViewHolder(LayoutInflater.from(context).inflate(R.layout.list_random_recipe,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RandomRecipeViewHolder holder, int position) {
        holder.textViewTitle.setText(list.get(position).title);
        holder.textViewTitle.setSelected(true);
        holder.textView_likes.setText(list.get(position).aggregateLikes+" Likes");
        holder.textView_servings.setText(list.get(position).servings+" Servings");
        holder.textView_time.setText(list.get(position).readyInMinutes+" Minutes");
        Picasso.get().load(list.get(position).image).into(holder.imageViewFood);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
class RandomRecipeViewHolder extends RecyclerView.ViewHolder{
    CardView random_list_container;
    TextView textViewTitle,textView_servings,textView_likes,textView_time;
    ImageView imageViewFood;
    public RandomRecipeViewHolder(@NonNull View itemView) {
        super(itemView);
        random_list_container=itemView.findViewById(R.id.random_list_container);
        textViewTitle=itemView.findViewById(R.id.textViewTitle);
        textView_servings=itemView.findViewById(R.id.textView_servings);
        textView_likes=itemView.findViewById(R.id.textView_likes);
        textView_time=itemView.findViewById(R.id.textView_time);
        imageViewFood=itemView.findViewById(R.id.imageViewFood);
    }
}
