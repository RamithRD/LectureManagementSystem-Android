package com.example.ramithrd.lecturemanagementsystem.AdminsView.Activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Build;
import android.provider.Settings;
import android.support.design.widget.CoordinatorLayout;
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

import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ramithrd.lecturemanagementsystem.GlobalClass;
import com.example.ramithrd.lecturemanagementsystem.Helpers.NetworkCheck;
import com.example.ramithrd.lecturemanagementsystem.LecturerView.Activities.LecturerMainActivity;
import com.example.ramithrd.lecturemanagementsystem.LecturerView.Fragments.LecturerMonthFragment;
import com.example.ramithrd.lecturemanagementsystem.LecturerView.Fragments.LecturerTodayFragment;
import com.example.ramithrd.lecturemanagementsystem.Model.User;
import com.example.ramithrd.lecturemanagementsystem.R;

public class AdminMainActivity extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;

    private ViewPager mViewPager;

    private GlobalClass globalClass;

    private FloatingActionButton addSessionfab;

    private LinearLayout adminMainContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);

        adminMainContainer = (LinearLayout) findViewById(R.id.admin_main_content);

        globalClass = ((GlobalClass) getApplicationContext());
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            if (extras.containsKey("userDetails")) {
                User userInfo = extras.getParcelable("userDetails");
                globalClass.setUserInfo(userInfo);
            }
        }


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        addSessionfab = (FloatingActionButton) findViewById(R.id.admin_fab);
        addSessionfab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addScheduleIntent = new Intent(AdminMainActivity.this, AdminAddSessionActivity.class);
                startActivity(addScheduleIntent);
            }
        });

        registerReceiver(new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {

                int status = NetworkCheck.getConnectivityStatusString(context);

                if(status == 0){

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        Window window = getWindow();
                        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                        window.setStatusBarColor(Color.RED);
                    }

                    Snackbar.make(adminMainContainer, "Device Offline, Some Features Will Not Function Properly!", Snackbar.LENGTH_LONG)
                            .setAction("GO ONLINE", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Intent intent = new Intent(Settings.ACTION_SETTINGS);
                                    startActivity(intent);
                                }
                            })
                            .setActionTextColor(getResources()
                                    .getColor(android.R.color.holo_red_light))
                            .show();

                    addSessionfab.setEnabled(false);

                }else{

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        Window window = getWindow();
                        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                        window.setStatusBarColor(Color.parseColor("#00796B"));
                    }

                    addSessionfab.setEnabled(true);
                }

            }}, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_admin_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if(position == 0){

                return LecturerTodayFragment.newInstance();

            }else {

                return LecturerMonthFragment.newInstance();

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
}
