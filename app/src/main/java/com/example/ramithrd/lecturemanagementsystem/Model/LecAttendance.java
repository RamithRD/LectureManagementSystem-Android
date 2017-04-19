package com.example.ramithrd.lecturemanagementsystem.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by RamithRD on 4/18/2017.
 */

public class LecAttendance {

    @SerializedName("User_Id")
    @Expose
    private String User_Id;

    @SerializedName("Student_Id")
    @Expose
    private String Student_Id;

    @SerializedName("Session_Id")
    @Expose
    private String Session_Id;

    public String getUser_Id() {
        return User_Id;
    }

    public void setUser_Id(String user_Id) {
        User_Id = user_Id;
    }

    public String getStudent_Id() {
        return Student_Id;
    }

    public void setStudent_Id(String student_Id) {
        Student_Id = student_Id;
    }

    public String getSession_Id() {
        return Session_Id;
    }

    public void setSession_Id(String session_Id) {
        Session_Id = session_Id;
    }
}
