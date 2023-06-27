package com.example.workouttracker.workout.model;

public class Workout {
    private String title;
    private String category;
    private String caloriePerHour;
    private String difficulty;

    public Workout() {
    }

    public Workout(String title, String category, String caloriePerHour) {
        this.title = title;
        this.category = category;
        this.caloriePerHour = caloriePerHour;
        if(Integer.parseInt(caloriePerHour)>=300){
            difficulty="Hard";
        }else if(Integer.parseInt(caloriePerHour)>=200){
            difficulty="Medium";
        }else {
            difficulty="Easy";
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCaloriePerHour() {
        return caloriePerHour;
    }

    public void setCaloriePerHour(String caloriePerHour) {
        this.caloriePerHour = caloriePerHour;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }
}
