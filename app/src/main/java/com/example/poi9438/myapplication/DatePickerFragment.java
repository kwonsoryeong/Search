package com.example.poi9438.myapplication;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

import static android.content.ContentValues.TAG;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    TextView tv;
    DatePicker dp;
    Calendar c;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        c = Calendar.getInstance();
        int year = c.get(c.YEAR);
        int month = c.get(c.MONTH); //1월은 '0'부터 시작
        int dayOfMonth = c.get(c.DAY_OF_MONTH);
        DatePickerDialog dpd = new DatePickerDialog(getActivity(),
                AlertDialog.THEME_DEVICE_DEFAULT_DARK, this,
                year, month, dayOfMonth);
        tv = (TextView) getActivity().findViewById(R.id.tv);
        //btnUpdateDate = (Button) getActivity().findViewById(R.id.btn_update_date);
        //btnGetDate = (Button) getActivity().findViewById(R.id.btn_get_date);
        dp = (DatePicker) getActivity().findViewById(R.id.dp);

        //tv.setText(year + "년 " + (month+1) + "월 " + dayOfMonth+"일");
        //btnUpdateDate.setOnClickListener((View.OnClickListener) getActivity());
        //btnGetDate.setOnClickListener((View.OnClickListener) getActivity());
        return dpd;
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        if(((ProjectApplication)getContext().getApplicationContext()).Dstart==1){
            ((ProjectApplication)getContext().getApplicationContext()).start_Year=year;
            ((ProjectApplication)getContext().getApplicationContext()).start_Month=month;
            ((ProjectApplication)getContext().getApplicationContext()).start_Day=dayOfMonth;
            TextView tv = (TextView) getActivity().findViewById(R.id.tv);
            tv.setText(year + "년 " + (month+1) + "월 " + dayOfMonth+"일");
        }
        else {
            ((ProjectApplication)getContext().getApplicationContext()).end_Year=year;
            ((ProjectApplication)getContext().getApplicationContext()).end_Month=month;
            ((ProjectApplication)getContext().getApplicationContext()).end_Day=dayOfMonth;
            TextView tv = (TextView) getActivity().findViewById(R.id.tv3);
            tv.setText(year + "년/" + (month+1) + "월/" + dayOfMonth+"일");
        }



    }
}