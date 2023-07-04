package com.example.workouttracker;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.workouttracker.workout.model.Workout;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private List<Workout> WorkOutList;
    private Context context;

    public RecyclerAdapter(List<Workout> workOutList, Context context) {
        WorkOutList = workOutList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.cardlayout,parent,false);
        ViewHolder viewHolder=new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Workout workout=WorkOutList.get(position);
        holder.card_title.setText(workout.getTitle());
        holder.card_caloriePerHour.setText(workout.getCaloriePerHour());
        holder.card_category.setText(workout.getCategory());
        holder.card_difficulty.setText(workout.getDifficulty());
        holder.card_image.setImageResource(R.drawable.ic_launcher_background);
    }

    @Override
    public int getItemCount() {
        return WorkOutList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView card_image;
        TextView card_title,card_caloriePerHour,card_category,card_difficulty;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            card_image=itemView.findViewById(R.id.card_image);
            card_title=itemView.findViewById(R.id.card_title);
            card_caloriePerHour=itemView.findViewById(R.id.card_caloriePerHour);
            card_category=itemView.findViewById(R.id.card_category);
            card_difficulty=itemView.findViewById(R.id.card_difficulty);
        }
    }
}
