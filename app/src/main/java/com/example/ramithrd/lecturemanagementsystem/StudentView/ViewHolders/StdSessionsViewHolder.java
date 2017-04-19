package com.example.ramithrd.lecturemanagementsystem.StudentView.ViewHolders;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.ramithrd.lecturemanagementsystem.R;
import com.example.ramithrd.lecturemanagementsystem.StudentView.Interfaces.StudentService;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by RamithRD on 4/18/2017.
 */

public class StdSessionsViewHolder extends RecyclerView.ViewHolder {

    public TextView moduleNameTxt;
    public TextView moduleIdTxt;
    public TextView startTimeTxt;
    public TextView endTimeTxt;
    public TextView venueTxt;

    private Context context;

    public StdSessionsViewHolder(View itemView) {
        super(itemView);

        context = itemView.getContext();

        moduleNameTxt = (TextView) itemView.findViewById(R.id.std_module_name_txt);
        moduleIdTxt = (TextView) itemView.findViewById(R.id.std_module_id_txt);
        startTimeTxt = (TextView) itemView.findViewById(R.id.std_start_time_txt);
        endTimeTxt = (TextView) itemView.findViewById(R.id.std_end_time_txt);
        venueTxt = (TextView) itemView.findViewById(R.id.std_venue_txt);

    }

}
