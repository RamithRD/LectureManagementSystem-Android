package com.example.ramithrd.lecturemanagementsystem.LecturerView.ViewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.ramithrd.lecturemanagementsystem.R;

/**
 * Created by RamithRD on 4/16/2017.
 */

public class LecSessionsViewHolder extends RecyclerView.ViewHolder {

    public TextView moduleNameTxt;
    public TextView moduleIdTxt;
    public TextView startTimeTxt;
    public TextView endTimeTxt;
    public TextView venueTxt;
    public TextView batchNameTxt;

    public Button updateLecSession;
    public Button cancelLecSession;

    public LecSessionsViewHolder(View itemView) {
        super(itemView);

        moduleNameTxt = (TextView) itemView.findViewById(R.id.lec_module_name_txt);
        moduleIdTxt = (TextView) itemView.findViewById(R.id.lec_module_id_txt);
        startTimeTxt = (TextView) itemView.findViewById(R.id.lec_start_time_txt);
        endTimeTxt = (TextView) itemView.findViewById(R.id.lec_end_time_txt);
        venueTxt = (TextView) itemView.findViewById(R.id.lec_venue_txt);
        batchNameTxt = (TextView) itemView.findViewById(R.id.lec_batch_txt);

        updateLecSession = (Button) itemView.findViewById(R.id.update_lec_btn);
        cancelLecSession = (Button) itemView.findViewById(R.id.cancel_lec_btn);

    }
}
