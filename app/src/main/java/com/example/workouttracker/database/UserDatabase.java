package com.example.workouttracker.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.workouttracker.user.model.User;
import com.example.workouttracker.user.model.UserDao;

@Database(entities = {User.class}, version=1, exportSchema = false)
public abstract class UserDatabase extends RoomDatabase {
    public abstract UserDao userDao();
}
