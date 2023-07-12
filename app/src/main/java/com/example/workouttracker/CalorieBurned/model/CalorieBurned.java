package com.example.workouttracker.CalorieBurned.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.text.DateFormat;
@Entity(tableName = "calorieBurned")
public class CalorieBurned {
    @NonNull
    @PrimaryKey
    @ColumnInfo(name="email")
    String email;

    @NonNull
    @ColumnInfo(name="dateYear")
    private int dateYear;

    @NonNull
    @ColumnInfo(name="dateMonth")
    private int dateMonth;
    @NonNull
    @ColumnInfo(name="dateDay")
    private int dateDay;
    @NonNull
    @ColumnInfo(name="totalCalorieBurned")
    private int totalCalorieBurned;

    public CalorieBurned() {
    }

    public CalorieBurned(@NonNull String email, int dateYear, int dateMonth, int dateDay, int totalCalorieBurned) {
        this.email = email;
        this.dateYear = dateYear;
        this.dateMonth = dateMonth;
        this.dateDay = dateDay;
        this.totalCalorieBurned = totalCalorieBurned;
    }

    @NonNull
    public String getEmail() {
        return email;
    }

    public void setEmail(@NonNull String email) {
        this.email = email;
    }

    public int getDateYear() {
        return dateYear;
    }

    public void setDateYear(int dateYear) {
        this.dateYear = dateYear;
    }

    public int getDateMonth() {
        return dateMonth;
    }

    public void setDateMonth(int dateMonth) {
        this.dateMonth = dateMonth;
    }

    public int getDateDay() {
        return dateDay;
    }

    public void setDateDay(int dateDay) {
        this.dateDay = dateDay;
    }

    public int getTotalCalorieBurned() {
        return totalCalorieBurned;
    }

    public void setTotalCalorieBurned(int totalCalorieBurned) {
        this.totalCalorieBurned = totalCalorieBurned;
    }
}
