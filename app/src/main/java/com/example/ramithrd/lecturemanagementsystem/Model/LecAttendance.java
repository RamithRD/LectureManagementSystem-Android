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

}
