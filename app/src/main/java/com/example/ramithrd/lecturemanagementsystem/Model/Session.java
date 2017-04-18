package com.example.ramithrd.lecturemanagementsystem.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by RamithRD on 4/16/2017.
 */

public class Session implements Parcelable {

    private int session_Id;
    private String lecturer_Id;
    private String lecturer_name;
    private String module_Id;
    private String module_name;
    private String batch_name;
    private String university_name;
    private String programme_name;
    private String lecture_hall;
    private String faculty;
    private String lec_date;
    private String lec_start_time;
    private String lec_end_time;

    public int getSession_Id() {
        return session_Id;
    }

    public void setSession_Id(int session_Id) {
        this.session_Id = session_Id;
    }

    public String getLecturer_Id() {
        return lecturer_Id;
    }

    public void setLecturer_Id(String lecturer_Id) {
        this.lecturer_Id = lecturer_Id;
    }

    public String getLecturer_name() {
        return lecturer_name;
    }

    public void setLecturer_name(String lecturer_name) {
        this.lecturer_name = lecturer_name;
    }

    public String getModule_Id() {
        return module_Id;
    }

    public void setModule_Id(String module_Id) {
        this.module_Id = module_Id;
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
        dest.writeInt(this.session_Id);
        dest.writeString(this.lecturer_Id);
        dest.writeString(this.lecturer_name);
        dest.writeString(this.module_Id);
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
        this.session_Id = in.readInt();
        this.lecturer_Id = in.readString();
        this.lecturer_name = in.readString();
        this.module_Id = in.readString();
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
