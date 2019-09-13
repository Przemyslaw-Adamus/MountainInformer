package com.mountaininformer;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class AddPersonActivity extends AppCompatActivity {

    private Button buttonAdd;
    private TextView textViewAddName;
    private TextView textViewAddSurname;
    private TextView textViewAddNumber;
    private Switch switchSMSstart;
    private Switch switchSMSend;
    private Switch switchSMShelp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_person);
        buttonAdd = findViewById(R.id.buttonAdd);
        textViewAddName = findViewById(R.id.editTextAddName);
        textViewAddSurname = findViewById(R.id.editTextAddSurname);
        textViewAddNumber = findViewById(R.id.editTextAddNumber);
        switchSMSstart = findViewById(R.id.switchSMSstart);
        switchSMSend = findViewById(R.id.switchSMSend);
        switchSMShelp = findViewById(R.id.switchSMShelp);

        switchSMShelp.setChecked(true);
        switchSMSstart.setChecked(true);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String surname = textViewAddSurname.getText().toString();
                String name = textViewAddName.getText().toString();
                String number = textViewAddNumber.getText().toString();
                int SMSstart =0;
                int SMSend =0;
                int SMShelp =0;
                boolean isAdd;

                if(switchSMSstart.isSelected()){
                    SMSstart =1;
                }
                if(switchSMSend.isSelected()){
                    SMSend =1;
                }
                if(switchSMShelp.isSelected()){
                    SMShelp =1;
                }

                if(name.length() < 1){
                    new SweetAlertDialog(AddPersonActivity.this, SweetAlertDialog.WARNING_TYPE)
                            .setTitleText("Opss...")
                            .setContentText(getString(R.string.name_missing_alert))
                            .setConfirmButton(getString(R.string.button_correct),new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                    sweetAlertDialog.dismiss();
                                    return;
                                }
                            })
                            .show();
                }
                else if(surname.length() <1){
                    new SweetAlertDialog(AddPersonActivity.this, SweetAlertDialog.WARNING_TYPE)
                            .setTitleText("Opss...")
                            .setContentText(getString(R.string.surname_missing_alert))
                            .setConfirmButton(getString(R.string.button_correct),new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                    sweetAlertDialog.dismiss();
                                    return;
                                }
                            })
                            .show();
                }
                else if(number.length() <1){
                    new SweetAlertDialog(AddPersonActivity.this, SweetAlertDialog.WARNING_TYPE)
                            .setTitleText("Opss...")
                            .setContentText(getString(R.string.number_missing_alert))
                            .setConfirmButton(getString(R.string.button_correct),new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                    sweetAlertDialog.dismiss();
                                    return;
                                }
                            })
                            .show();
                }
                else if(number.length() > 9){
                    new SweetAlertDialog(AddPersonActivity.this, SweetAlertDialog.WARNING_TYPE)
                            .setTitleText("Opss...")
                            .setContentText(getString(R.string.number_error_alert))
                            .setConfirmButton(getString(R.string.button_correct),new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                    sweetAlertDialog.dismiss();
                                    return;
                                }
                            })
                            .show();
                }
                else{
                    PersonsDBAdapter personsDBAdapter = new PersonsDBAdapter(AddPersonActivity.this);
                    personsDBAdapter.open();
                    isAdd = personsDBAdapter.insertEntry(name,surname,number,SMSstart,SMSend,SMShelp);
                    personsDBAdapter.close();

                    if(isAdd){
                        new SweetAlertDialog(AddPersonActivity.this, SweetAlertDialog.SUCCESS_TYPE)
                                .setTitleText("Yeaaa...")
                                .setContentText(getString(R.string.add_success_alert))
                                .setConfirmButton(getString(R.string.button_OK),new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                                        sweetAlertDialog.dismiss();
                                        finish();
                                        Intent intent = new Intent(AddPersonActivity.this, MainActivity.class);
                                        startActivity(intent);
                                    }
                                })
                                .show();
                    }
                    else{
                        new SweetAlertDialog(AddPersonActivity.this, SweetAlertDialog.SUCCESS_TYPE)
                                .setTitleText("Opss...")
                                .setContentText(getString(R.string.add_error_alert))
                                .setConfirmButton(getString(R.string.button_cancle),new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                                        sweetAlertDialog.dismiss();
                                        return;
                                    }
                                })
                                .show();
                    }
                }
            }
        });
    }

    public void onBackPressed() {
        finish();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
