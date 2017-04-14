package com.example.ramithrd.lecturemanagementsystem.LecturerView.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.example.ramithrd.lecturemanagementsystem.R;

import java.util.ArrayList;
import java.util.List;

public class LecturerTodayFragment extends Fragment {

    private List<String> lecfesfsturst;

    public LecturerTodayFragment() {
        // Required empty public constructor
    }

    public static LecturerTodayFragment newInstance() {
        LecturerTodayFragment fragment = new LecturerTodayFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lecturer_today, container, false);



        return view;

    }


}
