package com.example.workouttracker.LoginAndRegisterAcitivties;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.workouttracker.CalorieBurned.model.CalorieBurned;
import com.example.workouttracker.CalorieBurned.model.CalorieBurnedDao;
import com.example.workouttracker.MainActivity;
import com.example.workouttracker.R;
import com.example.workouttracker.database.CalorieBurnedDatabase;
import com.example.workouttracker.database.UserDatabase;
import com.example.workouttracker.user.model.User;
import com.example.workouttracker.user.model.UserDao;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LoginPageActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;

    EditText editText_email2, editText_password2;
    Button btn_login;
    CalorieBurnedDao calorieBurnedDao;
    CalorieBurnedDatabase calorieBurnedDatabase;
    UserDao userDao;
    UserDatabase userDatabase;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        btn_login = findViewById(R.id.btn_login);
        editText_email2 = findViewById(R.id.editText_email2);
        editText_password2 = findViewById(R.id.editText_password2);

        firebaseAuth = FirebaseAuth.getInstance();
        calorieBurnedDatabase = Room.databaseBuilder
                (this, CalorieBurnedDatabase.class, "calorieBurned.db").build();
        calorieBurnedDao = calorieBurnedDatabase.calorieBurnedDao();
        user = FirebaseAuth.getInstance().getCurrentUser();
        userDatabase = Room.databaseBuilder
                (this, UserDatabase.class, "user.db").build();
        userDao = userDatabase.userDao();

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editText_email2.getText().toString();
                String password = editText_password2.getText().toString();
                try {
                    firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(LoginPageActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(LoginPageActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(LoginPageActivity.this, MainActivity.class));
                            } else {
                                Toast.makeText(LoginPageActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                fetchData();
            }
        });
    }

    public void fetchData() {
        String _email = "cliff@gmail.com";
        String _userName = "Cliff Wong";
        String _weightInPounds = "170";
        User newuser = new User(_email, _userName, Integer.parseInt(_weightInPounds));
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int lastMonth = calendar.get(Calendar.MONTH);
        int currentMonth = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        List<CalorieBurned> calorieBurnedList = new ArrayList<>();

        calorieBurnedList.add(new CalorieBurned(_email, year, lastMonth, 1, 250));
        calorieBurnedList.add(new CalorieBurned(_email, year, lastMonth, 2, 750));
        calorieBurnedList.add(new CalorieBurned(_email, year, lastMonth, 3, 1250));
        calorieBurnedList.add(new CalorieBurned(_email, year, lastMonth, 4, 250));
        calorieBurnedList.add(new CalorieBurned(_email, year, lastMonth, 5, 750));
        calorieBurnedList.add(new CalorieBurned(_email, year, lastMonth, 6, 1250));
        calorieBurnedList.add(new CalorieBurned(_email, year, lastMonth, 7, 250));
        calorieBurnedList.add(new CalorieBurned(_email, year, lastMonth, 8, 750));
        calorieBurnedList.add(new CalorieBurned(_email, year, lastMonth, 9, 1250));
        calorieBurnedList.add(new CalorieBurned(_email, year, lastMonth, 10, 250));
        calorieBurnedList.add(new CalorieBurned(_email, year, lastMonth, 11, 750));
        calorieBurnedList.add(new CalorieBurned(_email, year, lastMonth, 12, 1250));
        calorieBurnedList.add(new CalorieBurned(_email, year, lastMonth, 13, 250));
        calorieBurnedList.add(new CalorieBurned(_email, year, lastMonth, 14, 750));
        calorieBurnedList.add(new CalorieBurned(_email, year, lastMonth, 15, 1250));
        calorieBurnedList.add(new CalorieBurned(_email, year, lastMonth, 16, 250));
        calorieBurnedList.add(new CalorieBurned(_email, year, lastMonth, 17, 750));
        calorieBurnedList.add(new CalorieBurned(_email, year, lastMonth, 18, 1250));
        calorieBurnedList.add(new CalorieBurned(_email, year, lastMonth, 19, 250));
        calorieBurnedList.add(new CalorieBurned(_email, year, lastMonth, 20, 750));
        calorieBurnedList.add(new CalorieBurned(_email, year, lastMonth, 21, 1250));
        calorieBurnedList.add(new CalorieBurned(_email, year, lastMonth, 22, 250));
        calorieBurnedList.add(new CalorieBurned(_email, year, lastMonth, 23, 750));
        calorieBurnedList.add(new CalorieBurned(_email, year, lastMonth, 24, 1250));
        calorieBurnedList.add(new CalorieBurned(_email, year, lastMonth, 25, 250));
        calorieBurnedList.add(new CalorieBurned(_email, year, lastMonth, 26, 750));
        calorieBurnedList.add(new CalorieBurned(_email, year, lastMonth, 27, 1250));
        calorieBurnedList.add(new CalorieBurned(_email, year, currentMonth, day, 500));
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                userDao.insertUser(newuser);
                for (int i = 0; i < calorieBurnedList.size(); i++) {
                    calorieBurnedDao.insertCalorieBurned(calorieBurnedList.get(i));
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                    }
                });
            }
        });
    }
}