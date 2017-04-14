package com.example.ramithrd.lecturemanagementsystem.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class University {

@SerializedName("Admins")
@Expose
private Object admins;
@SerializedName("Admins1")
@Expose
private Object admins1;
@SerializedName("Country")
@Expose
private Object country;
@SerializedName("Lecture_Session")
@Expose
private Object lectureSession;
@SerializedName("Lecturers")
@Expose
private Object lecturers;
@SerializedName("Name")
@Expose
private String name;
@SerializedName("Programmes")
@Expose
private Object programmes;
@SerializedName("Students")
@Expose
private Object students;
@SerializedName("University_Id")
@Expose
private String universityId;

public Object getAdmins() {
return admins;
}

public void setAdmins(Object admins) {
this.admins = admins;
}

public Object getAdmins1() {
return admins1;
}

public void setAdmins1(Object admins1) {
this.admins1 = admins1;
}

public Object getCountry() {
return country;
}

public void setCountry(Object country) {
this.country = country;
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

public String getName() {
return name;
}

public void setName(String name) {
this.name = name;
}

public Object getProgrammes() {
return programmes;
}

public void setProgrammes(Object programmes) {
this.programmes = programmes;
}

public Object getStudents() {
return students;
}

public void setStudents(Object students) {
this.students = students;
}

public String getUniversityId() {
return universityId;
}

public void setUniversityId(String universityId) {
this.universityId = universityId;
}

}