package com.example.workouttracker.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.workouttracker.CalorieBurned.model.CalorieBurned;
import com.example.workouttracker.R;
import com.example.workouttracker.WorkoutCountingActivity;
import com.example.workouttracker.workout.model.Workout;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class CalorieBurnedRecyclerAdapter extends RecyclerView.Adapter<CalorieBurnedRecyclerAdapter.ViewHolder> {
    private List<CalorieBurned> calorieBurnedList;
    private Context context;

    public CalorieBurnedRecyclerAdapter(List<CalorieBurned> calorieBurnedList, Context context) {
        this.calorieBurnedList = calorieBurnedList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.calorie_burned_layout, parent, false);
        CalorieBurnedRecyclerAdapter.ViewHolder viewHolder = new CalorieBurnedRecyclerAdapter.ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CalorieBurned calorieBurned = calorieBurnedList.get(position);
        ArrayList<PieEntry> entriers = new ArrayList<>();

        float calorieBurned_value = calorieBurned.getWorkoutCalorieBurned();
        float target = 1000f;
        float calorieBurned_left;
        if (calorieBurned_value <= target) {
            calorieBurned_left = target - calorieBurned_value;
        } else {
            calorieBurned_left = 0f;
        }
        int screenWidth = context.getResources().getDisplayMetrics().widthPixels;
        int screenHeight = context.getResources().getDisplayMetrics().heightPixels;
        int chartSize = Math.min(screenWidth, screenHeight) / 2;
        ViewGroup.LayoutParams layoutParams = holder.pieChart.getLayoutParams();
        layoutParams.width = chartSize;
        layoutParams.height = chartSize;
        holder.pieChart.setLayoutParams(layoutParams);
        entriers.add(new PieEntry(calorieBurned_value, ""));
        entriers.add(new PieEntry(calorieBurned_left, ""));
        PieDataSet pieDataSet = new PieDataSet(entriers, "Day");
        pieDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        PieData pieData = new PieData(pieDataSet);
        holder.pieChart.setData(pieData);
        holder.pieChart.getDescription().setEnabled(false);
        holder.pieChart.animateY(1000);
        holder.pieChart.invalidate();

    }

    @Override
    public int getItemCount() {
        return calorieBurnedList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        PieChart pieChart;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            pieChart = itemView.findViewById(R.id.chart);
        }
    }

}
