package com.example.ramithrd.lecturemanagementsystem.LecturerView.Fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ramithrd.lecturemanagementsystem.GlobalClass;
import com.example.ramithrd.lecturemanagementsystem.LecturerView.Activities.SessionsActivity;
import com.example.ramithrd.lecturemanagementsystem.LecturerView.Interfaces.LectureSessionService;
import com.example.ramithrd.lecturemanagementsystem.Helpers.EventDecorator;
import com.example.ramithrd.lecturemanagementsystem.Model.LectureSession;
import com.example.ramithrd.lecturemanagementsystem.Model.Session;
import com.example.ramithrd.lecturemanagementsystem.R;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LecturerMonthFragment extends Fragment implements OnDateSelectedListener {

    private LectureSessionService lecSessionService;
    private String lecturerID= "";

    private GlobalClass globalClass;

    private MaterialCalendarView calendarView;
    private TextView selectedDateText;
    private static final DateFormat FORMATTER = SimpleDateFormat.getDateInstance();

    private List<Session> lectureSessionsList;
    private List<CalendarDay> calendarDays;

    private ProgressDialog mProgress;

    public LecturerMonthFragment() {
        // Required empty public constructor
    }

    public static LecturerMonthFragment newInstance() {
        LecturerMonthFragment fragment = new LecturerMonthFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final String ENDPOINT_URL  = getContext().getString(R.string.lecturer_service_url);

        globalClass = ((GlobalClass) getContext().getApplicationContext());
        lecturerID  = globalClass.getLecturerID();

        mProgress = new ProgressDialog(getContext());

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ENDPOINT_URL)
                .client(getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        lecSessionService = retrofit.create(LectureSessionService.class);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lecturer_month, container, false);


        calendarView = (MaterialCalendarView) view.findViewById(R.id.calendarView);
        selectedDateText = (TextView) view.findViewById(R.id.selectedDateTxt);

        lectureSessionsList = new ArrayList<>();
        //dates that needs an event decorator is added to a list
        calendarDays = new ArrayList<>();

        getAllLectureSessions();

        calendarView.setOnDateChangedListener(this);

        return view;
    }


    @Override
    public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {

        selectedDateText.setText(getSelectedDateString());
        System.out.println("Lec Size "+getLecturesForDate(getSelectedDateString()).size());

        if(getLecturesForDate(getSelectedDateString()).size() > 0){

            Intent intent = new Intent(getActivity(), SessionsActivity.class);
            Bundle b = new Bundle();
            b.putParcelableArrayList("sessionsList",getLecturesForDate(getSelectedDateString()));

            intent.putExtras(b);
            startActivity(intent);

        }else{

            //show info dialog -  no lecs on this day

        }

    }

    private String getSelectedDateString() {
        CalendarDay date = calendarView.getSelectedDate();
        if (date == null) {
            return "No Selection";
        }
        return android.text.format.DateFormat.format("dd-MM-yyyy", date.getDate()).toString();
    }

    private void getAllLectureSessions(){

        mProgress.setMessage("Loading Lecture Sessions ...");
        mProgress.show();
        Call<List<LectureSession>> getAllSessions = lecSessionService.getAllSessions(lecturerID);
        getAllSessions.enqueue(new Callback<List<LectureSession>>() {
            @Override
            public void onResponse(Call<List<LectureSession>> call, Response<List<LectureSession>> response) {
                List<LectureSession> sessionsList = response.body();
                System.out.println("LIST SIZE : "+sessionsList.size());

                for(LectureSession lecture : sessionsList){

                    String tempDate = lecture.getSessionDate().replaceAll("\\D+", "");
                    String lecDateStr = getDate(Long.parseLong(tempDate)/10000);
                    System.out.println("Lec Dates "+lecDateStr);

                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                    try {
                        Date date = dateFormat.parse(lecDateStr);
                        CalendarDay lecDate = CalendarDay.from(date);

                        calendarDays.add(lecDate);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    Session lecSession = new Session();
                    lecSession.setSession_Id(lecture.getSessionId());
                    lecSession.setLecture_Id(lecture.getLecturerId());
                    lecSession.setBatch_name(lecture.getBatchId());
                    lecSession.setModule_Id(lecture.getModuleId());
                    lecSession.setModule_name(lecture.getModuleName());
                    lecSession.setUniversity_name(lecture.getUniversityName());
                    lecSession.setProgramme_name(lecture.getProgrammeName());
                    lecSession.setLecture_hall(lecture.getLectureHallName());
                    lecSession.setFaculty(lecture.getFaculty());
                    lecSession.setLec_date(lecDateStr);
                    lecSession.setLec_start_time(lecture.getSessionStartTimeText());
                    lecSession.setLec_end_time(lecture.getSessionEndTimeText());

                    lectureSessionsList.add(lecSession);
                    calendarView.addDecorator(new EventDecorator(Color.RED,calendarDays));

                }

                mProgress.hide();
            }

            @Override
            public void onFailure(Call<List<LectureSession>> call, Throwable t) {

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

    private String getDate(long time) {
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(time);
        String date = android.text.format.DateFormat.format("dd-MM-yyyy", cal).toString();
        return date;
    }

    private ArrayList<Session> getLecturesForDate(String lecDate){

        ArrayList<Session> lecs = new ArrayList<>();

        for(Session session  : lectureSessionsList){

            if(session.getLec_date().equalsIgnoreCase(lecDate)){
                lecs.add(session);
            }

        }

        return lecs;
    }

    @Override
    public void onResume() {
        super.onResume();

        calendarView.removeDecorators();
        calendarDays.clear();
        lectureSessionsList.clear();

        getAllLectureSessions();
    }
}
