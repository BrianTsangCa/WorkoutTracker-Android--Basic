package com.example.workouttracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.example.workouttracker.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        View view=binding.getRoot();
        setContentView(view);
    }

    public void ClickLogin(View view) {
        Toast.makeText(this, "Login is clicked!", Toast.LENGTH_SHORT).show();
    }

    public void ClickRegister(View view) {
        Toast.makeText(this, "Register is clicked!", Toast.LENGTH_SHORT).show();
    }
}