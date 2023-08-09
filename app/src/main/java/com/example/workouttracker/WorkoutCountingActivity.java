package com.example.workouttracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.workouttracker.CalorieBurned.model.CalorieBurned;
import com.example.workouttracker.CalorieBurned.model.CalorieBurnedDao;
import com.example.workouttracker.LoginAndRegisterAcitivties.RegisterPageActivity;
import com.example.workouttracker.database.CalorieBurnedDatabase;
import com.example.workouttracker.database.UserDatabase;
import com.example.workouttracker.user.model.User;
import com.example.workouttracker.user.model.UserDao;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Calendar;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WorkoutCountingActivity extends AppCompatActivity {
    TextView txtView_name, txtView_time;
    ImageButton btn_start_stop;
    int second;
    boolean isCounting = false;
    long startTime = 0;
    Runnable runnable;
    Handler handler = new Handler(Looper.myLooper());

    CalorieBurnedDao calorieBurnedDao;
    CalorieBurnedDatabase calorieBurnedDatabase;
    FirebaseAuth firebaseAuth;
    FirebaseUser user;
    CalorieBurned calorieBurnedData_original;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_counting);
        txtView_name = findViewById(R.id.txtView_name);
        txtView_time = findViewById(R.id.txtView_time);
        btn_start_stop = findViewById(R.id.btn_start_stop);
        calorieBurnedDatabase = Room.databaseBuilder(getApplicationContext(), CalorieBurnedDatabase.class, "calorieBurned.db").build();
        calorieBurnedDao = calorieBurnedDatabase.calorieBurnedDao();
        firebaseAuth = FirebaseAuth.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
        String email = user.getEmail();

        Bundle bundle = getIntent().getExtras();
        String name = bundle.getString("name");
        int calories_per_hour = bundle.getInt("calories_per_hour");
        txtView_name.setText(name);

        String time = String.format("%02d:%02d:%02d", second / 60 / 60, second / 60, second % 60);
        txtView_time.setText(time);
        btn_start_stop.setImageResource(R.drawable.start);

        btn_start_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isCounting) {
                    startTime = System.currentTimeMillis();
                    btn_start_stop.setImageResource(R.drawable.stop);
                    isCounting = true;
                    runnable = new Runnable() {
                        @Override
                        public void run() {
                            long currentTime = System.currentTimeMillis();
                            long elapsedTime = currentTime - startTime;
                            second = (int) (elapsedTime / 1000);
                            String time = String.format("%02d:%02d:%02d", second / 60 / 60, second / 60, second % 60);
                            txtView_time.setText(time);
                            handler.postDelayed(this, 1000);
                        }
                    };
                    handler.postDelayed(runnable, 1000);
                } else {
                    btn_start_stop.setImageResource(R.drawable.start);
                    isCounting = false;
                    handler.removeCallbacks(runnable);

                    String time = String.format("%02d:%02d:%02d", second / 60 / 60, second / 60, second % 60);
                    txtView_time.setText(time);
                    Intent intent = new Intent(WorkoutCountingActivity.this, MainActivity.class);
                    Calendar calendar = Calendar.getInstance();
                    int year = calendar.get(Calendar.YEAR);
                    int month = calendar.get(Calendar.MONTH) + 1;
                    int day = calendar.get(Calendar.DAY_OF_MONTH);
                    ExecutorService executorService = Executors.newSingleThreadExecutor();
                    executorService.execute(new Runnable() {
                        @Override
                        public void run() {
                            calorieBurnedData_original = calorieBurnedDao.GetAllCalorieBurnedToday(email, year, month, day);
                            if (calorieBurnedData_original == null) {
                                CalorieBurned newCalorieBurned = new CalorieBurned(email, year, month, day, calories_per_hour * second / 60 / 60);
                                calorieBurnedDao.insertCalorieBurned(newCalorieBurned);
                            } else {
                                calorieBurnedData_original.setWorkoutCalorieBurned(calorieBurnedData_original.getWorkoutCalorieBurned() + calories_per_hour * second / 60 / 60);
                                calorieBurnedDao.insertCalorieBurned(calorieBurnedData_original);
                            }
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {

                                }
                            });
                        }
                    });

                    Toast.makeText(WorkoutCountingActivity.this, "Calorie Burned Data -(" + calories_per_hour * second / 60 / 60 + " ) is Created!", Toast.LENGTH_LONG).show();
                    second = 0;
                    startActivity(intent);
                }
            }
        });

    }
}