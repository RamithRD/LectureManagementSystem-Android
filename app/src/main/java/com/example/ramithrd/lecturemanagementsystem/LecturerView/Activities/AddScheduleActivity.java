package com.example.ramithrd.lecturemanagementsystem.LecturerView.Activities;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;


import com.borax12.materialdaterangepicker.time.RadialPickerLayout;
import com.borax12.materialdaterangepicker.time.TimePickerDialog;
import com.example.ramithrd.lecturemanagementsystem.GlobalClass;
import com.example.ramithrd.lecturemanagementsystem.LecturerView.Interfaces.LectureSessionService;
import com.example.ramithrd.lecturemanagementsystem.Models.Batch;
import com.example.ramithrd.lecturemanagementsystem.Models.LectureHall;
import com.example.ramithrd.lecturemanagementsystem.Models.Module;
import com.example.ramithrd.lecturemanagementsystem.Models.Programme;
import com.example.ramithrd.lecturemanagementsystem.Models.University;
import com.example.ramithrd.lecturemanagementsystem.R;
import com.jaredrummler.materialspinner.MaterialSpinner;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddScheduleActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {

    private ImageButton pickLecTime;
    private ImageButton pickLecDate;

    private EditText selectedStartTimeTxt;
    private EditText selectedEndTimeTxt;
    private EditText selectedDateTxt;

    private MaterialSpinner universitiesSpinner;
    private MaterialSpinner programmesSpinner;
    private MaterialSpinner batchesSpinner;
    private MaterialSpinner modulesSpinner;
    private MaterialSpinner lectureHallsSpinner;

    private List<University> universitiesList;
    private List<Programme> programmesList;
    private List<Batch> batchesList;
    private List<Module> modulesList;
    private List<LectureHall> lectureHallsList;

    private int mYear, mMonth, mDay;

    private GlobalClass globalClass;
    private String lecturerID = "";

    private ProgressDialog mLoadDetailsDialog;

    public static final String ENDPOINT_URL  = "http://54.214.72.150/Service.svc/";
    private LectureSessionService lecSessionService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_schedule);

        mLoadDetailsDialog = new ProgressDialog(getApplicationContext());

        globalClass = ((GlobalClass) getApplicationContext());
        lecturerID =  globalClass.getLecturerID();

        selectedStartTimeTxt = (EditText) findViewById(R.id.schedule_picked_layout);
        selectedEndTimeTxt = (EditText) findViewById(R.id.schedule_end_time);
        selectedDateTxt = (EditText) findViewById(R.id.schedule_picked_date);

        universitiesSpinner = (MaterialSpinner) findViewById(R.id.unis_spinner);
        lectureHallsSpinner = (MaterialSpinner) findViewById(R.id.hall_spinner);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ENDPOINT_URL)
                .client(getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        lecSessionService = retrofit.create(LectureSessionService.class);

        Call<List<University>> uniRequest =  lecSessionService.getUniversities(globalClass.getLecturerID());
        uniRequest.enqueue(new Callback<List<University>>() {
            @Override
            public void onResponse(Call<List<University>> call, Response<List<University>> response) {

                List<University> hallList = response.body();

                List<String> hallNamesList = new ArrayList<String>();

                for(University uni : hallList){

                    hallNamesList.add(uni.getName());

                }

                universitiesSpinner.setItems(hallNamesList);

            }

            @Override
            public void onFailure(Call<List<University>> call, Throwable t) {

            }
        });

        Call<List<LectureHall>> lecHallRequest = lecSessionService.getLectureHalls();
        lecHallRequest.enqueue(new Callback<List<LectureHall>>() {
            @Override
            public void onResponse(Call<List<LectureHall>> call, Response<List<LectureHall>> response) {
                System.out.println("SUCCESS");

                List<LectureHall> hallList = response.body();

                List<String> hallNamesList = new ArrayList<String>();

                for(LectureHall lec : hallList){

                    hallNamesList.add(lec.getLectureHallName());

                }

                lectureHallsSpinner.setItems(hallNamesList);

            }

            @Override
            public void onFailure(Call<List<LectureHall>> call, Throwable t) {
                System.out.println("FAILURE: "+t.getMessage());
            }
        });

        pickLecTime = (ImageButton) findViewById(R.id.lecTimeBtn);
        pickLecTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar now = Calendar.getInstance();
                TimePickerDialog timePicker = TimePickerDialog.newInstance(
                        AddScheduleActivity.this,
                        now.get(Calendar.HOUR_OF_DAY),
                        now.get(Calendar.MINUTE),
                        false
                );
                timePicker.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialogInterface) {
                        Log.d("TimePicker", "Dialog was cancelled");
                    }
                });

                timePicker.show(getFragmentManager(), "BTimepickerdialog");

            }
        });

        pickLecDate = (ImageButton) findViewById(R.id.lecDateBtn);
        pickLecDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar calendar = Calendar.getInstance();
                mYear = calendar.get(Calendar.YEAR);
                mMonth = calendar.get(Calendar.MONTH);
                mDay = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePicker = new DatePickerDialog(AddScheduleActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                        selectedDateTxt.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);

                    }
                }, mYear, mMonth, mDay);

                //sets today's date as minimum date and all the past dates are disabled
                datePicker.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                datePicker.setCancelable(false);
                datePicker.show();

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();

    }


    @Override
    public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute, int hourOfDayEnd, int minuteEnd) {

        String hourString = hourOfDay < 10 ? "0"+hourOfDay : ""+hourOfDay;
        String minuteString = minute < 10 ? "0"+minute : ""+minute;
        String hourStringEnd = hourOfDayEnd < 10 ? "0"+hourOfDayEnd : ""+hourOfDayEnd;
        String minuteStringEnd = minuteEnd < 10 ? "0"+minuteEnd : ""+minuteEnd;

        String startTime = hourString+"."+minuteString;
        String endTime = hourStringEnd+"."+minuteStringEnd;

        selectedStartTimeTxt.setText(startTime);
        selectedEndTimeTxt.setText(endTime);
    }

    private static OkHttpClient getOkHttpClient(){
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.NONE);
        OkHttpClient okClient = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();
        return okClient;
    }
}
