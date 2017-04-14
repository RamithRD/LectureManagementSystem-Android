package com.example.ramithrd.lecturemanagementsystem.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Programme {

@SerializedName("Batches")
@Expose
private Object batches;
@SerializedName("Delivery_Type")
@Expose
private Object deliveryType;
@SerializedName("Duration")
@Expose
private Object duration;
@SerializedName("Lecture_Session")
@Expose
private Object lectureSession;
@SerializedName("Lecturers")
@Expose
private Object lecturers;
@SerializedName("Modules")
@Expose
private Object modules;
@SerializedName("Name")
@Expose
private String name;
@SerializedName("Programme_Id")
@Expose
private String programmeId;
@SerializedName("Students")
@Expose
private Object students;
@SerializedName("University")
@Expose
private Object university;
@SerializedName("University_Id")
@Expose
private Object universityId;

public Object getBatches() {
return batches;
}

public void setBatches(Object batches) {
this.batches = batches;
}

public Object getDeliveryType() {
return deliveryType;
}

public void setDeliveryType(Object deliveryType) {
this.deliveryType = deliveryType;
}

public Object getDuration() {
return duration;
}

public void setDuration(Object duration) {
this.duration = duration;
}

public Object getLectureSession() {
return lectureSession;
}

public void setLectureSession(Object lectureSession) {
this.lectureSession = lectureSession;
}

public Object getLecturers() {
return lecturers;
}

public void setLecturers(Object lecturers) {
this.lecturers = lecturers;
}

public Object getModules() {
return modules;
}

public void setModules(Object modules) {
this.modules = modules;
}

public String getName() {
return name;
}

public void setName(String name) {
this.name = name;
}

public String getProgrammeId() {
return programmeId;
}

public void setProgrammeId(String programmeId) {
this.programmeId = programmeId;
}

public Object getStudents() {
return students;
}

public void setStudents(Object students) {
this.students = students;
}

public Object getUniversity() {
return university;
}

public void setUniversity(Object university) {
this.university = university;
}

public Object getUniversityId() {
return universityId;
}

public void setUniversityId(Object universityId) {
this.universityId = universityId;
}

}