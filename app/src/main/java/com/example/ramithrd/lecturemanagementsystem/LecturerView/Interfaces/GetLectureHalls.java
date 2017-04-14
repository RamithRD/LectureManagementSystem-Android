package com.example.ramithrd.lecturemanagementsystem.LecturerView.Interfaces;

import com.example.ramithrd.lecturemanagementsystem.LecturerView.Responses.LecHallEndpointResponse;
import com.example.ramithrd.lecturemanagementsystem.Models.LectureHall;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by RamithRD on 4/14/2017.
 */

public interface GetLectureHalls {

    @GET("LectureHalls")
    Call<List<LectureHall>> getLectureHalls();

}
