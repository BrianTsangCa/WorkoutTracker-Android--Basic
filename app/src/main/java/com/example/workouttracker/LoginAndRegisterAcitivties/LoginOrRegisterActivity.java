package com.example.workouttracker.LoginAndRegisterAcitivties;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.workouttracker.R;

public class LoginOrRegisterActivity extends AppCompatActivity {
    Button btn_register_main, btn_login_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_or_register_page);

        btn_register_main = findViewById(R.id.btn_register_main);
        btn_login_main = findViewById(R.id.btn_login_main);
        btn_register_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginOrRegisterActivity.this, RegisterPageActivity.class));
                finish();
            }
        });
        btn_login_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginOrRegisterActivity.this, LoginPageActivity.class));
                finish();
            }
        });
    }
}