package com.example.ramithrd.lecturemanagementsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import com.example.ramithrd.lecturemanagementsystem.LecturerView.Activities.LecturerMainActivity;
import com.example.ramithrd.lecturemanagementsystem.LecturerView.Interfaces.LectureSessionService;
import com.example.ramithrd.lecturemanagementsystem.StudentView.Activities.StudentMainActivity;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    private Button sign_in_btn;
    private LectureSessionService lecSessionService;

    private EditText mEmail;
    private EditText mPassword;

    private String mEmailStr = "";
    private String mPasswordStr = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final String ENDPOINT_URL  = getString(R.string.lecturer_service_url);

        mEmail = (EditText) findViewById(R.id.login_input_email);
        mPassword = (EditText) findViewById(R.id.login_input_password);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ENDPOINT_URL)
                .client(getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        String pass = new String(Hex.encodeHex(DigestUtils.sha1("123")));
        System.out.println("SHA VALUE :"+pass);

        lecSessionService = retrofit.create(LectureSessionService.class);

        sign_in_btn = (Button) findViewById(R.id.btn_signin);
        sign_in_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent lectureIntent = new Intent(LoginActivity.this, LecturerMainActivity.class);
                startActivity(lectureIntent);

//                mEmailStr = mEmail.getText().toString();
//                mPasswordStr = mPassword.getText().toString();
//
//                String passHashed = new String(Hex.encodeHex(DigestUtils.sha1(mPasswordStr)));
//
//                Call<String> login = lecSessionService.login(mEmailStr,passHashed);
//                login.enqueue(new Callback<String>() {
//                    @Override
//                    public void onResponse(Call<String> call, Response<String> response) {
//                        System.out.println("User Details "+response.body());
//
//                        String responseBody = "";
//                        responseBody = response.body();
//
//                        if(responseBody.equals("")){
//                            //handle login error
//                        }else{
//
//                            String[] loginInfo = responseBody.split("\\-");
//
//                            switch(loginInfo[0]){
//
//                                case "student":{
//                                    Intent studentIntent = new Intent(LoginActivity.this, StudentMainActivity.class);
//                                    startActivity(studentIntent);
//                                    break;
//                                }
//
//                                case "lecturer":{
//                                    Intent lectureIntent = new Intent(LoginActivity.this, LecturerMainActivity.class);
//                                    startActivity(lectureIntent);
//                                    break;
//                                }
//
//                            }
//
//                        }
//
//                    }
//
//                    @Override
//                    public void onFailure(Call<String> call, Throwable t) {
//
//                    }
//                });

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
}
