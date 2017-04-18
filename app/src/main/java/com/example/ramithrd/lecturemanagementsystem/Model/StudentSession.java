package com.example.ramithrd.lecturemanagementsystem.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StudentSession {

@SerializedName("Batch_Id")
@Expose
private String batchId;
@SerializedName("Faculty")
@Expose
private String faculty;
@SerializedName("Hall_Name")
@Expose
private String hallName;
@SerializedName("Module_Id")
@Expose
private String moduleId;
@SerializedName("Name")
@Expose
private String name;
@SerializedName("Session_Date")
@Expose
private String sessionDate;
@SerializedName("Session_ET")
@Expose
private String sessionET;
@SerializedName("Session_ST")
@Expose
private String sessionST;

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

public String getHallName() {
return hallName;
}

public void setHallName(String hallName) {
this.hallName = hallName;
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

public String getSessionDate() {
return sessionDate;
}

public void setSessionDate(String sessionDate) {
this.sessionDate = sessionDate;
}

public String getSessionET() {
return sessionET;
}

public void setSessionET(String sessionET) {
this.sessionET = sessionET;
}

public String getSessionST() {
return sessionST;
}

public void setSessionST(String sessionST) {
this.sessionST = sessionST;
}

}