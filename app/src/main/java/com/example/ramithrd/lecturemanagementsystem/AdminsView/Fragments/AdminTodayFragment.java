package com.example.ramithrd.lecturemanagementsystem.AdminsView.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ramithrd.lecturemanagementsystem.AdminsView.Adapters.AdminAdapter;
import com.example.ramithrd.lecturemanagementsystem.AdminsView.Interfaces.AdminService;
import com.example.ramithrd.lecturemanagementsystem.GlobalClass;
import com.example.ramithrd.lecturemanagementsystem.LecturerView.Adapters.LecSessionsAdapter;
import com.example.ramithrd.lecturemanagementsystem.LecturerView.Interfaces.LectureSessionService;
import com.example.ramithrd.lecturemanagementsystem.Model.LectureSession;
import com.example.ramithrd.lecturemanagementsystem.Model.Session;
import com.example.ramithrd.lecturemanagementsystem.R;

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


public class AdminTodayFragment extends Fragment {

    private AdminService adminService;

    private String adminID= "";

    private GlobalClass globalClass;

    private RecyclerView adminTodayRecycler;
    private AdminAdapter sessionsAdapter;
    private ArrayList<Session> currentSessionsList;


    public AdminTodayFragment() {
        // Required empty public constructor
    }

    public static AdminTodayFragment newInstance() {
        AdminTodayFragment fragment = new AdminTodayFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final String ENDPOINT_URL  = getContext().getString(R.string.lecturer_service_url);

        globalClass = ((GlobalClass) getContext().getApplicationContext());
        adminID  = globalClass.getUserInfo().getUserId();

        currentSessionsList = new ArrayList<>();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ENDPOINT_URL)
                .client(getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        adminService = retrofit.create(AdminService.class);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_admin_today, container, false);

        adminTodayRecycler = (RecyclerView) view.findViewById(R.id.current_sessions_recycler);
        adminTodayRecycler.setNestedScrollingEnabled(false);

        Call<List<LectureSession>> getAllSessions = adminService.getAllSessions(adminID);
        getAllSessions.enqueue(new Callback<List<LectureSession>>() {
            @Override
            public void onResponse(Call<List<LectureSession>> call, Response<List<LectureSession>> response) {
                List<LectureSession> sessionsList = response.body();

                String currentDate = new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime());

                for(LectureSession lecture : sessionsList){

                    String tempDate = lecture.getSessionDate().replaceAll("\\D+", "");
                    String lecDateStr = getDate(Long.parseLong(tempDate)/10000);

                    if(currentDate.equalsIgnoreCase(lecDateStr)){

                        Session lecSession = new Session();
                        lecSession.setSession_Id(lecture.getSessionId());
                        lecSession.setLecturer_Id(lecture.getLecturerId());
                        lecSession.setLecturer_fname(lecture.getLecturer_First_Name());
                        lecSession.setLecturer_fname(lecture.getLecturer_Last_Name());
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

                        currentSessionsList.add(lecSession);
                    }

                }

                sessionsAdapter = new AdminAdapter(currentSessionsList);

                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
                adminTodayRecycler.setLayoutManager(mLayoutManager);

                adminTodayRecycler.setAdapter(sessionsAdapter);

            }

            @Override
            public void onFailure(Call<List<LectureSession>> call, Throwable t) {

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

    private String getDate(long time) {
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(time);
        String date = android.text.format.DateFormat.format("dd-MM-yyyy", cal).toString();
        return date;
    }

}
