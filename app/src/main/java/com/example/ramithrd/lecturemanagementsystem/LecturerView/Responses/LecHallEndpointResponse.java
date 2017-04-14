package com.example.ramithrd.lecturemanagementsystem.LecturerView.Responses;

import com.example.ramithrd.lecturemanagementsystem.Models.LectureHall;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by RamithRD on 4/14/2017.
 */

public class LecHallEndpointResponse {

    private List<LectureHall> lectureHalls;

    // public constructor is necessary for collections
    public LecHallEndpointResponse() {
        lectureHalls = new ArrayList<LectureHall>();
    }

    public static LecHallEndpointResponse[] parseJSON(String response) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        Type collectionType = new TypeToken<Collection<LectureHall>>(){}.getType();
        Gson gson = gsonBuilder.create();
        LecHallEndpointResponse[] employeeEndpointResponse = gson.fromJson(response, LecHallEndpointResponse[].class);
        return employeeEndpointResponse;
    }

    public List<LectureHall> getLectureHalls() {
        return lectureHalls;
    }
}
