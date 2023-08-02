package com.example.workouttracker.CalorieBurned.model;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.workouttracker.user.model.User;

import java.util.List;

@Dao
public interface CalorieBurnedDao {

    @Query("SELECT workoutID,email, dateYear,dateMonth,dateDay, workoutCalorieBurned FROM calorieBurned WHERE email=:Email")
    List<CalorieBurned> GetAllCalorieBurned(String Email);

    @Query("SELECT sum(workoutCalorieBurned) FROM calorieBurned WHERE email=:Email AND dateYear=:DateYear AND dateMonth=:DateMonth AND dateDay=:DateDay")
    int GetAllCalorieBurnedToday(String Email, int DateYear, int DateMonth, int DateDay);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCalorieBurned(CalorieBurned calorieBurned);

    @Query("DELETE FROM calorieBurned WHERE email=:email")
    void deleteAllCalorieBurned(String email);

    @Query("SELECT workoutID, email, dateYear, dateMonth, dateDay, workoutCalorieBurned " +
            "FROM calorieBurned WHERE email=:email AND dateYear=:currentYear")
    List<CalorieBurned> getAllCalorieBurnedThisYear(String email, int currentYear);

    @Query("SELECT workoutID, email, dateYear, dateMonth, dateDay, workoutCalorieBurned " +
            "FROM calorieBurned WHERE email=:email AND dateYear=:currentYear AND dateMonth=:currentMonth")
    List<CalorieBurned> getAllCalorieBurnedThisMonth(String email, int currentYear, int currentMonth);

    @Query("SELECT workoutID, email, dateYear, dateMonth, dateDay, workoutCalorieBurned " +
            "FROM calorieBurned WHERE email=:email AND dateYear=:currentYear AND dateMonth=:currentMonth " +
            "AND ((dateDay >= :startDay AND dateDay <= :endDay) OR " +
            "(dateDay >= :prevStartDay AND dateDay <= :prevEndDay))")
    List<CalorieBurned> getAllCalorieBurnedLastWeek(String email, int currentYear, int currentMonth, int startDay, int endDay, int prevStartDay, int prevEndDay);


}