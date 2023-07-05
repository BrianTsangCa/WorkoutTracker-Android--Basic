package com.example.workouttracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.workouttracker.user.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class Register_Page_Activity extends AppCompatActivity {
    FirebaseAuth firebaseAuth;

    EditText editText_email,editText_password,editText_username,editText_weight;
    Button btn_register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

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
                try{
                    firebaseAuth.createUserWithEmailAndPassword(_email, _password).addOnCompleteListener(Register_Page_Activity.this,new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){

                                User Newuser = new User(_email, _userName,Integer.parseInt(_weightInPounds));
                                Toast.makeText(Register_Page_Activity.this, "User -("+Newuser.getUserName()+") is Created!", Toast.LENGTH_LONG).show();
                                startActivity(new Intent(Register_Page_Activity.this,LoginOrRegisterActivity.class));
                            }else{
                                Toast.makeText(Register_Page_Activity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
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