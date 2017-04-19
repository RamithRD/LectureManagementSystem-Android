package com.example.ramithrd.lecturemanagementsystem;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;

import com.example.ramithrd.lecturemanagementsystem.LecturerView.Activities.LecturerMainActivity;
import com.example.ramithrd.lecturemanagementsystem.LecturerView.Interfaces.LectureSessionService;
import com.example.ramithrd.lecturemanagementsystem.Model.User;
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

    private TextInputLayout emailLayout;
    private TextInputLayout passwordLayout;

    private EditText mEmail;
    private EditText mPassword;

    private String mEmailStr = "";
    private String mPasswordStr = "";

    private ProgressDialog mLoginProg;

    private LinearLayout loginContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final String ENDPOINT_URL  = getString(R.string.lecturer_service_url);

        loginContainer = (LinearLayout) findViewById(R.id.loginActivityContainer);

        emailLayout = (TextInputLayout) findViewById(R.id.input_layout_email);
        passwordLayout = (TextInputLayout) findViewById(R.id.input_layout_password);

        mEmail = (EditText) findViewById(R.id.login_input_email);
        mPassword = (EditText) findViewById(R.id.login_input_password);

        mLoginProg = new ProgressDialog(this);

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

                mEmailStr = mEmail.getText().toString();
                mPasswordStr = mPassword.getText().toString();

                    if (!validateFields()) {

                        mLoginProg.setMessage("Signing In ...");
                        mLoginProg.setCancelable(false);
                        mLoginProg.show();

    //                //TODO use the hashed password for final
    //                String passHashed = new String(Hex.encodeHex(DigestUtils.sha1(mPasswordStr)));

                    Call<String> login = lecSessionService.login(mEmailStr, mPasswordStr);
                    login.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            System.out.println("User Details " + response.body());

                            String responseBody;
                            responseBody = response.body();

                            if (!responseBody.isEmpty()) {

                                String[] loginInfo = responseBody.split("\\-");

                                User user = new User();
                                user.setUser_role(loginInfo[0]);
                                user.setUserId(loginInfo[1]);
                                user.setFirst_name(loginInfo[2]);
                                user.setLast_name(loginInfo[3]);

                                mLoginProg.hide();

                                switch (user.getUser_role()) {


                                    case "student": {
                                        Intent studentIntent = new Intent(LoginActivity.this, StudentMainActivity.class);
                                        studentIntent.putExtra("userDetails", user);
                                        studentIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        System.out.println("USER DETAILS " + user.getUserId() + " " + user.getUser_role() + " " + user.getFirst_name());
                                        startActivity(studentIntent);
                                        break;
                                    }

                                    case "lecturer": {
                                        Intent lectureIntent = new Intent(LoginActivity.this, LecturerMainActivity.class);
                                        lectureIntent.putExtra("userDetails", user);
                                        lectureIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        startActivity(lectureIntent);
                                        break;
                                    }

                                }

                            }

                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            System.out.println("FAILED");
                            //handles login error
                            mLoginProg.hide();
                            mEmail.setText("");
                            mEmail.requestFocus();
                            mPassword.setText("");
                            Snackbar snackbar = Snackbar
                                    .make(loginContainer, "Incorrect Login Credentials, Please Try Again!", Snackbar.LENGTH_LONG);

                            snackbar.show();
                        }
                    });

                }

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

    private boolean validateFields(){

        boolean validationErrorOccurred = false;

        if(TextUtils.isEmpty(mEmailStr)){

            emailLayout.setErrorEnabled(true);
            emailLayout.setError("Please Enter an Email Address!");
            validationErrorOccurred = true;

        }else{
            emailLayout.setErrorEnabled(false);
            emailLayout.setError(null);
            validationErrorOccurred = false;
        }

        if(TextUtils.isEmpty(mPasswordStr)){

            passwordLayout.setErrorEnabled(true);
            passwordLayout.setError("Please Enter a Password!");
            validationErrorOccurred = true;

        }else{
            passwordLayout.setErrorEnabled(false);
            passwordLayout.setError(null);
            validationErrorOccurred = false;
        }

        return validationErrorOccurred;

    }
}
