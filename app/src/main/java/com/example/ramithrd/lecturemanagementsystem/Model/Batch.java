package com.example.ramithrd.lecturemanagementsystem.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Batch {

@SerializedName("Batch_Id")
@Expose
private String batchId;
@SerializedName("Learning_Materials")
@Expose
private Object learningMaterials;
@SerializedName("Lecture_Session")
@Expose
private Object lectureSession;
@SerializedName("Lecturers")
@Expose
private Object lecturers;
@SerializedName("Modules")
@Expose
private Object modules;
@SerializedName("No_Of_Student")
@Expose
private Object noOfStudent;
@SerializedName("Programme")
@Expose
private Object programme;
@SerializedName("Programme_Id")
@Expose
private Object programmeId;
@SerializedName("Students")
@Expose
private Object students;

public String getBatchId() {
return batchId;
}

public void setBatchId(String batchId) {
this.batchId = batchId;
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

public Object getNoOfStudent() {
return noOfStudent;
}

public void setNoOfStudent(Object noOfStudent) {
this.noOfStudent = noOfStudent;
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

public Object getStudents() {
return students;
}

public void setStudents(Object students) {
this.students = students;
}

}