package com.example.ramithrd.lecturemanagementsystem.AdminsView.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ramithrd.lecturemanagementsystem.AdminsView.ViewHolders.AdminViewHolder;
import com.example.ramithrd.lecturemanagementsystem.LecturerView.Interfaces.OnSessionStateListener;
import com.example.ramithrd.lecturemanagementsystem.Model.Session;
import com.example.ramithrd.lecturemanagementsystem.R;

import java.util.ArrayList;

/**
 * Created by Asus on 4/25/2017.
 */

public class AdminAdapter extends RecyclerView.Adapter<AdminViewHolder> {

    public ArrayList<Session> sessionsList = new ArrayList<>();
    private Session lecSession;
    private OnSessionStateListener mCallback;

    public AdminAdapter(ArrayList<Session> sessionsList) {
        this.sessionsList = sessionsList;
        mCallback = new OnSessionStateListener() {

            @Override
            public void lecSessionCancelled(int position) {
                removeSessionFromList(position);
            }

        };
    }

    private void removeSessionFromList(int position) {
        sessionsList.remove(position);
        notifyDataSetChanged();
    }

    @Override
    public AdminViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.admin_session_list_item, parent, false);

        return new AdminViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AdminViewHolder holder, int position) {

        lecSession = sessionsList.get(position);

        holder.lecturerNameTxt.setText(lecSession.getLecturer_name());

        holder.moduleIdTxt.setText(lecSession.getModule_Id());
        holder.moduleNameTxt.setText(lecSession.getModule_name());

        String startTime = lecSession.getLec_start_time();
        holder.startTimeTxt.setText(startTime.substring(startTime.indexOf(' ')));

        String endTime = lecSession.getLec_end_time();
        holder.endTimeTxt.setText(endTime.substring(endTime.indexOf(' ')));

        String lecVenue = lecSession.getLecture_hall()+" ("+lecSession.getFaculty()+")";
        holder.venueTxt.setText(lecVenue);
        holder.batchNameTxt.setText(lecSession.getBatch_name());

        holder.bind(lecSession,mCallback);

    }

    @Override
    public int getItemCount() {
        return sessionsList.size();
    }
}
