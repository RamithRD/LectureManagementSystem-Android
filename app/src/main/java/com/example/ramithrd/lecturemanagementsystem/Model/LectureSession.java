package com.example.ramithrd.lecturemanagementsystem.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LectureSession {

@SerializedName("Batch")
@Expose
private Object batch;
@SerializedName("Batch_Id")
@Expose
private String batchId;
@SerializedName("Faculty")
@Expose
private String faculty;
@SerializedName("Lecture_Hall")
@Expose
private Object lectureHall;
@SerializedName("Lecture_Hall_Name")
@Expose
private String lectureHallName;
@SerializedName("Lecturer")
@Expose
private Object lecturer;
@SerializedName("Lecturer_Id")
@Expose
private String lecturerId;
@SerializedName("Lecturer_First_Name")
@Expose
private String Lecturer_First_Name;
@SerializedName("Lecturer_Last_Name")
@Expose
private String Lecturer_Last_Name;
@SerializedName("Module")
@Expose
private Object module;
@SerializedName("Module_Id")
@Expose
private String moduleId;
@SerializedName("Module_Name")
@Expose
private String moduleName;
@SerializedName("Programme")
@Expose
private Object programme;
@SerializedName("Programme_Id")
@Expose
private String programmeId;
@SerializedName("Programme_Name")
@Expose
private String programmeName;
@SerializedName("Session_Date")
@Expose
private String sessionDate;
@SerializedName("Session_End_Time")
@Expose
private String sessionEndTime;
@SerializedName("Session_End_Time_Text")
@Expose
private String sessionEndTimeText;
@SerializedName("Session_Id")
@Expose
private Integer sessionId;
@SerializedName("Session_Start_Time")
@Expose
private String sessionStartTime;
@SerializedName("Session_Start_Time_Text")
@Expose
private String sessionStartTimeText;
@SerializedName("Students")
@Expose
private Object students;
@SerializedName("University")
@Expose
private Object university;
@SerializedName("University_Id")
@Expose
private String universityId;
@SerializedName("University_Name")
@Expose
private String universityName;
@SerializedName("User_Id")
@Expose
private String userId;

public Object getBatch() {
return batch;
}

public void setBatch(Object batch) {
this.batch = batch;
}

public String getBatchId() {
return batchId;
}

public void setBatchId(String batchId) {
this.batchId = batchId;
}

public String getFaculty() {
return faculty;
}

public void setFaculty(String faculty) {
this.faculty = faculty;
}

public Object getLectureHall() {
return lectureHall;
}

public void setLectureHall(Object lectureHall) {
this.lectureHall = lectureHall;
}

public String getLectureHallName() {
return lectureHallName;
}

public void setLectureHallName(String lectureHallName) {
this.lectureHallName = lectureHallName;
}

public Object getLecturer() {
return lecturer;
}

public void setLecturer(Object lecturer) {
this.lecturer = lecturer;
}

public String getLecturerId() {
return lecturerId;
}

public void setLecturerId(String lecturerId) {
this.lecturerId = lecturerId;
}

public String getLecturer_First_Name() {
        return Lecturer_First_Name;
    }

public void setLecturer_First_Name(String lecturer_First_Name) {
        Lecturer_First_Name = lecturer_First_Name;
    }

public String getLecturer_Last_Name() {
        return Lecturer_Last_Name;
    }

public void setLecturer_Last_Name(String lecturer_Last_Name) {
        Lecturer_Last_Name = lecturer_Last_Name;
    }

public Object getModule() {
return module;
}

public void setModule(Object module) {
this.module = module;
}

public String getModuleId() {
return moduleId;
}

public void setModuleId(String moduleId) {
this.moduleId = moduleId;
}

public String getModuleName() {
return moduleName;
}

public void setModuleName(String moduleName) {
this.moduleName = moduleName;
}

public Object getProgramme() {
return programme;
}

public void setProgramme(Object programme) {
this.programme = programme;
}

public String getProgrammeId() {
return programmeId;
}

public void setProgrammeId(String programmeId) {
this.programmeId = programmeId;
}

public String getProgrammeName() {
return programmeName;
}

public void setProgrammeName(String programmeName) {
this.programmeName = programmeName;
}

public String getSessionDate() {
return sessionDate;
}

public void setSessionDate(String sessionDate) {
this.sessionDate = sessionDate;
}

public Object getSessionEndTime() {
return sessionEndTime;
}

public void setSessionEndTime(String sessionEndTime) {
this.sessionEndTime = sessionEndTime;
}

public String getSessionEndTimeText() {
return sessionEndTimeText;
}

public void setSessionEndTimeText(String sessionEndTimeText) {
this.sessionEndTimeText = sessionEndTimeText;
}

public Integer getSessionId() {
return sessionId;
}

public void setSessionId(Integer sessionId) {
this.sessionId = sessionId;
}

public String getSessionStartTime() {
return sessionStartTime;
}

public void setSessionStartTime(String sessionStartTime) {
this.sessionStartTime = sessionStartTime;
}

public String getSessionStartTimeText() {
return sessionStartTimeText;
}

public void setSessionStartTimeText(String sessionStartTimeText) {
this.sessionStartTimeText = sessionStartTimeText;
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

public String getUniversityId() {
return universityId;
}

public void setUniversityId(String universityId) {
this.universityId = universityId;
}

public String getUniversityName() {
return universityName;
}

public void setUniversityName(String universityName) {
this.universityName = universityName;
}

public String getUserId() {
return userId;
}

public void setUserId(String userId) {
this.userId = userId;
}

}