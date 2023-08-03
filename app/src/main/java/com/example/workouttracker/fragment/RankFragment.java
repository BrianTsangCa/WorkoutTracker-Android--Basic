package com.example.workouttracker.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.workouttracker.Adapter.RankRecyclerAdapter;
import com.example.workouttracker.CalorieBurned.model.CalorieBurnedDao;
import com.example.workouttracker.R;
import com.example.workouttracker.database.CalorieBurnedDatabase;
import com.example.workouttracker.database.UserDatabase;
import com.example.workouttracker.user.model.User;
import com.example.workouttracker.user.model.UserDao;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RankFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RankFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    FirebaseAuth firebaseAuth;
    FirebaseUser user;
    CalorieBurnedDao calorieBurnedDao;
    CalorieBurnedDatabase calorieBurnedDatabase;
    RecyclerView rank_recyclerview;
    UserDao userDao;
    UserDatabase userDatabase;

    public RankFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GraphFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RankFragment newInstance(String param1, String param2) {
        RankFragment fragment = new RankFragment();
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
        View view = inflater.inflate(R.layout.fragment_rank, container, false);
        firebaseAuth = FirebaseAuth.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
        String email = user.getEmail();
        calorieBurnedDatabase = Room.databaseBuilder(view.getContext(), CalorieBurnedDatabase.class, "calorieBurned.db").build();
        calorieBurnedDao = calorieBurnedDatabase.calorieBurnedDao();
        userDatabase = Room.databaseBuilder
                (view.getContext(), UserDatabase.class, "user.db").build();
        userDao = userDatabase.userDao();
        rank_recyclerview = view.findViewById(R.id.rank_recyclerview);
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                List<User> AllUser = userDao.getAllUsers();
                List<Integer> calorieBurnedList = new ArrayList<>();
                for (int i = 0; i < AllUser.size(); i++) {
                    calorieBurnedList.add(calorieBurnedDao.getTotalCalorieBurned(AllUser.get(i).getEmail()));
                }
                MergeSort(AllUser, calorieBurnedList, 0, AllUser.size() - 1);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        int yourPosition = -1;
                        for (int i = 0; i < AllUser.size(); i++) {
                            if (AllUser.get(i).getEmail().equals(email)) {
                                yourPosition = i;
                            }
                        }
                        RecyclerView rank_recyclerview = view.findViewById(R.id.rank_recyclerview);
                        rank_recyclerview.setLayoutManager(new LinearLayoutManager(view.getContext()));
                        rank_recyclerview.setAdapter(new RankRecyclerAdapter(AllUser, calorieBurnedList, view.getContext(), yourPosition));
                    }
                });
            }
        });
        return view;

    }

    public static void MergeSort(List<User> userList, List<Integer> calorieBurnedList, int first, int last) {
        if (first < last) {
            int mid = (first + last) / 2;
            MergeSort(userList, calorieBurnedList, first, mid);
            MergeSort(userList, calorieBurnedList, mid + 1, last);
            merge(userList, calorieBurnedList, first, mid, last);
        }
    }

    public static void merge(List<User> userList, List<Integer> calorieBurnedList, int first, int mid, int last) {
        List<User> tempUserList = new ArrayList<>();
        List<Integer> tempCalorieBurnedList = new ArrayList<>();

        int beginHalf1 = first;
        int endHalf1 = mid;
        int beginHalf2 = mid + 1;
        int endHalf2 = last;

        while (beginHalf1 <= endHalf1 && beginHalf2 <= endHalf2) {
            if (calorieBurnedList.get(beginHalf1) >= calorieBurnedList.get(beginHalf2)) {
                tempUserList.add(userList.get(beginHalf1));
                tempCalorieBurnedList.add(calorieBurnedList.get(beginHalf1));
                beginHalf1++;
            } else {
                tempUserList.add(userList.get(beginHalf2));
                tempCalorieBurnedList.add(calorieBurnedList.get(beginHalf2));
                beginHalf2++;
            }
        }

        while (beginHalf1 <= endHalf1) {
            tempUserList.add(userList.get(beginHalf1));
            tempCalorieBurnedList.add(calorieBurnedList.get(beginHalf1));
            beginHalf1++;
        }

        while (beginHalf2 <= endHalf2) {
            tempUserList.add(userList.get(beginHalf2));
            tempCalorieBurnedList.add(calorieBurnedList.get(beginHalf2));
            beginHalf2++;
        }

        for (int i = 0; i < tempUserList.size(); i++) {
            userList.set(first + i, tempUserList.get(i));
            calorieBurnedList.set(first + i, tempCalorieBurnedList.get(i));
        }
    }
}