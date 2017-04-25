package com.example.ramithrd.lecturemanagementsystem.AdminsView.Interfaces;

import com.example.ramithrd.lecturemanagementsystem.Model.Batch;
import com.example.ramithrd.lecturemanagementsystem.Model.LectureHall;
import com.example.ramithrd.lecturemanagementsystem.Model.LectureSession;
import com.example.ramithrd.lecturemanagementsystem.Model.Lecturer;
import com.example.ramithrd.lecturemanagementsystem.Model.Module;
import com.example.ramithrd.lecturemanagementsystem.Model.Programme;
import com.example.ramithrd.lecturemanagementsystem.Model.University;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by Asus on 4/25/2017.
 */

public interface AdminService {

    @GET("Universities/{adminId}")
    Call<List<University>> getUniversities(@Path("adminId")String adminId);

    @GET("Programmes/{adminId}/{universityId}")
    Call<List<Programme>> getProgrammes(@Path("adminId")String lecturerId, @Path("universityId")String universityId);

    @GET("Modules/{adminId}/{programmeId}")
    Call<List<Module>> getModules(@Path("adminId")String lecturerId, @Path("programmeId")String programmeId);

    @GET("Batches/{adminId}/{programmeId}")
    Call<List<Batch>> getBatches(@Path("adminId")String lecturerId, @Path("programmeId")String programmeId);

    @GET("LectureHalls")
    Call<List<LectureHall>> getLectureHalls();

    @POST("Session")
    Call<Integer> AddSession(@Body LectureSession lectureSession);

    @GET("Sessions/{adminId}")
    Call<List<LectureSession>> getAllSessions(@Path("adminId")String lecturerId);

    @GET("DeleteSession/{sessionId}")
    Call<Boolean> cancelSession(@Path("sessionId")int sessionId);

    @PUT("UpdateSessionDateTime")
    Call<Boolean> updateSessionDateTime(@Body LectureSession lectureSession);

    @PUT("UpdateSession")
    Call<Boolean> updateSession(@Body LectureSession lectureSession);

    @GET("Session/{sessionId}")
    Call<LectureSession> getSingleSession(@Path("sessionId")String sessionId);

    @GET("Lecturers/{universityId}")
    Call<List<Lecturer>> getLecturers(@Path("universityId")String universityId);

}
