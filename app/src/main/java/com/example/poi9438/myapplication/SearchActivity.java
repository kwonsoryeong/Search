package com.example.poi9438.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class SearchActivity extends AppCompatActivity {
    Button btn_intent;
    TextView txt_next;
    DbOpenHelper mDbOpenHelper;
    Cursor mCursor;

    int cur_id;
    String cur_name;
    String cur_image;
    String cur_address;
    float cur_score;
    int cur_zzim;

    RecyclerView mRecyclerView;
    LinearLayoutManager mLayoutManager;
    RecyclerViewAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        // ArrayList 에 Item 객체(데이터) 넣기
        ArrayList<Item> items = new ArrayList();

        Intent intent = getIntent();
        String name = intent.getStringExtra("code");
        String like = intent.getStringExtra("like");
        Log.d(TAG,"like = " +like);
        if(like != null && like.equals("1")){
            mDbOpenHelper = new DbOpenHelper(this);
            mDbOpenHelper.open();
            mCursor = mDbOpenHelper.search_like();
            try {
                do {
                    cur_id = mCursor.getInt(mCursor.getColumnIndex("_id"));
                    cur_name = mCursor.getString(mCursor.getColumnIndex("name"));
                    cur_image = mCursor.getString(mCursor.getColumnIndex("image"));
                    cur_address = mCursor.getString(mCursor.getColumnIndex("address"));
                    cur_score = mCursor.getFloat(mCursor.getColumnIndex("score"));
                    cur_zzim = mCursor.getInt(mCursor.getColumnIndex("zzim"));
                    Log.v("hello", "끄아 : " + cur_id + "//" + cur_name + "/" + cur_address + "/" + cur_score);

                    items.add(new Item(cur_image, cur_name, cur_score, cur_zzim));

                } while (mCursor.moveToNext());
            } catch (Exception e) {
            }
        }
        else {
            mDbOpenHelper = new DbOpenHelper(this);
            mDbOpenHelper.open();
            mCursor = mDbOpenHelper.search_code(name);
            try {
                do {
                    cur_id = mCursor.getInt(mCursor.getColumnIndex("_id"));
                    cur_name = mCursor.getString(mCursor.getColumnIndex("name"));
                    cur_image = mCursor.getString(mCursor.getColumnIndex("image"));
                    cur_address = mCursor.getString(mCursor.getColumnIndex("address"));
                    cur_score = mCursor.getFloat(mCursor.getColumnIndex("score"));
                    cur_zzim = mCursor.getInt(mCursor.getColumnIndex("zzim"));
                    Log.v("hello", "끄아 : " + cur_id + "//" + cur_name + "/" + cur_address + "/" + cur_score);

                    items.add(new Item(cur_image, cur_name, cur_score, cur_zzim));

                } while (mCursor.moveToNext());
            } catch (Exception e) {
            }
        }
        // LinearLayout으로 설정
        mRecyclerView.setLayoutManager(mLayoutManager);
        // Animation Defualt 설정
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        // Decoration 설정
        //mRecyclerView.addItemDecoration(new RecyclerViewDecoration(this, RecyclerViewDecoration.VERTICAL_LIST));
        //mRecyclerView.addItemDecoration(new android.support.v7.widget.DividerItemDecoration(this, LinearLayoutManager.VERTICAL));


        // Adapter 생성
        mAdapter = new RecyclerViewAdapter(items);
        mRecyclerView.setAdapter(mAdapter);


        //txt_next = (TextView) findViewById(R.id.code);
        //txt_next.setText(name);

        btn_intent = (Button) findViewById(R.id.btn_intent);
        btn_intent.setOnClickListener(new View.OnClickListener() {
            String code = "";

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
