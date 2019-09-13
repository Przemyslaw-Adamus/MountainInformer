package com.mountaininformer;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.Formatter;

import static android.content.Context.LOCATION_SERVICE;
import static android.support.v4.content.ContextCompat.getSystemService;

public class Fragment_3 extends Fragment {

    private View view;
    private static final int REQUEST_CALL = 1;
    private static final int DIALOG_INFO_PL = 1;
    private static final int DIALOG_INFO_SK = 2;
    private static final int DIALOG_INFO_CZ = 3;
    private static final int DIALOG_INFO_HELP = 4;

    private LocationManager lm;
    private String bestProvider;
    private Location location;
    private Criteria criteria;

    private Button buttonPL;
    private Button buttonCZ;
    private Button buttonSK;
    private Button buttonHELP;
    private Button buttonCallPL;
    private Button buttonCallSK;
    private Button buttonCallCZ;
    private Button buttonCallHelp;
    private Button buttonSMSPL;
    private Button buttonSMSSK;
    private Button buttonSMSCZ;
    private Button buttonSMSHelp;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_3_layout,container,false);
        buttonPL = view.findViewById(R.id.buttonPL);
        buttonCZ = view.findViewById(R.id.buttonCZ);
        buttonSK = view.findViewById(R.id.buttonSK);
        buttonHELP = view.findViewById(R.id.buttonHELP);
        buttonCallPL = view.findViewById(R.id.buttonCallPL);
        buttonCallSK = view.findViewById(R.id.buttonCallSK);
        buttonCallCZ = view.findViewById(R.id.buttonCallCZ);
        buttonCallHelp = view.findViewById(R.id.buttonCallHelp);
        buttonSMSPL = view.findViewById(R.id.buttonSMSPL);
        buttonSMSSK = view.findViewById(R.id.buttonSMSSK);
        buttonSMSCZ = view.findViewById(R.id.buttonSMSCZ);
        buttonSMSHelp = view.findViewById(R.id.buttonSMSHelp);

        lm = (LocationManager) getActivity().getSystemService(LOCATION_SERVICE);
        criteria = new Criteria();
        bestProvider = lm.getBestProvider(criteria, true);
        if (ActivityCompat.checkSelfPermission(view.getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(view.getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

        }else{
            location = lm.getLastKnownLocation(bestProvider);
        }

        buttonPL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createMoreInfoDialog(DIALOG_INFO_PL);
            }
        });

        buttonCZ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createMoreInfoDialog(DIALOG_INFO_CZ);
            }
        });

        buttonSK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createMoreInfoDialog(DIALOG_INFO_SK);
            }
        });

        buttonHELP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createMoreInfoDialog(DIALOG_INFO_HELP);
            }
        });

        buttonCallPL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //makePhoneCall("787095800");
                makePhoneCall("601100300");
            }
        });

        buttonCallSK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //makePhoneCall("787095800");
                makePhoneCall("18300");
            }
        });

        buttonCallCZ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //makePhoneCall("787095800");
                makePhoneCall("01210");
            }
        });

        buttonCallHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //makePhoneCall("787095800");
                makePhoneCall("112");
            }
        });

        buttonSMSPL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(view.getContext(),getString(R.string.ToastMoreInfoHelp),Toast.LENGTH_LONG).show();
                //makeSMS("787095800");
                makeSMS("601100300");
            }
        });

        buttonSMSSK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(view.getContext(),getString(R.string.ToastMoreInfoHelp),Toast.LENGTH_LONG).show();
                //makeSMS("787095800");
                makeSMS("18300");
            }
        });

        buttonSMSCZ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(view.getContext(),getString(R.string.ToastMoreInfoHelp),Toast.LENGTH_LONG).show();
                //makeSMS("787095800");
                makeSMS("01210");
            }
        });

        buttonSMSHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(view.getContext(),getString(R.string.ToastMoreInfoHelp),Toast.LENGTH_LONG).show();
                //makeSMS("787095800");
                makeSMS("112");
            }
        });

        return view;
    }

    private void createMoreInfoDialog(int typeDialog) {
        String msg = "";
        switch (typeDialog){
            case DIALOG_INFO_PL:
                msg = getString(R.string.moreInfoPL);
                break;
            case DIALOG_INFO_SK:
                msg = getString(R.string.moreInfoSK);
                break;
            case DIALOG_INFO_CZ:
                msg = getString(R.string.moreInfoCZ);
                break;
            case DIALOG_INFO_HELP:
                msg = getString(R.string.moreInfoHELP);
                break;
        }

        Dialog dialogInfo;
        dialogInfo = new AlertDialog.Builder(view.getContext())
                .setTitle(R.string.info)
                .setMessage(msg)
                .setNegativeButton(R.string.cancel,null)
                .setIcon(android.R.drawable.ic_dialog_info)
                .show();
    }

    private void makePhoneCall(String number){
        if (number.trim().length() > 0) {

            if (ContextCompat.checkSelfPermission(view.getContext(),
                    Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions((Activity) view.getContext(), new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
            } else {
                String dial = "tel:" + number;
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            }

        } else {
            Toast.makeText(view.getContext(), "Enter Phone Number", Toast.LENGTH_SHORT).show();
        }
    }

    private void makeSMS(final String number){


    }

//    private void sendSMS(String number, String txtMessage) {
//        try{
//            SmsManager smgr = SmsManager.getDefault();
//            smgr.sendTextMessage(number,null,txtMessage,null,null);
//            Toast.makeText(view.getContext(), "SMS został wysłany poprawnie, ale z racji długiej wiadomości upewnij się, czy twój operator nie blokuje wysyłania wiadomości przez aplikację \"Górski Pamiętni\"", Toast.LENGTH_LONG).show();
//        }
//        catch (Exception e){
//            Toast.makeText(view.getContext(), "SMS nie został wysłany, spróbuj ponownie", Toast.LENGTH_SHORT).show();
//        }
//    }
}