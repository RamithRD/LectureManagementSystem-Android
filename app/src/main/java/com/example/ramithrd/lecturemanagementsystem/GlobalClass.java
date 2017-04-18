package com.example.ramithrd.lecturemanagementsystem;

import android.app.Application;

/**
 * Created by RamithRD on 3/14/2017.
 */

public class GlobalClass extends Application {

    private String lecturerID;
    private String studentID;

    @Override
    public void onCreate() {
        super.onCreate();

    }

    public String getLecturerID() {
        return lecturerID;
    }

    public void setLecturerID(String lecturerID) {
        this.lecturerID = lecturerID;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }
}
