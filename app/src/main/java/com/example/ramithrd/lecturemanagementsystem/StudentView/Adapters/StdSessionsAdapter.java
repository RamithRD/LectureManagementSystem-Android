package com.example.ramithrd.lecturemanagementsystem.StudentView.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ramithrd.lecturemanagementsystem.LecturerView.ViewHolders.LecSessionsViewHolder;
import com.example.ramithrd.lecturemanagementsystem.Model.Session;
import com.example.ramithrd.lecturemanagementsystem.R;
import com.example.ramithrd.lecturemanagementsystem.StudentView.ViewHolders.StdSessionsViewHolder;

import java.util.ArrayList;

/**
 * Created by RamithRD on 4/19/2017.
 */

public class StdSessionsAdapter extends RecyclerView.Adapter<StdSessionsViewHolder> {

    public ArrayList<Session> sessionsList = new ArrayList<>();

    private Session lecSession;

    public StdSessionsAdapter(ArrayList<Session> sessionsList) {
        this.sessionsList = sessionsList;
    }

    @Override
    public StdSessionsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.student_sessions_list_item, parent, false);

        return new StdSessionsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(StdSessionsViewHolder holder, int position) {

        lecSession = sessionsList.get(position);

        holder.moduleIdTxt.setText(lecSession.getModule_Id());
        holder.moduleNameTxt.setText(lecSession.getModule_name());
        holder.lecturerTxt.setText(lecSession.getLecturer_name());
        holder.startTimeTxt.setText(lecSession.getLec_start_time());
        holder.endTimeTxt.setText(lecSession.getLec_end_time());

        String lecVenue = lecSession.getLecture_hall()+" ("+lecSession.getFaculty()+")";
        holder.venueTxt.setText(lecVenue);
    }

    @Override
    public int getItemCount() {
        return sessionsList.size();
    }

}
