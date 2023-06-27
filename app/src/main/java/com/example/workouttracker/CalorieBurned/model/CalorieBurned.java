package com.example.workouttracker.CalorieBurned.model;

import java.text.DateFormat;

public class CalorieBurned {
    private DateFormat date;
    private double totalCalorieBurned;

    public CalorieBurned() {
    }

    public CalorieBurned(DateFormat date, double totalCalorieBurned) {
        this.date = date;
        this.totalCalorieBurned = totalCalorieBurned;
    }

    public DateFormat getDate() {
        return date;
    }

    public void setDate(DateFormat date) {
        this.date = date;
    }

    public double getTotalCalorieBurned() {
        return totalCalorieBurned;
    }

    public void setTotalCalorieBurned(double totalCalorieBurned) {
        this.totalCalorieBurned = totalCalorieBurned;
    }
}
