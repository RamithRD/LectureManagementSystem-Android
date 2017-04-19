package com.example.ramithrd.lecturemanagementsystem.StudentView.Fragments;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ramithrd.lecturemanagementsystem.GlobalClass;
import com.example.ramithrd.lecturemanagementsystem.Helpers.EventDecorator;
import com.example.ramithrd.lecturemanagementsystem.LecturerView.Adapters.LecSessionsAdapter;
import com.example.ramithrd.lecturemanagementsystem.Model.LectureSession;
import com.example.ramithrd.lecturemanagementsystem.Model.Session;
import com.example.ramithrd.lecturemanagementsystem.Model.StudentSession;
import com.example.ramithrd.lecturemanagementsystem.R;
import com.example.ramithrd.lecturemanagementsystem.StudentView.Adapters.StdSessionsAdapter;
import com.example.ramithrd.lecturemanagementsystem.StudentView.Interfaces.StudentService;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class StudentTodayFragment extends Fragment {

    private StudentService studentService;
    private String studentId;

    private GlobalClass globalClass;

    private ArrayList<Session> studentSessionsList;
    private RecyclerView todaysSessionsRecycler;
    private StdSessionsAdapter sessionsAdapter;


    public StudentTodayFragment() {
        // Required empty public constructor
    }

    public static StudentTodayFragment newInstance() {
        StudentTodayFragment fragment = new StudentTodayFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final String ENDPOINT_URL  = getContext().getString(R.string.student_service_url);

        studentSessionsList = new ArrayList<>();

        globalClass = ((GlobalClass) getContext().getApplicationContext());
        studentId  = globalClass.getStudentID();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ENDPOINT_URL)
                .client(getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        studentService = retrofit.create(StudentService.class);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_student_today, container, false);

        todaysSessionsRecycler = (RecyclerView) view.findViewById(R.id.std_current_sessions_recycler);
        todaysSessionsRecycler.setNestedScrollingEnabled(false);

        Call<List<StudentSession>> getTodaySessions = studentService.getLecturesForStudent(studentId);
        getTodaySessions.enqueue(new Callback<List<StudentSession>>() {
            @Override
            public void onResponse(Call<List<StudentSession>> call, Response<List<StudentSession>> response) {

                List<StudentSession> sessionsTdy = response.body();
                String currentDate = new SimpleDateFormat("M/dd/yyyy").format(Calendar.getInstance().getTime());
                System.out.println("CURRENT DATE "+currentDate);

                for(StudentSession lecture : sessionsTdy){

                    //TODO delete 0 and use millis
                    String lecDateStr = lecture.getSessionDate();
                    lecDateStr = lecDateStr.substring(0, lecDateStr.indexOf(" "));

                    System.out.println("DATE "+lecture.getSessionDate());

                    if(currentDate.equalsIgnoreCase(lecDateStr)){

                        Session lecSession = new Session();
                        lecSession.setModule_Id(lecture.getModuleId());
                        lecSession.setModule_name(lecture.getName());
                        lecSession.setLecture_hall(lecture.getHallName());
                        lecSession.setFaculty(lecture.getFaculty());
                        lecSession.setLec_date(lecDateStr);
                        lecSession.setLec_start_time(lecture.getSessionST());
                        lecSession.setLec_end_time(lecture.getSessionET());

                        studentSessionsList.add(lecSession);
                    }
                }

                sessionsAdapter = new StdSessionsAdapter(studentSessionsList);

                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
                todaysSessionsRecycler.setLayoutManager(mLayoutManager);

                todaysSessionsRecycler.setAdapter(sessionsAdapter);
            }

            @Override
            public void onFailure(Call<List<StudentSession>> call, Throwable t) {

            }
        });

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


}
