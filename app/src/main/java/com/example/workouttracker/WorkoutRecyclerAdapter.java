package com.example.workouttracker;


import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.workouttracker.CalorieBurned.model.CalorieBurned;
import com.example.workouttracker.workout.model.Workout;

import java.util.List;

public class WorkoutRecyclerAdapter extends RecyclerView.Adapter<WorkoutRecyclerAdapter.ViewHolder> {
    private List<Workout> WorkOutList;
    private Context context;

    public WorkoutRecyclerAdapter(List<Workout> workOutList, Context context) {
        WorkOutList = workOutList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.workout_cardlayout,parent,false);
        ViewHolder viewHolder=new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Workout workout=WorkOutList.get(position);
        holder.card_name.setText(workout.getName());
        holder.card_calories_per_hour.setText("Calorie Burned per hour:"+workout.getCalories_per_hour());
        holder.card_category.setText(workout.getCategory());
        holder.card_difficulty.setText(workout.getDiffculty().toString());
        holder.card_difficulty.setBackgroundColor(Color.parseColor("#00FF00"));
        if(workout.getDiffculty().toString().equals("Easy")){
            holder.card_difficulty.setBackgroundColor(Color.parseColor("#00FF00"));
        }else if(workout.getDiffculty().toString().equals("Medium")){
            holder.card_difficulty.setBackgroundColor(Color.parseColor("#FFA500"));
        }else{
            holder.card_difficulty.setBackgroundColor(Color.parseColor("#FF0000"));
        }
        holder.card_image.setImageResource(workout.getWorkoutImg());
    }

    @Override
    public int getItemCount() {
        return WorkOutList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView card_image;
        TextView card_name,card_calories_per_hour,card_category,card_difficulty;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            card_image=itemView.findViewById(R.id.card_image);
            card_name=itemView.findViewById(R.id.card_name);
            card_calories_per_hour=itemView.findViewById(R.id.card_calories_per_hour);
            card_category=itemView.findViewById(R.id.card_category);
            card_difficulty=itemView.findViewById(R.id.card_difficulty);
        }
    }
}
