package com.example.ramithrd.lecturemanagementsystem.StudentView.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;
import android.widget.Toast;

import com.example.ramithrd.lecturemanagementsystem.GlobalClass;
import com.example.ramithrd.lecturemanagementsystem.LoginActivity;
import com.example.ramithrd.lecturemanagementsystem.Model.LecAttendance;
import com.example.ramithrd.lecturemanagementsystem.Model.User;
import com.example.ramithrd.lecturemanagementsystem.R;
import com.example.ramithrd.lecturemanagementsystem.StudentView.Fragments.StudentMonthFragment;
import com.example.ramithrd.lecturemanagementsystem.StudentView.Fragments.StudentTodayFragment;
import com.example.ramithrd.lecturemanagementsystem.StudentView.Interfaces.StudentService;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static java.security.AccessController.getContext;

public class StudentMainActivity extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;

    private ViewPager mViewPager;

    private GlobalClass globalClass;

    private StudentService studentService;
    private ProgressDialog mAttendanceProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_main);

        globalClass = ((GlobalClass) getApplicationContext());

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            if (extras.containsKey("userDetails")) {
                User userInfo = extras.getParcelable("userDetails");
                globalClass.setUserInfo(userInfo);
            }
        }


        final String ENDPOINT_URL  = getString(R.string.student_service_url);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ENDPOINT_URL)
                .client(getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        studentService = retrofit.create(StudentService.class);

        mAttendanceProgress = new ProgressDialog(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Hello, "+globalClass.getUserInfo().getFirst_name()+"!");
        setSupportActionBar(toolbar);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_student_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_sign_out) {
            Intent loginIntent = new Intent(StudentMainActivity.this, LoginActivity.class);
            loginIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(loginIntent);
        }

        if(id == R.id.action_scan_attendance){

            IntentIntegrator integrator = new IntentIntegrator(StudentMainActivity.this);
            integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
            integrator.setPrompt("Scan Qr code to mark attendance");
            integrator.setBeepEnabled(false);
            integrator.setBarcodeImageEnabled(true);
            integrator.setOrientationLocked(true);
            integrator.setCaptureActivity(QrCaptureActivityPortrait.class);
            integrator.initiateScan();

        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if(result != null) {

            if(result.getContents() == null) {

                Toast.makeText(this, "Cancelled Qr Scan", Toast.LENGTH_LONG).show();

            } else {

                mAttendanceProgress.setMessage("Marking Attendance ...");
                mAttendanceProgress.show();
                mAttendanceProgress.setCancelable(false);

                String sessionIDStr = result.getContents();
                if(sessionIDStr.contains("att-")){

                    String[] sessionId = sessionIDStr.split("-");

                    LecAttendance attendance = new LecAttendance();
                    attendance.setSession_Id(sessionId[1]);
                    attendance.setSession_Id(globalClass.getUserInfo().getUserId());
                    attendance.setStudent_Id(globalClass.getUserInfo().getUserId());

                    Call<Boolean> sendAttendance = studentService.addAttendance(attendance);
                    sendAttendance.enqueue(new Callback<Boolean>() {
                        @Override
                        public void onResponse(Call<Boolean> call, Response<Boolean> response) {

                            mAttendanceProgress.hide();

                        }

                        @Override
                        public void onFailure(Call<Boolean> call, Throwable t) {

                        }
                    });

                    Toast.makeText(this, sessionIDStr, Toast.LENGTH_LONG).show();

                }else{
                    Toast.makeText(this,"Invalid Qr Code Scan!", Toast.LENGTH_LONG).show();
                }

            }

        } else {

            super.onActivityResult(requestCode, resultCode, data);

        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if(position == 0){

                return StudentTodayFragment.newInstance();

            }else {

                return StudentMonthFragment.newInstance();

            }
        }

        @Override
        public int getCount() {
            // Show 2 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "TODAY'S LECTURES";
                case 1:
                    return "THIS MONTH";
            }
            return null;
        }
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
