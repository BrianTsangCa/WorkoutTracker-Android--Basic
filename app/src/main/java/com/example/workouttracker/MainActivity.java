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
    ActivityMainBinding binding;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    UserDatabase udb;
    private List<Workout> WorkOutList=new ArrayList<>();
    private List<User> userList=new ArrayList<>();
    private List<CalorieBurned> calorieBurned=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        View view=binding.getRoot();
        setContentView(view);
        layoutManager=new LinearLayoutManager(this);
        fetchWorkout();
        fetchUser();
        udb= Room.databaseBuilder
                (getApplicationContext(),UserDatabase.class,"user.db").build();
        UserDao userDao=udb.userDao();


        ExecutorService executorService= Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                userDao.insertUsersfromList(userList);
                List<User> AllDBStudents=userDao.GetAllUsers();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
//                        binding.listViewUsers
//                                .setAdapter(new UserAdapter(AllDBStudents));
                    }
                });
            }
        });




        adapter=new RecyclerAdapter(WorkOutList,this);
        binding.workoutRecyclerview.setLayoutManager(layoutManager);
        binding.workoutRecyclerview.setAdapter(adapter);

    }
    private void fetchWorkout(){
        WorkOutList.add(new Workout("Ski","Ski","300"));
    }
    private void fetchUser(){
        userList.add(new User("email","userName",100));
    }
}