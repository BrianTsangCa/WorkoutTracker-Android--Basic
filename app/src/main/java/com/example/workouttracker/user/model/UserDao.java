package com.example.workouttracker.user.model;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT email,userName, weightInPounds FROM users WHERE email=:Email")
    User GetUsers(String Email);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUser(User user);


}
