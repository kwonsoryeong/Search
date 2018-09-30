package com.example.poi9438.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Calendar;

import static android.content.ContentValues.TAG;

public class scheduling extends AppCompatActivity {
    CalDbOpenHelper mCalDbOpenHelper;

    Cursor mCursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scheduling);
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String peri = intent.getStringExtra("period");

        TextView sh_name = findViewById(R.id.schd_name);
        TextView sh_peri = findViewById(R.id.sche_period);
        sh_name.setText(name);
        sh_peri.setText(peri);


        Button calendar_stset =findViewById(R.id.calendar_STset);
        calendar_stset.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                ((ProjectApplication)getApplication()).Tstart =1;
                TimePickerFragment newFragment = new TimePickerFragment();
                newFragment.show(getSupportFragmentManager(),"TimePicker");
            }
        });
        Button calendar_sdset =findViewById(R.id.calendar_SDset);
        calendar_sdset.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                ((ProjectApplication)getApplication()).Dstart =1;
                DatePickerFragment newFragment = new DatePickerFragment();
                newFragment.show(getSupportFragmentManager(),"DatePicker");
            }
        });
        Button calendar_etset =findViewById(R.id.calendar_ETset);
        calendar_etset.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                ((ProjectApplication)getApplication()).Tstart =0;
                TimePickerFragment newFragment = new TimePickerFragment();
                newFragment.show(getSupportFragmentManager(),"TimePicker");
            }
        });
        Button calendar_edset =findViewById(R.id.calendar_EDset);
        calendar_edset.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                ((ProjectApplication)getApplication()).Dstart =0;
                DatePickerFragment newFragment = new DatePickerFragment();
                newFragment.show(getSupportFragmentManager(),"DatePicker");
            }
        });
        Button ok_btn =findViewById(R.id.OKbtn);
        ok_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.d("TAG","뭄뭄 : "+
                        ((ProjectApplication)getApplication()).start_Year+"/"+
                        ((ProjectApplication)getApplication()).start_Month+"/"+
                        ((ProjectApplication)getApplication()).start_Day+"/"+
                        ((ProjectApplication)getApplication()).start_Hour+"/"+
                        ((ProjectApplication)getApplication()).start_minute+" ~ "+
                        ((ProjectApplication)getApplication()).end_Year+"/"+
                        ((ProjectApplication)getApplication()).end_Month+"/"+
                        ((ProjectApplication)getApplication()).end_Day+"/"+
                        ((ProjectApplication)getApplication()).end_Hour+"/"+
                        ((ProjectApplication)getApplication()).end_minute+"/"
                );
                mCalDbOpenHelper = new CalDbOpenHelper(scheduling.this);
                mCalDbOpenHelper.open();
                mCalDbOpenHelper.updateCal("ㅜㄷㄷㄷ",((ProjectApplication)getApplication()).start_Year,((ProjectApplication)getApplication()).start_Month,((ProjectApplication)getApplication()).start_Day,((ProjectApplication)getApplication()).start_Hour,((ProjectApplication)getApplication()).start_minute,((ProjectApplication)getApplication()).end_Year,((ProjectApplication)getApplication()).end_Month,((ProjectApplication)getApplication()).end_Day,((ProjectApplication)getApplication()).end_Hour,((ProjectApplication)getApplication()).end_minute);

                mCursor = mCalDbOpenHelper.getall();
                try {
                    do {
                        Log.d(TAG,"resultl ; "+
                                String.valueOf(mCursor.getInt(mCursor.getColumnIndex("_id"))) + " / " +
                                String.valueOf(mCursor.getString(mCursor.getColumnIndex("name"))) + " / " +
                                String.valueOf(mCursor.getInt(mCursor.getColumnIndex("y"))) + " / " +
                                String.valueOf(mCursor.getInt(mCursor.getColumnIndex("m"))) + " / " +
                                String.valueOf(mCursor.getInt(mCursor.getColumnIndex("d"))) + " / " +
                                String.valueOf(mCursor.getInt(mCursor.getColumnIndex("sh"))) + " / " +
                                String.valueOf(mCursor.getInt(mCursor.getColumnIndex("smi"))) + " / " +
                                String.valueOf(mCursor.getInt(mCursor.getColumnIndex("eh"))) + " / " +

                                String.valueOf(mCursor.getInt(mCursor.getColumnIndex("emi"))));
                    } while (mCursor.moveToNext());
                }catch (Exception e){}
                finish();
            }
        });
    }
}
