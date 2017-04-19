package com.example.ramithrd.lecturemanagementsystem.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by RamithRD on 4/19/2017.
 */

public class User implements Parcelable {

    private String userId;
    private String first_name;
    private String last_name;
    private String user_role;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getUser_role() {
        return user_role;
    }

    public void setUser_role(String user_role) {
        this.user_role = user_role;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.userId);
        dest.writeString(this.first_name);
        dest.writeString(this.last_name);
        dest.writeString(this.user_role);
    }

    public User() {
    }

    protected User(Parcel in) {
        this.userId = in.readString();
        this.first_name = in.readString();
        this.last_name = in.readString();
        this.user_role = in.readString();
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}

