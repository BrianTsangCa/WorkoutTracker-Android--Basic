package com.example.workouttracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class WorkoutCountingActivity extends AppCompatActivity {
    TextView txtView_name,txtView_time;
    ImageButton btn_start_stop;
    int second;
    boolean isCounting = false;
    long startTime=0;
    Runnable runnable;
    Handler handler=new Handler(Looper.myLooper());


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_counting);
        txtView_name=findViewById(R.id.txtView_name);
        txtView_time=findViewById(R.id.txtView_time);
        btn_start_stop=findViewById(R.id.btn_start_stop);

//        Bundle bundle=getIntent().getExtras();
//        String name=bundle.getString("name");
//        int calories_per_hour=bundle.getInt("calories_per_hour");
//        txtView_name.setText(name);

        String time=String.format("%02d:%02d:%02d", second/60/60, second/60, second%60);
        txtView_time.setText(time);
        btn_start_stop.setImageResource(R.drawable.start);

        btn_start_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isCounting){
                    startTime =System.currentTimeMillis();
                    btn_start_stop.setImageResource(R.drawable.stop);
                    isCounting=true;
                    runnable=new Runnable() {
                        @Override
                        public void run() {
                            long currentTime = System.currentTimeMillis();
                            long elapsedTime = currentTime - startTime;
                            second = (int) (elapsedTime / 1000);
                            String time=String.format("%02d:%02d:%02d", second/60/60, second/60, second%60);
                            txtView_time.setText(time);
                            handler.postDelayed(this, 1000);
                        }
                    };
                    handler.postDelayed(runnable, 1000);
                }else{
                    btn_start_stop.setImageResource(R.drawable.start);
                    isCounting=false;
                    handler.removeCallbacks(runnable);
                    second = 0;
                    String time=String.format("%02d:%02d:%02d", second/60/60, second/60, second%60);
                    txtView_time.setText(time);

                }
            }
        });

    }
}