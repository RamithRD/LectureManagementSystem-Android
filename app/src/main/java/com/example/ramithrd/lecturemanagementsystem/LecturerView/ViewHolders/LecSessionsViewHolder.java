package com.example.ramithrd.lecturemanagementsystem.LecturerView.ViewHolders;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.ContentFrameLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ramithrd.lecturemanagementsystem.GlobalClass;
import com.example.ramithrd.lecturemanagementsystem.LecturerView.Activities.LecScheduleActivity;
import com.example.ramithrd.lecturemanagementsystem.LecturerView.Interfaces.LectureSessionService;
import com.example.ramithrd.lecturemanagementsystem.LecturerView.Interfaces.OnSessionStateListener;
import com.example.ramithrd.lecturemanagementsystem.Model.LectureSession;
import com.example.ramithrd.lecturemanagementsystem.Model.Session;
import com.example.ramithrd.lecturemanagementsystem.R;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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

    private LectureSessionService lecSessionService;
    private String lecturerID= "";

    private GlobalClass globalClass;

    private OnSessionStateListener mListener;
    private Context context;

    private ProgressDialog cancelProgress;
    private ProgressDialog updateProgress;

    public LecSessionsViewHolder(View itemView) {
        super(itemView);

        final String ENDPOINT_URL  = itemView.getContext().getString(R.string.lecturer_service_url);

        context = itemView.getContext();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ENDPOINT_URL)
                .client(getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        lecSessionService = retrofit.create(LectureSessionService.class);

        moduleNameTxt = (TextView) itemView.findViewById(R.id.lec_module_name_txt);
        moduleIdTxt = (TextView) itemView.findViewById(R.id.lec_module_id_txt);
        startTimeTxt = (TextView) itemView.findViewById(R.id.lec_start_time_txt);
        endTimeTxt = (TextView) itemView.findViewById(R.id.lec_end_time_txt);
        venueTxt = (TextView) itemView.findViewById(R.id.lec_venue_txt);
        batchNameTxt = (TextView) itemView.findViewById(R.id.lec_batch_txt);

        updateLecSession = (Button) itemView.findViewById(R.id.update_lec_btn);
        cancelLecSession = (Button) itemView.findViewById(R.id.cancel_lec_btn);

        cancelProgress = new ProgressDialog(context);

    }

    public void bind(Session sessionData, OnSessionStateListener listener) {

        final Session lecSession = sessionData;
        mListener = listener;

        cancelLecSession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog alertDialog = new AlertDialog.Builder(context).create();
                alertDialog.setTitle("Cancel Lecture For "+lecSession.getBatch_name());
                alertDialog.setMessage("Please Confirm to Cancel Lecture Session");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "YES",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                cancelLecture(lecSession);
                                dialog.dismiss();
                            }
                        });
                alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "NO",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();

            }
        });

        updateLecSession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateLecture(lecSession);
            }
        });

    }

    private static OkHttpClient getOkHttpClient(){
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.NONE);
        OkHttpClient okClient = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();
        return okClient;
    }

    private void cancelLecture(Session lecToBeCancelled){

        cancelProgress.setCancelable(false);
        cancelProgress.setMessage("Cancelling Lecture ...");
        cancelProgress.show();

        int position = getAdapterPosition();
        if (position >= 0) {

            mListener.lecSessionCancelled(position);
            Call<Boolean> cancelSession = lecSessionService.cancelSession(lecToBeCancelled.getSession_Id());
            cancelSession.enqueue(new Callback<Boolean>() {
                @Override
                public void onResponse(Call<Boolean> call, Response<Boolean> response) {

                    cancelProgress.hide();
                    Toast.makeText(context,"Lecture Cancelled!",Toast.LENGTH_LONG).show();

                }

                @Override
                public void onFailure(Call<Boolean> call, Throwable t) {
                    cancelProgress.hide();
                    Toast.makeText(context,"Cancellation Failed! "+t.getMessage(),Toast.LENGTH_LONG).show();
                }
            });

        }

    }

    private void updateLecture(Session lecSession){

        Intent updateIntent = new Intent(context.getApplicationContext(), LecScheduleActivity.class);
        //calling startActivity() from outside of an Activity context requires the FLAG_ACTIVITY_NEW_TASK flag
        updateIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        updateIntent.putExtra("sessionToUpdate",lecSession);
        context.getApplicationContext().startActivity(updateIntent);

    }
}
