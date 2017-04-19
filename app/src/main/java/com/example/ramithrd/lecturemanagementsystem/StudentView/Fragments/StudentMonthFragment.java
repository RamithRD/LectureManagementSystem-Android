package com.example.ramithrd.lecturemanagementsystem.StudentView.Fragments;


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
import com.example.ramithrd.lecturemanagementsystem.Helpers.EventDecorator;
import com.example.ramithrd.lecturemanagementsystem.LecturerView.Activities.SessionsActivity;
import com.example.ramithrd.lecturemanagementsystem.Model.Session;
import com.example.ramithrd.lecturemanagementsystem.Model.StudentSession;
import com.example.ramithrd.lecturemanagementsystem.R;
import com.example.ramithrd.lecturemanagementsystem.StudentView.Activities.StudentsLecActivity;
import com.example.ramithrd.lecturemanagementsystem.StudentView.Interfaces.StudentService;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

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


public class StudentMonthFragment extends Fragment implements OnDateSelectedListener {

    private StudentService studentService;
    private String studentId;

    private GlobalClass globalClass;

    private MaterialCalendarView calendarView;

    private List<Session> lectureSessionsList;
    private List<CalendarDay> calendarDays;

    private ProgressDialog mProgress;

    public StudentMonthFragment() {
        // Required empty public constructor
    }

    public static StudentMonthFragment newInstance() {
        StudentMonthFragment fragment = new StudentMonthFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final String ENDPOINT_URL  = getContext().getString(R.string.student_service_url);

        globalClass = ((GlobalClass) getContext().getApplicationContext());
        studentId  = globalClass.getUserInfo().getUserId();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ENDPOINT_URL)
                .client(getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        studentService = retrofit.create(StudentService.class);

        mProgress = new ProgressDialog(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_student_month, container, false);

        calendarView = (MaterialCalendarView) view.findViewById(R.id.stdCalendarView);

        lectureSessionsList = new ArrayList<>();
        //dates that needs an event decorator is added to a list
        calendarDays = new ArrayList<>();

        getAllLectureSessions();

        calendarView.setOnDateChangedListener(this);

        return view;
    }


    private static OkHttpClient getOkHttpClient(){
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.NONE);
        OkHttpClient okClient = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();
        return okClient;
    }

    @Override
    public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {


        if(getLecturesForDate(getSelectedDateString()).size() > 0){

            Intent intent = new Intent(getActivity(), StudentsLecActivity.class);
            Bundle b = new Bundle();
            b.putParcelableArrayList("stdSessionsList",getLecturesForDate(getSelectedDateString()));

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
        return android.text.format.DateFormat.format("M/dd/yyyy", date.getDate()).toString();
    }

    private void getAllLectureSessions() {

        calendarView.removeDecorators();
        calendarDays.clear();
        lectureSessionsList.clear();

        mProgress.setMessage("Loading Lecture Sessions ...");
        mProgress.show();

        Call<List<StudentSession>> getAllSessions = studentService.getLecturesForStudent(studentId);
        getAllSessions.enqueue(new Callback<List<StudentSession>>() {
            @Override
            public void onResponse(Call<List<StudentSession>> call, Response<List<StudentSession>> response) {
                List<StudentSession> sessionsList = response.body();
                System.out.println("LIST SIZE : "+sessionsList.size());

                for(StudentSession lecture : sessionsList){


                    String lecDateStr = lecture.getSessionDate();
                    lecDateStr = lecDateStr.substring(0, lecDateStr.indexOf(" "));


                    SimpleDateFormat dateFormat = new SimpleDateFormat("M/dd/yyyy");
                    try {
                        Date date = dateFormat.parse(lecDateStr);
                        CalendarDay lecDate = CalendarDay.from(date);

                        calendarDays.add(lecDate);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    Session lecSession = new Session();
                    lecSession.setModule_Id(lecture.getModuleId());
                    lecSession.setModule_name(lecture.getName());
                    lecSession.setLecture_hall(lecture.getHallName());
                    lecSession.setFaculty(lecture.getFaculty());
                    lecSession.setLec_date(lecDateStr);
                    lecSession.setLec_start_time(lecture.getSessionST());
                    lecSession.setLec_end_time(lecture.getSessionET());

                    lectureSessionsList.add(lecSession);
                    calendarView.addDecorator(new EventDecorator(Color.parseColor("#009688"),calendarDays));

                }


                mProgress.hide();
            }

            @Override
            public void onFailure(Call<List<StudentSession>> call, Throwable t) {

            }
        });


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
}
