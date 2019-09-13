package com.mountaininformer;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;

import static android.graphics.Color.GREEN;
import static com.mountaininformer.R.color.colorAccent;
import static com.mountaininformer.R.color.sweet_dialog_bg_color;

public class Fragment_1 extends Fragment {
//#####################################################################################################################################
    private static final int SMS_START = 1;
    private static final int SMS_END = 2;
    private static final int SMS_HELP = 3;

    private View view;
    private TextView timerTextView;
    private ImageButton buttonStartEnd1;
    private ImageButton buttonStartEnd2;
    private ImageButton buttonStartEnd3;
    private ImageButton imageButtonCalender;
    private ImageButton imageButtonTime;
    private TextView textViewDate;
    private TextView textViewTime;
    private ProgressBar progressBar;
    private ImageView imageViewStatus;

    private boolean[] onoff = new boolean[3];
    private long startTime = 0;
    private Handler timerHandler = new Handler();
    private Runnable timerRunnable = new Runnable() {

        @Override
        public void run() {
            long millis = System.currentTimeMillis() - startTime;
            int seconds = (int) (millis / 1000);
            int minutes = seconds / 60;
            seconds = seconds % 60;
            timerTextView.setText(String.format("%d:%02d", minutes, seconds));
            timerHandler.postDelayed(this, 950);
            duration--;
            progressBar.setProgress(duration);
            if(duration == 0){
                createSMS(SMS_HELP);
            }
        }
    };
    private static final int DIALOG_TIME_ID =1;
    private static final int DIALOG_DATE_ID =0;
    private DatePickerDialog.OnDateSetListener dpickerListnerDate;
    private TimePickerDialog.OnTimeSetListener dpickerListnerTime;
    private Calendar calendar = Calendar.getInstance();
    private int yearStart;
    private int monthStart;
    private int dayStart;
    private int hourStart;
    private int minuteStart;
    private int yearEnd;
    private int monthEnd;
    private int dayEnd;
    private int hourEnd;
    private int minuteEnd;
    private int duration;
    private int durationStatic;
    private PersonsDBAdapter personsDBAdapter;
    private List<Person> personList;


