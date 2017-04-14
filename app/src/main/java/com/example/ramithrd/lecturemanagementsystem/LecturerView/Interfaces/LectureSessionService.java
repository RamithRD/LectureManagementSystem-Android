package com.example.ramithrd.lecturemanagementsystem.LecturerView.Interfaces;

import com.example.ramithrd.lecturemanagementsystem.Models.Batch;
import com.example.ramithrd.lecturemanagementsystem.Models.LectureHall;
import com.example.ramithrd.lecturemanagementsystem.Models.LectureSession;
import com.example.ramithrd.lecturemanagementsystem.Models.Module;
import com.example.ramithrd.lecturemanagementsystem.Models.Programme;
import com.example.ramithrd.lecturemanagementsystem.Models.University;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by RamithRD on 4/14/2017.
 */

public interface LectureSessionService {

    @GET("Universities/{lecturerId}")
    Call<List<University>> getUniversities(@Path("lecturerId")String lecturerId);

    @GET("Programmes/{lecturerId}/{universityId}")
    Call<List<Programme>> getProgrammes(@Path("lecturerId")String lecturerId, @Path("programmeId")String universityId);

    @GET("Modules/{lecturerId}/{programmeId}")
    Call<List<Module>> getModules(@Path("lecturerId")String lecturerId, @Path("programmeId")String programmeId);

    @GET("Batches/{lecturerId}/{programmeId}")
    Call<List<Batch>> getBatches(@Path("lecturerId")String lecturerId, @Path("programmeId")String programmeId);

    @GET("LectureHalls")
    Call<List<LectureHall>> getLectureHalls();

    @POST("Session")
    @FormUrlEncoded
    Call<LectureSession> addLectureSession(@Body LectureSession lectureSession);

    @GET("DeleteSession/{sessionId}")
    Call<Boolean> cancelSession(@Path("sessionId")String sessionId);

    @PUT("UpdateSessionDateTime")
    @FormUrlEncoded
    Call<Boolean> updateSessionDateTime(@Body LectureSession lectureSession);

    @PUT("UpdateSession")
    @FormUrlEncoded
    Call<Boolean> updateSession(@Body LectureSession lectureSession);

    @GET("Session/{sessionId}")
    Call<LectureSession> getSingleSession(@Path("sessionId")String sessionId);

    //to generate QR - attendance
    @GET("CurrentSession/{lecturerId}")
    Call<LectureSession> getCurrentSession(@Path("lecturerId")String lecturerId);

}
