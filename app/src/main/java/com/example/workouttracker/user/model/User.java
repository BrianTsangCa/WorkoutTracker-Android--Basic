package com.example.workouttracker.user.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.workouttracker.CalorieBurned.model.CalorieBurned;

import java.util.List;

@Entity(tableName = "users")
public class User {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name="email")
    String email;

    @NonNull
    @ColumnInfo(name="userName")
    String userName;

    @NonNull
    @ColumnInfo(name="weightInPounds")
    int weightInPounds;

//    @NonNull
//    @ColumnInfo(name="calorieBurned")
//    List<CalorieBurned> calorieBurned;

    public User(@NonNull String email, @NonNull String userName, int weightInPounds) {
        this.email = email;
        this.userName = userName;
        this.weightInPounds = weightInPounds;
    }
}
