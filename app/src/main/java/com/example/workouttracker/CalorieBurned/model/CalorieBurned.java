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
    @ColumnInfo(name="date")
    private DateFormat date;

    @NonNull
    @ColumnInfo(name="totalCalorieBurned")
    private int totalCalorieBurned;

    public CalorieBurned() {
    }

    public CalorieBurned(@NonNull String email, @NonNull DateFormat date, int totalCalorieBurned) {
        this.email = email;
        this.date = date;
        this.totalCalorieBurned = totalCalorieBurned;
    }

    @NonNull
    public String getEmail() {
        return email;
    }

    public void setEmail(@NonNull String email) {
        this.email = email;
    }

    @NonNull
    public DateFormat getDate() {
        return date;
    }

    public void setDate(@NonNull DateFormat date) {
        this.date = date;
    }

    public int getTotalCalorieBurned() {
        return totalCalorieBurned;
    }

    public void setTotalCalorieBurned(int totalCalorieBurned) {
        this.totalCalorieBurned = totalCalorieBurned;
    }
}
