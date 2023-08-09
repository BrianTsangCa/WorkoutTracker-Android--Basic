package com.example.workouttracker.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.workouttracker.R;
import com.example.workouttracker.Adapter.WorkoutRecyclerAdapter;
import com.example.workouttracker.database.UserDatabase;
import com.example.workouttracker.user.model.User;
import com.example.workouttracker.user.model.UserDao;
import com.example.workouttracker.workout.model.Difficulty;
import com.example.workouttracker.workout.model.Workout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WorkoutFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WorkoutFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    List<Workout> WorkOutList = new ArrayList<>();
    List<Workout> WorkOutListSorted = new ArrayList<>();
    FirebaseAuth firebaseAuth;
    FirebaseUser user;
    UserDao userDao;
    UserDatabase userDatabase;
    int client_weight = 0;
    RadioGroup radioGroup_sort;


    public WorkoutFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment WorkoutFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static WorkoutFragment newInstance(String param1, String param2) {
        WorkoutFragment fragment = new WorkoutFragment();
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
        View view = inflater.inflate(R.layout.fragment_workout, container, false);
        radioGroup_sort = view.findViewById(R.id.radioGroup_sort);
        firebaseAuth = FirebaseAuth.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
        userDatabase = Room.databaseBuilder
                (view.getContext(), UserDatabase.class, "user.db").build();
        userDao = userDatabase.userDao();
        String email = user.getEmail();
        RequestQueue requestQueue = Volley.newRequestQueue(requireContext());
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                User currentUser = userDao.GetUsers(email);
                client_weight = currentUser.getWeightInPounds();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
//                        home_intro.setText("Welcome to workout tracker, "+currentUser.getUserName()+" ! ");
                    }
                });
            }
        });


        // Make the API call using JsonArrayRequest
        String url = "https://calories-burned-by-api-ninjas.p.rapidapi.com/v1/caloriesburned?activity=skiing&weight=" + client_weight;
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject workoutData = response.getJSONObject(i);
                                String name = workoutData.getString("name");
                                String calories_per_hour = workoutData.getString("calories_per_hour");
                                String category = "Skiing";
                                Difficulty diffculty;
                                if (Integer.parseInt(calories_per_hour) <= 510) {
                                    diffculty = Difficulty.Easy;
                                } else if (Integer.parseInt(calories_per_hour) <= 610) {
                                    diffculty = Difficulty.Medium;
                                } else {
                                    diffculty = Difficulty.Hard;
                                }
                                Workout workout = new Workout(name, calories_per_hour, category, diffculty);
                                WorkOutList.add(workout);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        RecyclerView workout_recyclerview = view.findViewById(R.id.workout_recyclerview);
                        workout_recyclerview.setLayoutManager(new LinearLayoutManager(view.getContext()));
                        workout_recyclerview.setAdapter(new WorkoutRecyclerAdapter(WorkOutList, view.getContext()));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle the error occurred during the API call
                        // Update the UI or log the error message as per your requirements
                        Toast.makeText(view.getContext(), "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.d("error", "Error: " + error.getMessage());
                    }
                }
        ) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<>();
                headers.put("X-RapidAPI-Key", "3f759878f1mshd25ac36121b8a6ap133611jsn07de4c438dab");
                headers.put("X-RapidAPI-Host", "calories-burned-by-api-ninjas.p.rapidapi.com");
                return headers;
            }
        };
        // Make the API call using JsonArrayRequest
        String url2 = "https://calories-burned-by-api-ninjas.p.rapidapi.com/v1/caloriesburned?activity=cycling&weight=" + client_weight;
        JsonArrayRequest jsonArrayRequest2 = new JsonArrayRequest(Request.Method.GET, url2, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject workoutData = response.getJSONObject(i);
                                String name = workoutData.getString("name");
                                String calories_per_hour = workoutData.getString("calories_per_hour");
                                String category = "Cycling";
                                Difficulty diffculty;
                                if (Integer.parseInt(calories_per_hour) <= 510) {
                                    diffculty = Difficulty.Easy;
                                } else if (Integer.parseInt(calories_per_hour) <= 610) {
                                    diffculty = Difficulty.Medium;
                                } else {
                                    diffculty = Difficulty.Hard;
                                }
                                Workout workout = new Workout(name, calories_per_hour, category, diffculty);
                                WorkOutList.add(workout);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        RecyclerView workout_recyclerview = view.findViewById(R.id.workout_recyclerview);
                        workout_recyclerview.setLayoutManager(new LinearLayoutManager(view.getContext()));
                        workout_recyclerview.setAdapter(new WorkoutRecyclerAdapter(WorkOutList, view.getContext()));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle the error occurred during the API call
                        // Update the UI or log the error message as per your requirements
                        Toast.makeText(view.getContext(), "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.d("error", "Error: " + error.getMessage());
                    }
                }
        ) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<>();
                headers.put("X-RapidAPI-Key", "3f759878f1mshd25ac36121b8a6ap133611jsn07de4c438dab");
                headers.put("X-RapidAPI-Host", "calories-burned-by-api-ninjas.p.rapidapi.com");
                return headers;
            }
        };
        // Make the API call using JsonArrayRequest
        String url3 = "https://calories-burned-by-api-ninjas.p.rapidapi.com/v1/caloriesburned?activity=aerobics&weight=" + client_weight;
        JsonArrayRequest jsonArrayRequest3 = new JsonArrayRequest(Request.Method.GET, url3, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject workoutData = response.getJSONObject(i);
                                String name = workoutData.getString("name");
                                String calories_per_hour = workoutData.getString("calories_per_hour");
                                String category = "Aerobics";
                                Difficulty diffculty;
                                if (Integer.parseInt(calories_per_hour) <= 510) {
                                    diffculty = Difficulty.Easy;
                                } else if (Integer.parseInt(calories_per_hour) <= 610) {
                                    diffculty = Difficulty.Medium;
                                } else {
                                    diffculty = Difficulty.Hard;
                                }
                                Workout workout = new Workout(name, calories_per_hour, category, diffculty);
                                WorkOutList.add(workout);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        RecyclerView workout_recyclerview = view.findViewById(R.id.workout_recyclerview);
                        workout_recyclerview.setLayoutManager(new LinearLayoutManager(view.getContext()));
                        radioGroup_sort.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(RadioGroup group, int checkedId) {
                                switch (checkedId) {
                                    case R.id.radiobtn_calorie:
                                        WorkOutListSorted = WorkOutList;
                                        MergeSort(WorkOutListSorted, 0, WorkOutList.size() - 1);
                                        workout_recyclerview.setAdapter(new WorkoutRecyclerAdapter(WorkOutListSorted, view.getContext()));
                                        break;
                                    case R.id.radiobtn_category:
                                        WorkOutListSorted = WorkOutList;
                                        MergeSortByCategory(WorkOutList, 0, WorkOutList.size() - 1);
                                        workout_recyclerview.setAdapter(new WorkoutRecyclerAdapter(WorkOutListSorted, view.getContext()));
                                        break;
                                }
                            }
                        });
                        WorkOutListSorted = WorkOutList;
                        workout_recyclerview.setAdapter(new WorkoutRecyclerAdapter(WorkOutListSorted, view.getContext()));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle the error occurred during the API call
                        // Update the UI or log the error message as per your requirements
                        Toast.makeText(view.getContext(), "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.d("error", "Error: " + error.getMessage());
                    }
                }
        ) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<>();
                headers.put("X-RapidAPI-Key", "3f759878f1mshd25ac36121b8a6ap133611jsn07de4c438dab");
                headers.put("X-RapidAPI-Host", "calories-burned-by-api-ninjas.p.rapidapi.com");
                return headers;
            }
        };

        // Add the request to the Volley request queue
        requestQueue.add(jsonArrayRequest);
        requestQueue.add(jsonArrayRequest2);
        requestQueue.add(jsonArrayRequest3);

        // Inflate the layout for this fragment
        return view;

    }

    public static void MergeSort(List<Workout> workoutList, int first, int last) {
        if (first < last) {
            int mid = (first + last) / 2;
            MergeSort(workoutList, first, mid);
            MergeSort(workoutList, mid + 1, last);
            merge(workoutList, first, mid, last);
        }
    }

    public static void merge(List<Workout> workoutList, int first, int mid, int last) {
        List<Workout> tempWorkoutList = new ArrayList<>();

        // Rest of your merge code...

        int beginHalf1 = first;
        int endHalf1 = mid;
        int beginHalf2 = mid + 1;
        int endHalf2 = last;

        while (beginHalf1 <= endHalf1 && beginHalf2 <= endHalf2) {
            int calorie1 = Integer.parseInt(workoutList.get(beginHalf1).getCalories_per_hour());
            int calorie2 = Integer.parseInt(workoutList.get(beginHalf2).getCalories_per_hour());

            if (calorie1 <= calorie2) {
                tempWorkoutList.add(workoutList.get(beginHalf1));
                beginHalf1++;
            } else {
                tempWorkoutList.add(workoutList.get(beginHalf2));
                beginHalf2++;
            }
        }

        while (beginHalf1 <= endHalf1) {
            tempWorkoutList.add(workoutList.get(beginHalf1));
            beginHalf1++;
        }

        while (beginHalf2 <= endHalf2) {
            tempWorkoutList.add(workoutList.get(beginHalf2));
            beginHalf2++;
        }

        for (int i = 0; i < tempWorkoutList.size(); i++) {
            workoutList.set(first + i, tempWorkoutList.get(i));
        }
    }

    public static void MergeSortByCategory(List<Workout> workoutList, int first, int last) {
        if (first < last) {
            int mid = (first + last) / 2;
            MergeSortByCategory(workoutList, first, mid);
            MergeSortByCategory(workoutList, mid + 1, last);
            mergeByCategory(workoutList, first, mid, last);
        }
    }

    public static void mergeByCategory(List<Workout> workoutList, int first, int mid, int last) {
        List<Workout> tempWorkoutList = new ArrayList<>();

        int beginHalf1 = first;
        int endHalf1 = mid;
        int beginHalf2 = mid + 1;
        int endHalf2 = last;

        while (beginHalf1 <= endHalf1 && beginHalf2 <= endHalf2) {
            String category1 = workoutList.get(beginHalf1).getCategory();
            String category2 = workoutList.get(beginHalf2).getCategory();

            if (category1.compareTo(category2) <= 0) {
                tempWorkoutList.add(workoutList.get(beginHalf1));
                beginHalf1++;
            } else {
                tempWorkoutList.add(workoutList.get(beginHalf2));
                beginHalf2++;
            }
        }

        while (beginHalf1 <= endHalf1) {
            tempWorkoutList.add(workoutList.get(beginHalf1));
            beginHalf1++;
        }

        while (beginHalf2 <= endHalf2) {
            tempWorkoutList.add(workoutList.get(beginHalf2));
            beginHalf2++;
        }

        for (int i = 0; i < tempWorkoutList.size(); i++) {
            workoutList.set(first + i, tempWorkoutList.get(i));
        }
    }
}