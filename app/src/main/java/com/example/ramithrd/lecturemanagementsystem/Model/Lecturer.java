package com.example.ramithrd.lecturemanagementsystem.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Asus on 4/25/2017.
 */

public class Lecturer {

    @SerializedName("User_Id")
    @Expose
    String User_Id;

    @SerializedName("Lecturer_Id")
    @Expose
    String Lecturer_Id;

    @SerializedName("First_Name")
    @Expose
    String First_Name;

    @SerializedName("Last_Name")
    @Expose
    String Last_Name;

    @SerializedName("Email")
    @Expose
    String Email;

    @SerializedName("Lecturer_Password")
    @Expose
    String Lecturer_Password;

    public String getUser_Id() {
        return User_Id;
    }

    public void setUser_Id(String user_Id) {
        User_Id = user_Id;
    }

    public String getLecturer_Id() {
        return Lecturer_Id;
    }

    public void setLecturer_Id(String lecturer_Id) {
        Lecturer_Id = lecturer_Id;
    }

    public String getFirst_Name() {
        return First_Name;
    }

    public void setFirst_Name(String first_Name) {
        First_Name = first_Name;
    }

    public String getLast_Name() {
        return Last_Name;
    }

    public void setLast_Name(String last_Name) {
        Last_Name = last_Name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getLecturer_Password() {
        return Lecturer_Password;
    }

    public void setLecturer_Password(String lecturer_Password) {
        Lecturer_Password = lecturer_Password;
    }
}
