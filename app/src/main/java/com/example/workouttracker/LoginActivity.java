package com.example.workouttracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.workouttracker.databinding.ActivityLoginPageBinding;
import com.example.workouttracker.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    ActivityLoginPageBinding binding;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityLoginPageBinding.inflate(getLayoutInflater());
        View view=binding.getRoot();
        setContentView(view);
        firebaseAuth=FirebaseAuth.getInstance();
    }
    public void ClickLogin(View view) {
        Toast.makeText(this, "Login is clicked!", Toast.LENGTH_SHORT).show();
    }
    public void ClickRegister(View view) {
        Toast.makeText(this, "Register is clicked!", Toast.LENGTH_SHORT).show();
    }
}