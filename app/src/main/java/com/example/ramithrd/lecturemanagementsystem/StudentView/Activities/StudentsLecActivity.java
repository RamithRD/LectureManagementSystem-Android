package com.example.ramithrd.lecturemanagementsystem.StudentView.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.ramithrd.lecturemanagementsystem.Model.Session;
import com.example.ramithrd.lecturemanagementsystem.R;
import com.example.ramithrd.lecturemanagementsystem.StudentView.Adapters.StdSessionsAdapter;

import java.util.ArrayList;

public class StudentsLecActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private StdSessionsAdapter sessionsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students_lec);

        ArrayList<Session> sessionsList = getIntent().getExtras().getParcelableArrayList("stdSessionsList");
        System.out.println("SIZE IN NEW "+sessionsList.size());

        getSupportActionBar().setTitle("Lectures on "+sessionsList.get(0).getLec_date());

        recyclerView = (RecyclerView) findViewById(R.id.lec_sessions_month_recycler);
        recyclerView.setNestedScrollingEnabled(false);
        sessionsAdapter = new StdSessionsAdapter(sessionsList);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);

        recyclerView.setAdapter(sessionsAdapter);
    }
}
