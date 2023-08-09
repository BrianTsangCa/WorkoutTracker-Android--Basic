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

    @Query("SELECT email, dateYear,dateMonth,dateDay, workoutCalorieBurned FROM calorieBurned WHERE email=:Email")
    List<CalorieBurned> GetAllCalorieBurned(String Email);

    @Query("SELECT email,dateYear,dateMonth,dateDay,workoutCalorieBurned FROM calorieBurned WHERE email=:Email AND dateYear=:DateYear AND dateMonth=:DateMonth AND dateDay=:DateDay")
    CalorieBurned GetAllCalorieBurnedToday(String Email, int DateYear, int DateMonth, int DateDay);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCalorieBurned(CalorieBurned calorieBurned);


    @Query("DELETE FROM calorieBurned WHERE email=:email")
    void deleteAllCalorieBurned(String email);

    @Query("SELECT email, dateYear, dateMonth, dateDay, workoutCalorieBurned " +
            "FROM calorieBurned WHERE email=:email AND dateYear=:currentYear")
    List<CalorieBurned> getAllCalorieBurnedThisYear(String email, int currentYear);

    @Query("SELECT email, dateYear, dateMonth, dateDay, workoutCalorieBurned " +
            "FROM calorieBurned WHERE email=:email AND dateYear=:currentYear AND dateMonth=:currentMonth")
    List<CalorieBurned> getAllCalorieBurnedThisMonth(String email, int currentYear, int currentMonth);

    @Query("SELECT email, dateYear, dateMonth, dateDay, workoutCalorieBurned " +
            "FROM calorieBurned WHERE email=:email AND dateYear=:currentYear AND dateMonth=:currentMonth " +
            "AND ((dateDay >= :startDay AND dateDay <= :endDay) OR " +
            "(dateDay >= :prevStartDay AND dateDay <= :prevEndDay))")
    List<CalorieBurned> getAllCalorieBurnedLastWeek(String email, int currentYear, int currentMonth, int startDay, int endDay, int prevStartDay, int prevEndDay);

    @Query("SELECT sum(workoutCalorieBurned) FROM calorieBurned WHERE email=:email")
    int getTotalCalorieBurned(String email);

    @Query("SELECT sum(workoutCalorieBurned) FROM calorieBurned WHERE email=:email AND dateYear=:year AND dateMonth=:month")
    int getTotalCalorieBurnedForMonth(String email, int year, int month);

    @Query("DELETE FROM calorieBurned")
    public abstract void deleteAll();

    @Query("SELECT AVG(workoutCalorieBurned) FROM calorieBurned " +
            "WHERE email=:email AND dateYear=:lastMonthYear AND dateMonth=:lastMonth")
    double getAverageCaloriesBurnedLastMonth(String email, int lastMonthYear, int lastMonth);

}