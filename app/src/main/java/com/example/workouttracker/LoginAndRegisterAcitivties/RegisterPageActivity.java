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

import com.example.workouttracker.R;
import com.example.workouttracker.database.UserDatabase;
import com.example.workouttracker.user.model.User;
import com.example.workouttracker.user.model.UserDao;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RegisterPageActivity extends AppCompatActivity {
    FirebaseAuth firebaseAuth;

    EditText editText_email,editText_password,editText_username,editText_weight;
    Button btn_register;
    UserDao userDao;
    UserDatabase userDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);
        userDatabase = Room.databaseBuilder
                (getApplicationContext(),UserDatabase.class,"user.db").build();
        userDao = userDatabase.userDao();
        editText_email=findViewById(R.id.editText_email);
        editText_password=findViewById(R.id.editText_password);
        editText_username=findViewById(R.id.editText_username);
        editText_weight=findViewById(R.id.editText_weight);
        btn_register=findViewById(R.id.btn_register);

        firebaseAuth= FirebaseAuth.getInstance();

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String _email = editText_email.getText().toString();
                String _password =editText_password.getText().toString();
                String _userName=editText_username.getText().toString();
                String _weightInPounds=editText_weight.getText().toString();
                if(Integer.parseInt(_weightInPounds)<50||Integer.parseInt(_weightInPounds)>500){
                    Toast.makeText(RegisterPageActivity.this, "Weight must be between 50 and 500", Toast.LENGTH_SHORT).show();
                    return;
                }
                try{
                    firebaseAuth.createUserWithEmailAndPassword(_email, _password).addOnCompleteListener(RegisterPageActivity.this,new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                User newuser = new User(_email, _userName,Integer.parseInt(_weightInPounds));
                                ExecutorService executorService= Executors.newSingleThreadExecutor();
                                executorService.execute(new Runnable() {
                                    @Override
                                    public void run() {
                                        userDao.insertUser(newuser);
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {

                                            }
                                        });
                                    }
                                });
                                Toast.makeText(RegisterPageActivity.this, "User -("+newuser.getUserName()+") is Created!", Toast.LENGTH_LONG).show();
                                startActivity(new Intent(RegisterPageActivity.this, LoginOrRegisterActivity.class));
                            }else{
                                Toast.makeText(RegisterPageActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }catch(Exception ex){
                    ex.printStackTrace();
                }
            }
        });

    }
}