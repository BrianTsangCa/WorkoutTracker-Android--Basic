package com.example.workouttracker.CalorieBurned.model;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.workouttracker.user.model.User;

import java.util.List;
@Dao
public interface CalorieBurnedDao {

        @Query("SELECT email, dateYear,dateMonth,dateDay, totalCalorieBurned FROM calorieBurned WHERE email=:Email")
        List<CalorieBurned> GetAllCalorieBurned(String Email);

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        void insertCalorieBurned(CalorieBurned calorieBurned);
}