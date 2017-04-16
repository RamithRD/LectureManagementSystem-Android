package com.example.ramithrd.lecturemanagementsystem.Model;

/**
 * Created by RamithRD on 4/16/2017.
 */

public class Session {

    private String lecture_Id;
    private String module_name;
    private String batch_name;
    private String university_name;
    private String programme_name;
    private String lecture_hall;
    private String faculty;
    private String lec_date;
    private String lec_start_time;
    private String lec_end_time;

    public String getLecture_Id() {
        return lecture_Id;
    }

    public void setLecture_Id(String lecture_Id) {
        this.lecture_Id = lecture_Id;
    }

    public String getModule_name() {
        return module_name;
    }

    public void setModule_name(String module_name) {
        this.module_name = module_name;
    }

    public String getBatch_name() {
        return batch_name;
    }

    public void setBatch_name(String batch_name) {
        this.batch_name = batch_name;
    }

    public String getUniversity_name() {
        return university_name;
    }

    public void setUniversity_name(String university_name) {
        this.university_name = university_name;
    }

    public String getProgramme_name() {
        return programme_name;
    }

    public void setProgramme_name(String programme_name) {
        this.programme_name = programme_name;
    }

    public String getLecture_hall() {
        return lecture_hall;
    }

    public void setLecture_hall(String lecture_hall) {
        this.lecture_hall = lecture_hall;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getLec_date() {
        return lec_date;
    }

    public void setLec_date(String lec_date) {
        this.lec_date = lec_date;
    }

    public String getLec_start_time() {
        return lec_start_time;
    }

    public void setLec_start_time(String lec_start_time) {
        this.lec_start_time = lec_start_time;
    }

    public String getLec_end_time() {
        return lec_end_time;
    }

    public void setLec_end_time(String lec_end_time) {
        this.lec_end_time = lec_end_time;
    }
}
