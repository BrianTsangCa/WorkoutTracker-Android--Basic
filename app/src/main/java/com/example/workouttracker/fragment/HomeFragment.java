package com.example.workouttracker.fragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.workouttracker.CalorieBurned.model.CalorieBurned;
import com.example.workouttracker.CalorieBurned.model.CalorieBurnedDao;
import com.example.workouttracker.R;
import com.example.workouttracker.database.CalorieBurnedDatabase;
import com.example.workouttracker.database.UserDatabase;
import com.example.workouttracker.user.model.User;
import com.example.workouttracker.user.model.UserDao;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    FirebaseAuth firebaseAuth;
    FirebaseUser user;
    UserDao userDao;
    UserDatabase userDatabase;
    CalorieBurnedDao calorieBurnedDao;
    CalorieBurnedDatabase calorieBurnedDatabase;
    List<BarEntry> entries = new ArrayList<>();

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        // Inflate the layout for this fragment
        TextView home_intro = view.findViewById(R.id.home_intro);
        TextView txt_graphTitle = view.findViewById(R.id.txt_graphTitle);
        BarChart chart = view.findViewById(R.id.chart);
        firebaseAuth = FirebaseAuth.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
        userDatabase = Room.databaseBuilder
                (view.getContext(), UserDatabase.class, "user.db").build();
        userDao = userDatabase.userDao();
        String email = user.getEmail();
        calorieBurnedDatabase = Room.databaseBuilder
                (view.getContext(), CalorieBurnedDatabase.class, "calorieBurned.db").build();
        calorieBurnedDao = calorieBurnedDatabase.calorieBurnedDao();

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                User currentUser = userDao.GetUsers(email);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        home_intro.setText("Welcome to workout tracker, " + currentUser.getUserName() + " ! ");
                    }
                });
            }
        });
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                int calorie = calorieBurnedDao.GetAllCalorieBurnedToday(email, year, month, day);
                for (int i = 0; i < month; i++) {
                    entries.add(new BarEntry(i, calorieBurnedDao.getTotalCalorieBurnedForMonth(email, year, i + 1)));
                }
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        home_intro.setText(home_intro.getText() + " " + calorie + " calorie is burned today!");
                        BarDataSet dataSet = new BarDataSet(entries, "Calorie Burned");
                        dataSet.setColor(Color.GRAY);

                        BarData barData = new BarData(dataSet);
                        chart.setData(barData);
                        // Customize chart settings if needed
                        Description description = new Description();
                        chart.setDescription(description);
                        chart.setTouchEnabled(true);
                        chart.setDragEnabled(true);
                        chart.setScaleEnabled(true);
                        txt_graphTitle.setText("Monthly Calorie Burned");
                    }
                });
            }
        });
        Toast.makeText(view.getContext(), "email is " + email, Toast.LENGTH_SHORT).show();
        return view;
    }
}