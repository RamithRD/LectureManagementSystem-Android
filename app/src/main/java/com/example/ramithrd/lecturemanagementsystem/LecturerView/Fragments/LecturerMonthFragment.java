package com.example.ramithrd.lecturemanagementsystem.LecturerView.Fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ramithrd.lecturemanagementsystem.Model.EventDecorator;
import com.example.ramithrd.lecturemanagementsystem.R;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class LecturerMonthFragment extends Fragment implements OnDateSelectedListener {

    private MaterialCalendarView calendarView;
    private TextView selectedDateText;
    private static final DateFormat FORMATTER = SimpleDateFormat.getDateInstance();

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

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lecturer_month, container, false);

        calendarView = (MaterialCalendarView) view.findViewById(R.id.calendarView);
        selectedDateText = (TextView) view.findViewById(R.id.selectedDateTxt);

        //dates that needs an event decorator is added to a list
        List<CalendarDay> calendarDays = new ArrayList<>();

        Calendar calendar = Calendar.getInstance();
        CalendarDay day = CalendarDay.from(calendar);
        calendarDays.add(calendarView.getCurrentDate());
        calendarDays.add(day);

        calendarView.addDecorator(new EventDecorator(Color.RED,calendarDays));

        calendarView.setOnDateChangedListener(this);

        return view;
    }


    @Override
    public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {

        selectedDateText.setText(getSelectedDatesString());

    }

    private String getSelectedDatesString() {
        CalendarDay date = calendarView.getSelectedDate();
        if (date == null) {
            return "No Selection";
        }
        return FORMATTER.format(date.getDate());
    }
}
