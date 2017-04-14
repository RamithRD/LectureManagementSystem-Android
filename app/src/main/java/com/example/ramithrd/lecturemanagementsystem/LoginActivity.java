package com.example.ramithrd.lecturemanagementsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.ramithrd.lecturemanagementsystem.LecturerView.Activities.LecturerMainActivity;
import com.example.ramithrd.lecturemanagementsystem.StudentView.Activities.StudentMainActivity;

public class LoginActivity extends AppCompatActivity {

    private Button sign_in_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sign_in_btn = (Button) findViewById(R.id.btn_signin);
        sign_in_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lecturerIntent = new Intent(LoginActivity.this, LecturerMainActivity.class);
                startActivity(lecturerIntent);
            }
        });
    }
}
