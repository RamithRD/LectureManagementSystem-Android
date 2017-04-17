package com.example.ramithrd.lecturemanagementsystem.LecturerView.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ramithrd.lecturemanagementsystem.LecturerView.Interfaces.OnSessionStateListener;
import com.example.ramithrd.lecturemanagementsystem.LecturerView.ViewHolders.LecSessionsViewHolder;
import com.example.ramithrd.lecturemanagementsystem.Model.Session;
import com.example.ramithrd.lecturemanagementsystem.R;

import java.util.ArrayList;

/**
 * Created by RamithRD on 4/16/2017.
 */

public class LecSessionsAdapter extends RecyclerView.Adapter<LecSessionsViewHolder> {

    public ArrayList<Session> sessionsList = new ArrayList<>();

    private OnSessionStateListener mCallback;

    private Session lecSession;

    public LecSessionsAdapter(ArrayList<Session> sessionsList) {
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
    public LecSessionsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.lecturer_session_list_item, parent, false);

        return new LecSessionsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(LecSessionsViewHolder holder, int position) {

        lecSession = sessionsList.get(position);

        holder.moduleIdTxt.setText(lecSession.getModule_Id());
        holder.moduleNameTxt.setText(lecSession.getModule_name());
        holder.startTimeTxt.setText(lecSession.getLec_start_time());
        holder.endTimeTxt.setText(lecSession.getLec_end_time());
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
