package com.example.poi9438.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.GridLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.support.design.internal.BottomNavigationItemView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;

import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;

import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    CalDbOpenHelper mCalDbOpenHelper;

    Calendar cal = Calendar.getInstance();
    Cursor mCursor;
    // 요일 표시 헤더
    private String[] calHeader = {"일", "월", "화", "수", "목", "금", "토"};
    // 날짜 데이터 배열
    private String[][] calDate = new String[6][7];

    private int width = calHeader.length; // 배열 가로 넓이
    private int startDay;   // 월 시작 요일
    private int lastDay;    // 월 마지막 날짜
    private int inputDate = 1;  // 입력 날짜




    GridLayout gridlayout;
    DbOpenHelper mDbOpenHelper;
    int cur_id;
    String cur_name;
    String cur_image, cur_address;


    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        gridlayout =  findViewById(R.id.gridlayout);


        mCalDbOpenHelper = new CalDbOpenHelper(MainActivity.this);
        mCalDbOpenHelper.open();

        final int year = 2018;//Calendar.YEAR;
        final int month = 9;//Calendar.MONTH;
        Log.d("TAG", String.valueOf(year) + " / " + String.valueOf(month));
        try {
            // 입력받은 year와 month를 매개변수로 객체 및 데이터 생성
            // 입력 month(월) 은 1~12 사이의 값이다.
            if (month < 1 || month > 12) {
                System.out.println("월은 1~12 사이의 숫자입니다.");
                throw new Exception();
            } else {

                // Calendar에 년,월,일 셋팅
                cal.set(Calendar.YEAR, year);
                cal.set(Calendar.MONTH, month - 1);
                cal.set(Calendar.DATE, 1);

                startDay = cal.get(Calendar.DAY_OF_WEEK); // 월 시작 요일
                lastDay = cal.getActualMaximum(Calendar.DATE); // 월 마지막 날짜
                System.out.println(startDay + " : " + lastDay);

                // 2차 배열에 날짜 입력
                int row = 0;
                for (int i = 1; inputDate <= lastDay; i++) {
                    System.out.println("dd " + row + " / " + (i - 1) % width);
                    // 시작 요일이 오기전에는 공백 대입
                    if (i < startDay) calDate[row][i - 1] = "";
                    else {
                        // 날짜 배열에 입력
                        calDate[row][(i - 1) % width] = Integer.toString(inputDate);
                        inputDate++;

                        // 가로 마지막 열에 오면 행 바꿈
                        if (i % width == 0) row++;
                    }
                }
            }

            // 달력 헤더 출력 "일월화수목금토"
            for (int i = 0; i < width; i++) {
                TextView text = new TextView(this);
                System.out.print(calHeader[i] + " ");
                text.setText(calHeader[i]);
                text.setTextSize(20);
                text.setPadding(30,15,30,15);
                text.setBackgroundResource(R.drawable.back);
                gridlayout.addView(text);
            }

            // 날짜 배열 출력
            int row = 0;
            TextView text;
            LinearLayout linearlayout;
            for (int j = 1; j < lastDay + startDay; j++) {
                linearlayout = new LinearLayout(this);

                linearlayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int d = (v.getId())/10000;
                        mCursor = mCalDbOpenHelper.IFexsist(year,month,d);
                        try {
                            do {
                                Log.d(TAG,"resultl ; "+
                                        String.valueOf(mCursor.getString(mCursor.getColumnIndex("name")))+ " / " +
                                        String.valueOf(mCursor.getInt(mCursor.getColumnIndex("y"))) + " / " +
                                        String.valueOf(mCursor.getInt(mCursor.getColumnIndex("m"))) + " / " +
                                        String.valueOf(mCursor.getInt(mCursor.getColumnIndex("d"))) + " / " +
                                        String.valueOf(mCursor.getInt(mCursor.getColumnIndex("sh"))) + " / " +
                                        String.valueOf(mCursor.getInt(mCursor.getColumnIndex("smi"))) + " / " +
                                        String.valueOf(mCursor.getInt(mCursor.getColumnIndex("eh"))) + " / " +
                                        String.valueOf(mCursor.getInt(mCursor.getColumnIndex("emi"))));
                            } while (mCursor.moveToNext());
                        }catch (Exception e){}
                    }
                });
                //LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) linearlayout.getLayoutParams();
                //params.height =50;
                //params.width = 50;
                //linearlayout.setLayoutParams(params);
                linearlayout.setOrientation(LinearLayout.VERTICAL);
                linearlayout.setBackgroundResource(R.drawable.back);
                text = new TextView(this);
                System.out.println("ee " + row + " / " + (j - 1) % width);
                System.out.print(calDate[row][(j - 1) % width] + " ");
                text.setText(calDate[row][(j - 1) % width]);
                //text.setId(Integer.parseInt("te"));
                text.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.FILL_PARENT,
                        LinearLayout.LayoutParams.FILL_PARENT));

                linearlayout.addView(text);
                linearlayout.setId(Integer.parseInt(calDate[row][(j - 1) % width]+"0000"));
                if(calDate[row][(j - 1) % width]!="") {
                    mCursor = mCalDbOpenHelper.IFexsist(year, month, Integer.parseInt(calDate[row][(j - 1) % width]));
                    try {
                        System.out.println("Cursor : " + String.valueOf(mCursor.getInt(mCursor.getColumnIndex("_id"))));
                        ImageView imgview = new ImageView(this);
                        imgview.setImageResource(R.drawable.nana);
//                    imgview.setLayoutParams(new LinearLayout.LayoutParams(3,3));
                        Log.d("tag", "전부 다~~ " + year + "/" + month + "/" + calDate[row][(j - 1) % width]);

                        linearlayout.addView(imgview);
                    }catch(Exception e){

                    }
                }

                gridlayout.addView(linearlayout);


                if ((j - 1) % width == width - 1) {
                    //    System.out.println();
                    row++;
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        final CheckBox checkBox1 = (CheckBox) findViewById(R.id.checkBox1) ;
        final CheckBox checkBox21 = (CheckBox) findViewById(R.id.checkBox21) ;
        final CheckBox checkBox22 = (CheckBox) findViewById(R.id.checkBox22) ;
        final CheckBox checkBox31 = (CheckBox) findViewById(R.id.checkBox31) ;
        final CheckBox checkBox32 = (CheckBox) findViewById(R.id.checkBox32) ;
        final CheckBox checkBox33 = (CheckBox) findViewById(R.id.checkBox33) ;
        final CheckBox checkBox41 = (CheckBox) findViewById(R.id.checkBox41) ;
        final CheckBox checkBox42 = (CheckBox) findViewById(R.id.checkBox42) ;
        final CheckBox checkBox43 = (CheckBox) findViewById(R.id.checkBox43) ;
        final CheckBox checkBox44 = (CheckBox) findViewById(R.id.checkBox44) ;
        final CheckBox checkBox45 = (CheckBox) findViewById(R.id.checkBox45) ;
        final CheckBox checkBox51 = (CheckBox) findViewById(R.id.checkBox51) ;
        final CheckBox checkBox52 = (CheckBox) findViewById(R.id.checkBox52) ;
        final CheckBox checkBox53 = (CheckBox) findViewById(R.id.checkBox53) ;
        final CheckBox checkBox54 = (CheckBox) findViewById(R.id.checkBox54) ;
        final CheckBox checkBox61 = (CheckBox) findViewById(R.id.checkBox61) ;
        final CheckBox checkBox62 = (CheckBox) findViewById(R.id.checkBox62) ;



        LinearLayout LL = (LinearLayout)findViewById(R.id.LL);
        mDbOpenHelper = new DbOpenHelper(this);
        mDbOpenHelper.open();
        mCursor = mDbOpenHelper.ScoreSort();
        int i=0;
        try{
            do {
                cur_id = mCursor.getInt(mCursor.getColumnIndex("_id"));
                cur_name = mCursor.getString(mCursor.getColumnIndex("name"));
                cur_image = mCursor.getString(mCursor.getColumnIndex("image"));
                cur_address = mCursor.getString(mCursor.getColumnIndex("address"));
                Log.d(TAG, "나는 "+cur_name+"//"+cur_image);
                TextView v2 = new TextView(this);

                v2.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.FILL_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT));
                v2.setText(cur_name);
                v2.setId(1111);
                v2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        TextView tv = (TextView)v.findViewById(v.getId());
                        Log.d(TAG, "ddddddd "+v.getId());
                        Log.d(TAG,"이건~ " + tv.getText().toString());
                        mCursor = mDbOpenHelper.view_click(tv.getText().toString());
                        mCursor.moveToFirst();
                        String url=null;
                        if(mCursor!=null) {
                                url = mCursor.getString(mCursor.getColumnIndex("address"));
                        }
                        Intent intent = new Intent(MainActivity.this,WebviewActivity.class);
                        intent.putExtra("url",url);
                        MainActivity.this.startActivity(intent);
                    }
                });


                ((LinearLayout) LL).addView(v2);
                i++;
            }while(mCursor.moveToNext());
        } catch (Exception e) {
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TabHost tabHost1 = (TabHost) findViewById(R.id.tabHost1);
        tabHost1.setup();

        // 첫 번째 Tab. (탭 표시 텍스트:"TAB 1"), (페이지 뷰:"content1")
        TabHost.TabSpec ts1 = tabHost1.newTabSpec("Tab Spec 1");
        ts1.setContent(R.id.content1);
        ts1.setIndicator("TAB 1");
        tabHost1.addTab(ts1);

        // 두 번째 Tab. (탭 표시 텍스트:"TAB 2"), (페이지 뷰:"content2")
        TabHost.TabSpec ts2 = tabHost1.newTabSpec("Tab Spec 2");
        ts2.setContent(R.id.content2);
        ts2.setIndicator("TAB 2");
        tabHost1.addTab(ts2);

        // 세 번째 Tab. (탭 표시 텍스트:"TAB 3"), (페이지 뷰:"content3")
        /*TabHost.TabSpec ts3 = tabHost1.newTabSpec("Tab Spec 3");
        ts3.setContent(R.id.content3);
        ts3.setIndicator("TAB 3");
        tabHost1.addTab(ts3);*/

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            String code= "";
            @Override
            public void onClick(View v) {
                if(checkBox1.isChecked()){
                    code+="1";
                }else{code+="0";}
                if(checkBox21.isChecked()){
                    code+="1";
                }else{code+="0";}
                if(checkBox22.isChecked()){
                    code+="1";
                }else{code+="0";}
                if(checkBox31.isChecked()){
                    code+="1";
                }else{code+="0";}
                if(checkBox32.isChecked()){
                    code+="1";
                }else{code+="0";}
                if(checkBox33.isChecked()){
                    code+="1";
                }else{code+="0";}
                if(checkBox41.isChecked()){
                    code+="1";
                }else{code+="0";}
                if(checkBox42.isChecked()){
                    code+="1";
                }else{code+="0";}
                if(checkBox43.isChecked()){
                    code+="1";
                }else{code+="0";}
                if(checkBox44.isChecked()){
                    code+="1";
                }else{code+="0";}
                if(checkBox45.isChecked()){
                    code+="1";
                }else{code+="0";}
                if(checkBox51.isChecked()){
                    code+="1";
                }else{code+="0";}
                if(checkBox52.isChecked()){
                    code+="1";
                }else{code+="0";}
                if(checkBox53.isChecked()){
                    code+="1";
                }else{code+="0";}
                if(checkBox54.isChecked()){
                    code+="1";
                }else{code+="0";}
                if(checkBox61.isChecked()){
                    code+="1";
                }else{code+="0";}
                if(checkBox62.isChecked()){
                    code+="1";
                }else{code+="0";}
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                intent.putExtra("code",code);
                startActivity(intent);
            }

        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_mypage) {
            // Handle the camera action
        } else if (id == R.id.nav_like) {
            Intent intent = new Intent(MainActivity.this,SearchActivity.class);
            intent.putExtra("like","1");
            MainActivity.this.startActivity(intent);

        } else if (id == R.id.nav_setting) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



}

//66764e5648706f693539566259534c
//6157506b65706f6936366650704b6c