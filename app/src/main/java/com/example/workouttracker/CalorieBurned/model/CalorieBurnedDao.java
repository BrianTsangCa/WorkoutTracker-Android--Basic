package com.example.workouttracker.CalorieBurned.model;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.workouttracker.user.model.User;

import java.util.List;
@Dao
public interface CalorieBurnedDao {

        @Query("SELECT email, date, totalCalorieBurned FROM calorieBurned")
        List<User> GetAllCalorieBurned();



}
