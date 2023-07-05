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

    @NonNull
    public String getEmail() {
        return email;
    }

    public void setEmail(@NonNull String email) {
        this.email = email;
    }

    @NonNull
    public String getUserName() {
        return userName;
    }

    public void setUserName(@NonNull String userName) {
        this.userName = userName;
    }

    public int getWeightInPounds() {
        return weightInPounds;
    }

    public void setWeightInPounds(int weightInPounds) {
        this.weightInPounds = weightInPounds;
    }
}
