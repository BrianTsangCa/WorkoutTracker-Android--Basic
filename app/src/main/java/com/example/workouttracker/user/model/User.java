package com.example.workouttracker.user.model;

import com.example.workouttracker.workout.model.WorkoutHistories;

import java.util.List;

public class User {
    private String loginname;
    private String name;
    private int weight;
    private List<WorkoutHistories> workoutHistories;
    public User() {
    }

    public User(String loginname, String name, int weight, List<WorkoutHistories> workoutHistories) {
        this.loginname = loginname;
        this.name = name;
        this.weight = weight;
        this.workoutHistories = workoutHistories;
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public List<WorkoutHistories> getWorkoutHistories() {
        return workoutHistories;
    }

    public void setWorkoutHistories(List<WorkoutHistories> workoutHistories) {
        this.workoutHistories = workoutHistories;
    }
}
