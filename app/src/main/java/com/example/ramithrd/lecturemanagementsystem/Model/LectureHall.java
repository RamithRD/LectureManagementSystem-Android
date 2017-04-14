
package com.example.ramithrd.lecturemanagementsystem.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LectureHall {

@SerializedName("Capacity")
@Expose
private Object capacity;
@SerializedName("Faculty")
@Expose
private String faculty;
@SerializedName("Floor_No")
@Expose
private String floorNo;
@SerializedName("Hall_Type")
@Expose
private Object hallType;
@SerializedName("Lecture_Hall_Name")
@Expose
private String lectureHallName;
@SerializedName("Lecture_Session")
@Expose
private Object lectureSession;

public Object getCapacity() {
return capacity;
}

public void setCapacity(Object capacity) {
this.capacity = capacity;
}

public String getFaculty() {
return faculty;
}

public void setFaculty(String faculty) {
this.faculty = faculty;
}

public String getFloorNo() {
return floorNo;
}

public void setFloorNo(String floorNo) {
this.floorNo = floorNo;
}

public Object getHallType() {
return hallType;
}

public void setHallType(Object hallType) {
this.hallType = hallType;
}

public String getLectureHallName() {
return lectureHallName;
}

public void setLectureHallName(String lectureHallName) {
this.lectureHallName = lectureHallName;
}

public Object getLectureSession() {
return lectureSession;
}

public void setLectureSession(Object lectureSession) {
this.lectureSession = lectureSession;
}

}