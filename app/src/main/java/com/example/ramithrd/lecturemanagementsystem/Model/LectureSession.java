package com.example.ramithrd.lecturemanagementsystem.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by RamithRD on 4/15/2017.
 */

public class LectureSession {

    @SerializedName("User_Id")
    public String User_Id;

    @SerializedName("Lecturer_Id")
    public String Lecturer_Id;

    @SerializedName("Batch_Id")
    public String Batch_Id;

    @SerializedName("Module_Id")
    public String Module_Id;

    @SerializedName("University_Id")
    public String University_Id;

    @SerializedName("Programme_Id")
    public String Programme_Id;

    @SerializedName("Lecture_Hall_Name")
    public String Lecture_Hall_Name;

    @SerializedName("Faculty")
    public String Faculty;

    @SerializedName("Session_Start_Time")
    public String Session_Start_Time;

    @SerializedName("Session_End_Time")
    public String Session_End_Time;

    @SerializedName("Session_Date")
    public String Session_Date;

    public LectureSession(String user_Id, String lecturer_Id, String batch_Id, String module_Id, String university_Id, String programme_Id, String lecture_Hall_Name, String faculty, String session_Start_Time, String session_End_Time, String session_Date) {
        User_Id = user_Id;
        Lecturer_Id = lecturer_Id;
        Batch_Id = batch_Id;
        Module_Id = module_Id;
        University_Id = university_Id;
        Programme_Id = programme_Id;
        Lecture_Hall_Name = lecture_Hall_Name;
        Faculty = faculty;
        Session_Start_Time = session_Start_Time;
        Session_End_Time = session_End_Time;
        Session_Date = session_Date;
    }
}
