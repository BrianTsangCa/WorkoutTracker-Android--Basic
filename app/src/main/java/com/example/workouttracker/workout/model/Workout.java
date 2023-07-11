package com.example.workouttracker.workout.model;

import com.example.workouttracker.R;

public class Workout {
    private String name;
    private String calories_per_hour;

    private String category;
    private Difficulty diffculty;
    private int workoutImg;



    public Workout() {
    }

    public Workout(String name, String calories_per_hour, String category, Difficulty diffculty) {
        this.name = name;
        this.calories_per_hour = calories_per_hour;
        this.category = category;
        this.diffculty = diffculty;
        if(category.equals("Skiing")){
            workoutImg= R.drawable.skii;
        }else if(category.equals("Cycling")){
            workoutImg= R.drawable.cycling;
        }else if(category.equals("Aerobics")){
            workoutImg= R.drawable.aerobics;
        }else{
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCalories_per_hour() {
        return calories_per_hour;
    }

    public void setCalories_per_hour(String calories_per_hour) {
        this.calories_per_hour = calories_per_hour;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Difficulty getDiffculty() {
        return diffculty;
    }

    public void setDiffculty(Difficulty diffculty) {
        this.diffculty = diffculty;
    }

    public int getWorkoutImg() {
        return workoutImg;
    }

    public void setWorkoutImg(int workoutImg) {
        this.workoutImg = workoutImg;
    }
}
