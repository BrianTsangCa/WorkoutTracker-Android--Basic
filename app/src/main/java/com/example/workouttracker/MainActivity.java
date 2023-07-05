package com.example.workouttracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;

import com.example.workouttracker.CalorieBurned.model.CalorieBurned;
import com.example.workouttracker.database.UserDatabase;
import com.example.workouttracker.databinding.ActivityMainBinding;
import com.example.workouttracker.user.model.User;
import com.example.workouttracker.user.model.UserDao;
import com.example.workouttracker.workout.model.Workout;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    UserDatabase udb;
    private List<Workout> WorkOutList=new ArrayList<>();
    //    private List<CalorieBurned> calorieBurned=new ArrayList<>();
    RecyclerView workoutRecyclerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        workoutRecyclerview=findViewById(R.id.workout_recyclerview);
        fetchWorkout();
        udb= Room.databaseBuilder
                (getApplicationContext(),UserDatabase.class,"user.db").build();
        UserDao userDao=udb.userDao();


        ExecutorService executorService= Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                    }
                });
            }
        });
        workoutRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        workoutRecyclerview.setAdapter(new RecyclerAdapter(WorkOutList,this));

    }
    private void fetchWorkout(){
        WorkOutList.add(new Workout("Ski","Ski","300"));
    }
}