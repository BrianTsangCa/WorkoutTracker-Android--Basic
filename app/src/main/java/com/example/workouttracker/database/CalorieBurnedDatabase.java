package com.example.workouttracker.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.workouttracker.CalorieBurned.model.CalorieBurned;
import com.example.workouttracker.CalorieBurned.model.CalorieBurnedDao;
import com.example.workouttracker.user.model.User;
import com.example.workouttracker.user.model.UserDao;

@Database(entities = {CalorieBurned.class}, version=3, exportSchema = false)
public abstract class CalorieBurnedDatabase extends RoomDatabase {
    public abstract CalorieBurnedDao calorieBurnedDao();
}
