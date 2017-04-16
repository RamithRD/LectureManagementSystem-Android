package com.example.ramithrd.lecturemanagementsystem.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by RamithRD on 4/16/2017.
 */

public class Session implements Parcelable {

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.lecture_Id);
        dest.writeString(this.module_name);
        dest.writeString(this.batch_name);
        dest.writeString(this.university_name);
        dest.writeString(this.programme_name);
        dest.writeString(this.lecture_hall);
        dest.writeString(this.faculty);
        dest.writeString(this.lec_date);
        dest.writeString(this.lec_start_time);
        dest.writeString(this.lec_end_time);
    }

    public Session() {
    }

    protected Session(Parcel in) {
        this.lecture_Id = in.readString();
        this.module_name = in.readString();
        this.batch_name = in.readString();
        this.university_name = in.readString();
        this.programme_name = in.readString();
        this.lecture_hall = in.readString();
        this.faculty = in.readString();
        this.lec_date = in.readString();
        this.lec_start_time = in.readString();
        this.lec_end_time = in.readString();
    }

    public static final Parcelable.Creator<Session> CREATOR = new Parcelable.Creator<Session>() {
        @Override
        public Session createFromParcel(Parcel source) {
            return new Session(source);
        }

        @Override
        public Session[] newArray(int size) {
            return new Session[size];
        }
    };
}