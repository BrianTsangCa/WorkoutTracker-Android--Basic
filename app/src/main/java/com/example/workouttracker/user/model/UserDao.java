package com.example.workouttracker.user.model;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT email, userName, weightInPounds FROM users")
    List<User> GetAllUsers();

    @Insert(onConflict= OnConflictStrategy.REPLACE)
    Long[] insertUsersfromList(List<User> users);

}
