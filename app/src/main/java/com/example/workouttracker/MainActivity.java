package com.example.workouttracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.workouttracker.databinding.ActivityMainBinding;
import com.example.workouttracker.workout.model.Workout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    private ArrayList<Workout> WorkOutList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        View view=binding.getRoot();
        setContentView(view);
        layoutManager=new LinearLayoutManager(this);
        fetchWorkout();



        adapter=new RecyclerAdapter(WorkOutList,this);
        binding.workoutRecyclerview.setLayoutManager(layoutManager);
        binding.workoutRecyclerview.setAdapter(adapter);
    }
    private void fetchWorkout(){
        WorkOutList.add(new Workout("Ski","Ski","300"));
    }
}