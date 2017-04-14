package com.example.ramithrd.lecturemanagementsystem.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Module {

@SerializedName("Batches")
@Expose
private Object batches;
@SerializedName("Credits")
@Expose
private Object credits;
@SerializedName("Learning_Materials")
@Expose
private Object learningMaterials;
@SerializedName("Lecture_Session")
@Expose
private Object lectureSession;
@SerializedName("Lecturer")
@Expose
private Object lecturer;
@SerializedName("Lecturer_Id")
@Expose
private Object lecturerId;
@SerializedName("Module_Id")
@Expose
private String moduleId;
@SerializedName("Name")
@Expose
private String name;
@SerializedName("No_Of_Hours")
@Expose
private Object noOfHours;
@SerializedName("Programme")
@Expose
private Object programme;
@SerializedName("Programme_Id")
@Expose
private Object programmeId;
@SerializedName("User_Id")
@Expose
private Object userId;

public Object getBatches() {
return batches;
}

public void setBatches(Object batches) {
this.batches = batches;
}

public Object getCredits() {
return credits;
}

public void setCredits(Object credits) {
this.credits = credits;
}

public Object getLearningMaterials() {
return learningMaterials;
}

public void setLearningMaterials(Object learningMaterials) {
this.learningMaterials = learningMaterials;
}

public Object getLectureSession() {
return lectureSession;
}

public void setLectureSession(Object lectureSession) {
this.lectureSession = lectureSession;
}

public Object getLecturer() {
return lecturer;
}

public void setLecturer(Object lecturer) {
this.lecturer = lecturer;
}

public Object getLecturerId() {
return lecturerId;
}

public void setLecturerId(Object lecturerId) {
this.lecturerId = lecturerId;
}

public String getModuleId() {
return moduleId;
}

public void setModuleId(String moduleId) {
this.moduleId = moduleId;
}

public String getName() {
return name;
}

public void setName(String name) {
this.name = name;
}

public Object getNoOfHours() {
return noOfHours;
}

public void setNoOfHours(Object noOfHours) {
this.noOfHours = noOfHours;
}

public Object getProgramme() {
return programme;
}

public void setProgramme(Object programme) {
this.programme = programme;
}

public Object getProgrammeId() {
return programmeId;
}

public void setProgrammeId(Object programmeId) {
this.programmeId = programmeId;
}

public Object getUserId() {
return userId;
}

public void setUserId(Object userId) {
this.userId = userId;
}

}