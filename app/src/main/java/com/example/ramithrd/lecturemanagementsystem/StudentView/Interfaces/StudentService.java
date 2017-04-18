package com.example.ramithrd.lecturemanagementsystem.StudentView.Interfaces;

import com.example.ramithrd.lecturemanagementsystem.Model.LecAttendance;
import com.example.ramithrd.lecturemanagementsystem.Model.LectureSession;
import com.example.ramithrd.lecturemanagementsystem.Model.StudentSession;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by RamithRD on 4/18/2017.
 */

public interface StudentService {

    @GET("stdDetails/{id}")
    Call<List<StudentSession>> getLecturesForStudent(@Path("id")String studentId);

    @POST("Attendance")
    Call<Boolean> AddSession(@Body LecAttendance attendance);

}