    //#####################################################################################################################################
    @SuppressLint("ResourceAsColor")
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_1_layout, container, false);
        timerTextView = view.findViewById(R.id.textViewTimer);
        buttonStartEnd1 = view.findViewById(R.id.buttonStartEnd1);
        buttonStartEnd2 = view.findViewById(R.id.buttonStartEnd2);
        buttonStartEnd3 = view.findViewById(R.id.buttonStartEnd3);
        imageButtonCalender = view.findViewById(R.id.imageButtonCalender);
        imageButtonTime = view.findViewById(R.id.imageButtonTime);
        textViewDate = view.findViewById(R.id.textViewDate);
        textViewTime = view.findViewById(R.id.textViewTime);
        progressBar = view.findViewById(R.id.progressBar);
        imageViewStatus = view.findViewById(R.id.imageViewStatus);

        initPersonList();
        initData();

        dpickerListnerDate = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int _year, int _month, int _dayOfMonth) {
                yearEnd = _year;
                monthEnd = _month+1;
                dayEnd = _dayOfMonth;
                textViewDate.setText(String.format("%d-%d-%d", dayEnd, monthEnd, yearEnd));
            }
        };

        dpickerListnerTime = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int _hourOfDay, int _minute) {
                hourEnd = _hourOfDay;
                minuteEnd = _minute;
                textViewTime.setText(String.format("%d:%d", hourEnd, minuteEnd));
            }
        };

        buttonStartEnd1.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                if(!dataTimeCorect()){
                    return;
                }

                if(onoff[0]){
                    buttonStartEnd1.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(colorAccent)));
                    onoff[0]=false;
                }
                else{
                    buttonStartEnd1.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
                    onoff[0]=true;
                    Toast.makeText(getContext(),"1",Toast.LENGTH_SHORT).show();
                }
                if(onoff[0] && onoff[1] && onoff[2]){
                    start();
                }
                else if(!onoff[0] && !onoff[1] && !onoff[2]){
                    end();
                }
            }
        });

        buttonStartEnd2.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                if(!dataTimeCorect()){
                    return;
                }

                if(onoff[1]){
                    buttonStartEnd2.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(colorAccent)));
                    onoff[1]=false;
                }
                else{
                    buttonStartEnd2.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
                    onoff[1]=true;
                    Toast.makeText(getContext(),"2",Toast.LENGTH_SHORT).show();
                }
                if(onoff[0] && onoff[1] && onoff[2]){
                    start();
                }
                else if(!onoff[0] && !onoff[1] && !onoff[2]){
                    end();
                }
            }
        });

        buttonStartEnd3.setOnClickListener(new View.OnClickListener() {
            @Override
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @SuppressLint("ResourceAsColor")
            public void onClick(View v) {
                if(!dataTimeCorect()){
                    return;
                }

                if(onoff[2]){
                    buttonStartEnd3.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(colorAccent)));
                    onoff[2]=false;
                }
                else{
                    buttonStartEnd3.setBackgroundTintList(ColorStateList.valueOf(GREEN));
                    onoff[2]=true;
                    Toast.makeText(getContext(),"3",Toast.LENGTH_SHORT).show();
                }
                if(onoff[0] && onoff[1] && onoff[2]){
                    start();
                }
                else if(!onoff[0] && !onoff[1] && !onoff[2]){
                    end();
                }
            }
        });

        imageButtonCalender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCreateDialog(DIALOG_DATE_ID).show();
            }
        });

        imageButtonTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCreateDialog(DIALOG_TIME_ID).show();
            }
        });

        return view;
    }



    //#####################################################################################################################################
    @SuppressLint("ResourceAsColor")
    @Override
    public void onPause() {
        super.onPause();
        timerHandler.removeCallbacks(timerRunnable);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            buttonStartEnd1.setBackgroundTintList(ColorStateList.valueOf(colorAccent));
        }
    }

    private void initPersonList() {
        personList = new ArrayList<>();
        personsDBAdapter = new PersonsDBAdapter(getContext());

        personsDBAdapter.open();
        Cursor cursor = personsDBAdapter.getEntry();
        Person personTmp;
        if(cursor == null){
            return;
        }
        while(cursor.moveToNext()) {
            personTmp = new Person(cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getInt(4),cursor.getInt(5), cursor.getInt(6));
            personList.add(personTmp);
        }
        personsDBAdapter.close();
    }

    private void initData(){
        onoff[0] = false;
        onoff[1] = false;
        onoff[2] = false;

        minuteStart = calendar.get(Calendar.MINUTE);
        hourStart = calendar.get(Calendar.HOUR_OF_DAY);
        dayStart = calendar.get(Calendar.DAY_OF_MONTH);
        monthStart = calendar.get(Calendar.MONTH);
        yearStart = calendar.get(Calendar.YEAR);
    }

    protected Dialog onCreateDialog(int id){
        Dialog dialog;
        if(id == DIALOG_DATE_ID) {
            dialog = new DatePickerDialog(getContext(), dpickerListnerDate, yearStart,monthStart,dayStart);
            return dialog;
        }
        else if(id == DIALOG_TIME_ID) {
            dialog = new TimePickerDialog(getContext(),dpickerListnerTime, hourStart,minuteStart,android.text.format.DateFormat.is24HourFormat(getContext()));
            return dialog;
        }
        return null;
    }

    protected void start(){
        createSMS(SMS_START);
        Date now = new Date();
        Date dateEnd = new Date(yearEnd, monthEnd, dayEnd, hourEnd, minuteEnd, 0);
        long diff = now.getTime() - dateEnd.getTime();
        duration = (int)(diff/1000);
        durationStatic = duration;
        progressBar.setMax(durationStatic);
        startTime = System.currentTimeMillis();
        timerHandler.postDelayed(timerRunnable, 0);

        minuteStart = calendar.get(Calendar.MINUTE);
        hourStart = calendar.get(Calendar.HOUR_OF_DAY);
        dayStart = calendar.get(Calendar.DAY_OF_MONTH);
        monthStart = calendar.get(Calendar.MONTH);
        yearStart = calendar.get(Calendar.YEAR);

        imageButtonCalender.setEnabled(false);
        imageButtonTime.setEnabled(false);

    }

    protected void end(){
        timerHandler.removeCallbacks(timerRunnable);
        createSMS(SMS_END);

        minuteStart = calendar.get(Calendar.MINUTE);
        hourStart = calendar.get(Calendar.HOUR_OF_DAY);
        dayStart = calendar.get(Calendar.DAY_OF_MONTH);
        monthStart = calendar.get(Calendar.MONTH);
        yearStart = calendar.get(Calendar.YEAR);

        imageButtonTime.setEnabled(true);
        imageButtonCalender.setEnabled(true);


    }

    private void createSMS(int smsType) {
        String text = "HEPL";
        switch (smsType) {
            case SMS_START:
                    text = getString(R.string.sms_start_text);
                    for (Person tmpPerson : personList) {
                        if (tmpPerson.getSMSstart() == 0) {
                            sendSMS(tmpPerson.getNumber(), text);
                         }
                    }
                    break;
            case SMS_END:
                    text = getString(R.string.sms_end_text);
                    for (Person tmpPerson : personList) {
                        if (tmpPerson.getSMSend() == 0) {
                            sendSMS(tmpPerson.getNumber(), text);
                        }
                    }
                    break;
             case SMS_HELP:
                    text = getString(R.string.sms_help_text);
                    for (Person tmpPerson : personList) {
                        if (tmpPerson.getSMShelp() == 0) {
                            sendSMS(tmpPerson.getNumber(), text);
                        }
                    }
                    break;

                }
        }

    @SuppressLint("ResourceType")
    private void sendSMS(String number, String txtMessage) {
        try {
            SmsManager smgr = SmsManager.getDefault();
            smgr.sendTextMessage(number, null, txtMessage, null, null);
            Toast.makeText(view.getContext(), getString(R.string.sms_successful_alert), Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(view.getContext(), getString(R.string.sms_error_alert), Toast.LENGTH_SHORT).show();
        }
    }

    private boolean dataTimeCorect() {
        if(textViewDate.getText().toString().contains("dd - mm - rrrr")){
            new SweetAlertDialog(getContext(), SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("Opss...")
                    .setContentText(getString(R.string.data_missing_alert))
                    .setConfirmButton(getString(R.string.button_correct),new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            sweetAlertDialog.dismiss();
                        }
                    })
                    .show();
            return false;
        }
        else if(textViewTime.getText().toString().contains("hh : mm")){
            new SweetAlertDialog(getContext(), SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("Opss...")
                    .setContentText(getString(R.string.time_missing_alert))
                    .setConfirmButton(getString(R.string.button_correct),new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            sweetAlertDialog.dismiss();
                        }
                    })
                    .show();
            return false;
        }
        else if(personList.size() == 0){
            new SweetAlertDialog(getContext(), SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("Opss...")
                    .setContentText(getString(R.string.list_empty_error))
                    .setConfirmButton(getString(R.string.button_correct),new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            sweetAlertDialog.dismiss();
                        }
                    })
                    .show();
            return false;
        }
        return true;
    }

//#####################################################################################################################################
}