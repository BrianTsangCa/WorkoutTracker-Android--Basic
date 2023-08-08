package com.example.workouttracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.workouttracker.CalorieBurned.model.CalorieBurnedDao;
import com.example.workouttracker.LoginAndRegisterAcitivties.LoginOrRegisterActivity;
import com.example.workouttracker.LoginAndRegisterAcitivties.LoginPageActivity;
import com.example.workouttracker.database.CalorieBurnedDatabase;
import com.example.workouttracker.database.UserDatabase;
import com.example.workouttracker.fragment.RankFragment;
import com.example.workouttracker.fragment.HomeFragment;
import com.example.workouttracker.fragment.StatisticFragment;
import com.example.workouttracker.fragment.WorkoutFragment;
import com.example.workouttracker.user.model.UserDao;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    private FirebaseAuth firebaseAuth;
    CalorieBurnedDao calorieBurnedDao;
    CalorieBurnedDatabase calorieBurnedDatabase;
    UserDao userDao;
    UserDatabase userDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        replaceFragment(new HomeFragment());
        firebaseAuth = FirebaseAuth.getInstance();
        calorieBurnedDatabase = Room.databaseBuilder
                (this, CalorieBurnedDatabase.class, "calorieBurned.db").build();
        calorieBurnedDao = calorieBurnedDatabase.calorieBurnedDao();
        userDatabase = Room.databaseBuilder
                (this, UserDatabase.class, "user.db").build();
        userDao = userDatabase.userDao();

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_home:
                        replaceFragment(new HomeFragment());
                        break;
                    case R.id.action_workout:
                        replaceFragment(new WorkoutFragment());
                        break;
                    case R.id.action_rank:
                        replaceFragment(new RankFragment());
                        break;
                    case R.id.action_statistic:
                        replaceFragment(new StatisticFragment());
                        break;
                }
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.optiontab, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_logout) {
            firebaseAuth.signOut();
            ExecutorService executorService = Executors.newSingleThreadExecutor();
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    userDao.deleteAllUsers();
                    calorieBurnedDao.deleteAll();
                }
            });
            startActivity(new Intent(this, LoginOrRegisterActivity.class));
            finish();
        }
        return true;
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment, fragment);
        fragmentTransaction.commit();
    }
}