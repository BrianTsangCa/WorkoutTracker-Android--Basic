package com.example.workouttracker.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.workouttracker.CalorieBurned.model.CalorieBurned;
import com.example.workouttracker.R;

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
        float calorieBurned_value = calorieBurned.getWorkoutCalorieBurned();
        float target = 1000f;
        float percentage = calorieBurned_value / target * 100;
        holder.progressBar.setProgress((int) percentage);
        holder.txtView_date.setText(calorieBurned.getDateYear() + "/" + calorieBurned.getDateMonth() + "/" + calorieBurned.getDateDay());
        holder.txtView_percentage.setText(calorieBurned_value + "");
    }

    @Override
    public int getItemCount() {
        return calorieBurnedList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ProgressBar progressBar;
        TextView txtView_percentage, txtView_date;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            progressBar = itemView.findViewById(R.id.progressBar);
            txtView_percentage = itemView.findViewById(R.id.txtView_calorieBurnedThatDay);
            txtView_date = itemView.findViewById(R.id.txtView_date);
        }
    }

}
