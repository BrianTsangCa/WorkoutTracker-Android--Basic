package com.example.workouttracker.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.example.workouttracker.Adapter.CalorieBurnedRecyclerAdapter;
import com.example.workouttracker.Adapter.WorkoutRecyclerAdapter;
import com.example.workouttracker.CalorieBurned.model.CalorieBurned;
import com.example.workouttracker.CalorieBurned.model.CalorieBurnedDao;
import com.example.workouttracker.R;
import com.example.workouttracker.database.CalorieBurnedDatabase;
import com.example.workouttracker.database.UserDatabase;
import com.example.workouttracker.user.model.User;
import com.example.workouttracker.user.model.UserDao;
import com.example.workouttracker.workout.model.Workout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StatisticFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StatisticFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private List<CalorieBurned> calorieBurnedList = new ArrayList<>();
    FirebaseAuth firebaseAuth;
    FirebaseUser user;
    CalorieBurnedDao calorieBurnedDao;
    CalorieBurnedDatabase calorieBurnedDatabase;
    RecyclerView statistic_recyclerview;

    public StatisticFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StatisticFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StatisticFragment newInstance(String param1, String param2) {
        StatisticFragment fragment = new StatisticFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_statistic, container, false);
        firebaseAuth = FirebaseAuth.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
        String email = user.getEmail();
        calorieBurnedDatabase = Room.databaseBuilder(view.getContext(), CalorieBurnedDatabase.class, "calorieBurned.db").build();
        calorieBurnedDao = calorieBurnedDatabase.calorieBurnedDao();
        statistic_recyclerview = view.findViewById(R.id.statistic_recyclerview);
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                calorieBurnedList = calorieBurnedDao.GetAllCalorieBurned(email);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        statistic_recyclerview.setLayoutManager(new GridLayoutManager(view.getContext(), 3));
                        statistic_recyclerview.setAdapter(new CalorieBurnedRecyclerAdapter(calorieBurnedList, view.getContext()));
                    }
                });
            }
        });


        Spinner spinner_dataformat = view.findViewById(R.id.spinner_dataformat);
        spinner_dataformat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ExecutorService executorService2 = Executors.newSingleThreadExecutor();
                executorService2.execute(new Runnable() {
                    @Override
                    public void run() {
                        if (spinner_dataformat.getSelectedItemPosition() == 0) {
                            getDataFromLastWeek(email);
                        } else if (spinner_dataformat.getSelectedItemPosition() == 1) {
                            int currentYear = Calendar.getInstance().get(Calendar.YEAR);
                            int currentMonth = Calendar.getInstance().get(Calendar.MONTH) + 1;
                            calorieBurnedList = calorieBurnedDao.getAllCalorieBurnedThisMonth(email, currentYear, currentMonth);
                        } else if (spinner_dataformat.getSelectedItemPosition() == 2) {
                            int currentYear = Calendar.getInstance().get(Calendar.YEAR);
                            calorieBurnedList = calorieBurnedDao.getAllCalorieBurnedThisYear(email, currentYear);
                        }
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                statistic_recyclerview.setAdapter(new CalorieBurnedRecyclerAdapter(calorieBurnedList, view.getContext()));
                            }
                        });
                    }
                });
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                ExecutorService executorService = Executors.newSingleThreadExecutor();
                executorService.execute(new Runnable() {
                    @Override
                    public void run() {
                        getDataFromLastWeek(email);
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                statistic_recyclerview.setAdapter(new CalorieBurnedRecyclerAdapter(calorieBurnedList, view.getContext()));
                            }
                        });
                    }
                });
            }
        });
        // Inflate the layout for this fragment
        return view;
    }

    public void getDataFromLastWeek(String email) {
        // Get the current date
        Calendar currentDate = Calendar.getInstance();
        int currentYear = currentDate.get(Calendar.YEAR);
        int currentMonth = currentDate.get(Calendar.MONTH) + 1; // January is 0, so we add 1
        int currentDay = currentDate.get(Calendar.DAY_OF_MONTH);

        // Calculate the start and end days of the current week
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, currentYear);
        calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek()); // Set to the first day of the week (usually Sunday)
        int startDay = calendar.get(Calendar.DAY_OF_MONTH);

        calendar.add(Calendar.DATE, 6); // Move to the end of the week (usually Saturday)
        int endDay = calendar.get(Calendar.DAY_OF_MONTH);

        // Check if the current week spans two different months
        if (endDay < startDay) {
            // If it does, we need to adjust the month and year for the end day
            int currentMonthDays = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
            if (currentDay + (currentMonthDays - endDay) >= startDay) {
                // The end day is in the next month
                currentMonth++;
                if (currentMonth > 12) {
                    currentMonth = 1;
                    currentYear++;
                }
            } else {
                // The end day is in the previous month
                currentMonth--;
                if (currentMonth < 1) {
                    currentMonth = 12;
                    currentYear--;
                }
            }
        }

        // Calculate the start and end days for the previous week
        calendar.set(Calendar.YEAR, currentYear);
        calendar.set(Calendar.MONTH, currentMonth - 1); // Subtract 1 as months are 0-indexed
        calendar.set(Calendar.DAY_OF_MONTH, startDay);

        calendar.add(Calendar.DATE, -7); // Move to the start of the previous week
        int prevStartDay = calendar.get(Calendar.DAY_OF_MONTH);

        calendar.add(Calendar.DATE, 6); // Move to the end of the previous week (usually Saturday)
        int prevEndDay = calendar.get(Calendar.DAY_OF_MONTH);
        calorieBurnedList = calorieBurnedDao.getAllCalorieBurnedLastWeek(email, currentYear, currentMonth, startDay, endDay, prevStartDay, prevEndDay);
    }
}