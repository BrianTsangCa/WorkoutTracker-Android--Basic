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
        String[] email = {"cliff@gmail.com", "ada@gmail.com", "bob@gmail.com"};
        String[] userName = {"Cliff Wong", "Ada Wong", "Bob Wong"};
        List<User> userList = new ArrayList<>();
        for (int i = 0; i < email.length; i++) {
            userList.add(new User(email[i], userName[i], 170));
        }
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int lastMonth = calendar.get(Calendar.MONTH);
        int lastlastMonth = lastMonth - 1;
        int lastlastlastMonth = lastMonth - 2;
        int currentMonth = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        List<CalorieBurned> calorieBurnedList = new ArrayList<>();

        String _email = email[0];
        calorieBurnedList.add(new CalorieBurned(_email, year, lastlastlastMonth, 1, 100));
        calorieBurnedList.add(new CalorieBurned(_email, year, lastlastlastMonth, 2, 200));
        calorieBurnedList.add(new CalorieBurned(_email, year, lastlastlastMonth, 3, 300));
        calorieBurnedList.add(new CalorieBurned(_email, year, lastlastlastMonth, 4, 100));
        calorieBurnedList.add(new CalorieBurned(_email, year, lastlastlastMonth, 5, 200));
        calorieBurnedList.add(new CalorieBurned(_email, year, lastlastlastMonth, 6, 300));
        calorieBurnedList.add(new CalorieBurned(_email, year, lastlastlastMonth, 7, 100));
        calorieBurnedList.add(new CalorieBurned(_email, year, lastlastlastMonth, 8, 200));
        calorieBurnedList.add(new CalorieBurned(_email, year, lastlastlastMonth, 9, 300));
        calorieBurnedList.add(new CalorieBurned(_email, year, lastlastlastMonth, 10, 100));
        calorieBurnedList.add(new CalorieBurned(_email, year, lastlastlastMonth, 11, 200));
        calorieBurnedList.add(new CalorieBurned(_email, year, lastlastlastMonth, 12, 300));
        calorieBurnedList.add(new CalorieBurned(_email, year, lastlastlastMonth, 13, 100));
        calorieBurnedList.add(new CalorieBurned(_email, year, lastlastlastMonth, 14, 200));
        calorieBurnedList.add(new CalorieBurned(_email, year, lastlastlastMonth, 15, 300));
        calorieBurnedList.add(new CalorieBurned(_email, year, lastlastlastMonth, 16, 100));
        calorieBurnedList.add(new CalorieBurned(_email, year, lastlastlastMonth, 17, 200));
        calorieBurnedList.add(new CalorieBurned(_email, year, lastlastlastMonth, 18, 300));
        calorieBurnedList.add(new CalorieBurned(_email, year, lastlastlastMonth, 19, 100));
        calorieBurnedList.add(new CalorieBurned(_email, year, lastlastlastMonth, 20, 200));
        calorieBurnedList.add(new CalorieBurned(_email, year, lastlastlastMonth, 21, 300));
        calorieBurnedList.add(new CalorieBurned(_email, year, lastlastlastMonth, 22, 100));
        calorieBurnedList.add(new CalorieBurned(_email, year, lastlastlastMonth, 23, 200));
        calorieBurnedList.add(new CalorieBurned(_email, year, lastlastlastMonth, 24, 300));
        calorieBurnedList.add(new CalorieBurned(_email, year, lastlastlastMonth, 25, 100));
        calorieBurnedList.add(new CalorieBurned(_email, year, lastlastlastMonth, 26, 200));
        calorieBurnedList.add(new CalorieBurned(_email, year, lastlastlastMonth, 27, 300));
        calorieBurnedList.add(new CalorieBurned(_email, year, lastlastMonth, 1, 100));
        calorieBurnedList.add(new CalorieBurned(_email, year, lastlastMonth, 2, 500));
        calorieBurnedList.add(new CalorieBurned(_email, year, lastlastMonth, 3, 750));
        calorieBurnedList.add(new CalorieBurned(_email, year, lastlastMonth, 4, 100));
        calorieBurnedList.add(new CalorieBurned(_email, year, lastlastMonth, 5, 500));
        calorieBurnedList.add(new CalorieBurned(_email, year, lastlastMonth, 6, 750));
        calorieBurnedList.add(new CalorieBurned(_email, year, lastlastMonth, 7, 100));
        calorieBurnedList.add(new CalorieBurned(_email, year, lastlastMonth, 8, 500));
        calorieBurnedList.add(new CalorieBurned(_email, year, lastlastMonth, 9, 750));
        calorieBurnedList.add(new CalorieBurned(_email, year, lastlastMonth, 10, 100));
        calorieBurnedList.add(new CalorieBurned(_email, year, lastlastMonth, 11, 500));
        calorieBurnedList.add(new CalorieBurned(_email, year, lastlastMonth, 12, 750));
        calorieBurnedList.add(new CalorieBurned(_email, year, lastlastMonth, 13, 100));
        calorieBurnedList.add(new CalorieBurned(_email, year, lastlastMonth, 14, 500));
        calorieBurnedList.add(new CalorieBurned(_email, year, lastlastMonth, 15, 750));
        calorieBurnedList.add(new CalorieBurned(_email, year, lastlastMonth, 16, 100));
        calorieBurnedList.add(new CalorieBurned(_email, year, lastlastMonth, 17, 500));
        calorieBurnedList.add(new CalorieBurned(_email, year, lastlastMonth, 18, 750));
        calorieBurnedList.add(new CalorieBurned(_email, year, lastlastMonth, 19, 100));
        calorieBurnedList.add(new CalorieBurned(_email, year, lastlastMonth, 20, 500));
        calorieBurnedList.add(new CalorieBurned(_email, year, lastlastMonth, 21, 750));
        calorieBurnedList.add(new CalorieBurned(_email, year, lastlastMonth, 22, 100));
        calorieBurnedList.add(new CalorieBurned(_email, year, lastlastMonth, 23, 500));
        calorieBurnedList.add(new CalorieBurned(_email, year, lastlastMonth, 24, 750));
        calorieBurnedList.add(new CalorieBurned(_email, year, lastlastMonth, 25, 100));
        calorieBurnedList.add(new CalorieBurned(_email, year, lastlastMonth, 26, 500));
        calorieBurnedList.add(new CalorieBurned(_email, year, lastlastMonth, 27, 750));
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
        _email = email[1];
        calorieBurnedList.add(new CalorieBurned(_email, year, lastMonth, 1, 200));
        calorieBurnedList.add(new CalorieBurned(_email, year, lastMonth, 2, 700));
        calorieBurnedList.add(new CalorieBurned(_email, year, lastMonth, 3, 1000));
        calorieBurnedList.add(new CalorieBurned(_email, year, lastMonth, 4, 200));
        calorieBurnedList.add(new CalorieBurned(_email, year, lastMonth, 5, 700));
        calorieBurnedList.add(new CalorieBurned(_email, year, lastMonth, 6, 1000));
        calorieBurnedList.add(new CalorieBurned(_email, year, lastMonth, 7, 200));
        calorieBurnedList.add(new CalorieBurned(_email, year, lastMonth, 8, 700));
        calorieBurnedList.add(new CalorieBurned(_email, year, lastMonth, 9, 1000));
        calorieBurnedList.add(new CalorieBurned(_email, year, lastMonth, 10, 200));
        calorieBurnedList.add(new CalorieBurned(_email, year, lastMonth, 11, 700));
        calorieBurnedList.add(new CalorieBurned(_email, year, lastMonth, 12, 1000));
        calorieBurnedList.add(new CalorieBurned(_email, year, lastMonth, 13, 200));
        calorieBurnedList.add(new CalorieBurned(_email, year, lastMonth, 14, 700));
        calorieBurnedList.add(new CalorieBurned(_email, year, lastMonth, 15, 1000));
        calorieBurnedList.add(new CalorieBurned(_email, year, lastMonth, 16, 200));
        calorieBurnedList.add(new CalorieBurned(_email, year, lastMonth, 17, 700));
        calorieBurnedList.add(new CalorieBurned(_email, year, lastMonth, 18, 1000));
        calorieBurnedList.add(new CalorieBurned(_email, year, lastMonth, 19, 200));
        calorieBurnedList.add(new CalorieBurned(_email, year, lastMonth, 20, 700));
        calorieBurnedList.add(new CalorieBurned(_email, year, lastMonth, 21, 1000));
        calorieBurnedList.add(new CalorieBurned(_email, year, lastMonth, 22, 200));
        calorieBurnedList.add(new CalorieBurned(_email, year, lastMonth, 23, 700));
        calorieBurnedList.add(new CalorieBurned(_email, year, lastMonth, 24, 1000));
        calorieBurnedList.add(new CalorieBurned(_email, year, lastMonth, 25, 200));
        calorieBurnedList.add(new CalorieBurned(_email, year, lastMonth, 26, 700));
        calorieBurnedList.add(new CalorieBurned(_email, year, lastMonth, 27, 1000));
        calorieBurnedList.add(new CalorieBurned(_email, year, currentMonth, day, 450));
        _email = email[2];
        calorieBurnedList.add(new CalorieBurned(_email, year, lastMonth, 1, 100));
        calorieBurnedList.add(new CalorieBurned(_email, year, lastMonth, 2, 500));
        calorieBurnedList.add(new CalorieBurned(_email, year, lastMonth, 3, 750));
        calorieBurnedList.add(new CalorieBurned(_email, year, lastMonth, 4, 100));
        calorieBurnedList.add(new CalorieBurned(_email, year, lastMonth, 5, 500));
        calorieBurnedList.add(new CalorieBurned(_email, year, lastMonth, 6, 750));
        calorieBurnedList.add(new CalorieBurned(_email, year, lastMonth, 7, 100));
        calorieBurnedList.add(new CalorieBurned(_email, year, lastMonth, 8, 500));
        calorieBurnedList.add(new CalorieBurned(_email, year, lastMonth, 9, 750));
        calorieBurnedList.add(new CalorieBurned(_email, year, lastMonth, 10, 100));
        calorieBurnedList.add(new CalorieBurned(_email, year, lastMonth, 11, 500));
        calorieBurnedList.add(new CalorieBurned(_email, year, lastMonth, 12, 750));
        calorieBurnedList.add(new CalorieBurned(_email, year, lastMonth, 13, 100));
        calorieBurnedList.add(new CalorieBurned(_email, year, lastMonth, 14, 500));
        calorieBurnedList.add(new CalorieBurned(_email, year, lastMonth, 15, 750));
        calorieBurnedList.add(new CalorieBurned(_email, year, lastMonth, 16, 100));
        calorieBurnedList.add(new CalorieBurned(_email, year, lastMonth, 17, 500));
        calorieBurnedList.add(new CalorieBurned(_email, year, lastMonth, 18, 750));
        calorieBurnedList.add(new CalorieBurned(_email, year, lastMonth, 19, 100));
        calorieBurnedList.add(new CalorieBurned(_email, year, lastMonth, 20, 500));
        calorieBurnedList.add(new CalorieBurned(_email, year, lastMonth, 21, 750));
        calorieBurnedList.add(new CalorieBurned(_email, year, lastMonth, 22, 100));
        calorieBurnedList.add(new CalorieBurned(_email, year, lastMonth, 23, 500));
        calorieBurnedList.add(new CalorieBurned(_email, year, lastMonth, 24, 750));
        calorieBurnedList.add(new CalorieBurned(_email, year, lastMonth, 25, 100));
        calorieBurnedList.add(new CalorieBurned(_email, year, lastMonth, 26, 500));
        calorieBurnedList.add(new CalorieBurned(_email, year, lastMonth, 27, 750));
        calorieBurnedList.add(new CalorieBurned(_email, year, currentMonth, day, 250));

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < userList.size(); i++) {
                    userDao.insertUser(userList.get(i));
                    calorieBurnedDao.deleteAllCalorieBurned(userList.get(i).getEmail());
                }
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