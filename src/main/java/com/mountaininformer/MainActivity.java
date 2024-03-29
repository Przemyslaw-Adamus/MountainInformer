package com.mountaininformer;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
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

import cn.pedant.SweetAlert.SweetAlertDialog;

public class MainActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private static final String[] PERMISIONS = {
            Manifest.permission.VIBRATE,
            Manifest.permission.CALL_PHONE,
            Manifest.permission.SEND_SMS,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent intent = new Intent(getApplicationContext(), AddPersonActivity.class);
                startActivity(intent);
            }
        });
        veryfiPermisions();
    }

    @Override
    public void onBackPressed() {
        new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                .setTitleText("Hmmm...")
                .setContentText(String.valueOf(R.string.ending))
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        finish();
                    }
                })
                .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        sweetAlertDialog.dismiss();
                    }
                })
                .show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.action_language:
                break;
            case R.id.action_info:
                break;
            case R.id.action_home:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void veryfiPermisions() {
        //int permisionsBodySensor = ActivityCompat.checkSelfPermission(this, Manifest.permission.BODY_SENSORS);
        int permisionsVibrate = ActivityCompat.checkSelfPermission(this,Manifest.permission.VIBRATE);
        int permisionsSendSMS = ActivityCompat.checkSelfPermission(this,Manifest.permission.SEND_SMS);
        //int permisionsCamera = ActivityCompat.checkSelfPermission(this,Manifest.permission.CAMERA);
        int permisionsLocation = ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION);
        int permisionsCall = ActivityCompat.checkSelfPermission(this,Manifest.permission.CALL_PHONE);
        int permisionsWrite = ActivityCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE);


//        if(permisionsBodySensor != PackageManager.PERMISSION_GRANTED){
//            ActivityCompat.requestPermissions(this,PERMISIONS,1);
//        }
        if(permisionsVibrate != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,PERMISIONS,1);
        }
        if(permisionsSendSMS != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,PERMISIONS,1);
        }
//        if(permisionsCamera != PackageManager.PERMISSION_GRANTED){
//            ActivityCompat.requestPermissions(this,PERMISIONS,1);
//        }
        if(permisionsLocation != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,PERMISIONS,1);
        }
        if(permisionsCall != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,PERMISIONS,1);
        }
        if(permisionsWrite != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,PERMISIONS,1);
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
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
            Fragment fragment = null;
            switch (position){
                case 0:
                    fragment = new Fragment_1();
                    break;
                case 1:
                    fragment = new Fragment_2();
                    break;
                case 2:
                    fragment = new Fragment_3();
                    break;
            }
            return fragment;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }
    }
}
