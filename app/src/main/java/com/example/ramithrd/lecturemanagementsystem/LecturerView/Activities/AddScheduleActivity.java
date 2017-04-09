package com.example.ramithrd.lecturemanagementsystem.LecturerView.Activities;

import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;


import com.borax12.materialdaterangepicker.time.RadialPickerLayout;
import com.borax12.materialdaterangepicker.time.TimePickerDialog;
import com.example.ramithrd.lecturemanagementsystem.R;


import java.util.Calendar;

public class AddScheduleActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {

    private ImageButton pickLecTime;
    private EditText selectedStartTimeTxt;
    private EditText selectedEndTimeTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_schedule);

        selectedStartTimeTxt = (EditText) findViewById(R.id.schedule_picked_layout);
        selectedEndTimeTxt = (EditText) findViewById(R.id.schedule_end_time);

        pickLecTime = (ImageButton) findViewById(R.id.lecTimeBtn);
        pickLecTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar now = Calendar.getInstance();
                TimePickerDialog tpd = TimePickerDialog.newInstance(
                        AddScheduleActivity.this,
                        now.get(Calendar.HOUR_OF_DAY),
                        now.get(Calendar.MINUTE),
                        false
                );
                tpd.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialogInterface) {
                        Log.d("TimePicker", "Dialog was cancelled");
                    }
                });
                tpd.show(getFragmentManager(), "Timepickerdialog");

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
}
