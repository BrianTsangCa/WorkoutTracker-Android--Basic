package com.example.workouttracker;


import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.workouttracker.workout.model.WorkoutHistories;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private ArrayList<WorkoutHistories> WorkOutList;
    private Context context;

    public class ViewHolder extends RecyclerView.ViewHolder{

    }
}
