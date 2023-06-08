package com.example.workouttracker.workout.model;

public class WorkoutHistories {
    private String category;
    private String difficulty;
    private String duration;
    private String date;

    public WorkoutHistories() {
    }

    public WorkoutHistories(String category, String difficulty, String duration, String date) {
        this.category = category;
        this.difficulty = difficulty;
        this.duration = duration;
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
