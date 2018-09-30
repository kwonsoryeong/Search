package com.example.poi9438.myapplication;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.SQLException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.InputStream;
import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {

    private ArrayList<Item> mItems;
    DbOpenHelper mDbOpenHelper;
    Context mContext;
    Cursor mCursor;

    public RecyclerViewAdapter(ArrayList itemList) {

        mItems = itemList;

    }

    // 필수 오버라이드 : View 생성, ViewHolder 호출
    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        mContext = parent.getContext();
        RecyclerViewHolder holder = new RecyclerViewHolder(v);

        mDbOpenHelper = new DbOpenHelper(mContext);
        try {
            mDbOpenHelper.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return holder;
    }

    @Override

    public void onBindViewHolder(RecyclerViewHolder holder, final int position) {
        AssetManager am = mContext.getResources().getAssets() ;
        InputStream is = null ;
        try {
            is = am.open(mItems.get(position).image + ".png");
            Bitmap bm = BitmapFactory.decodeStream(is) ;
            holder.mImage.setImageBitmap(bm) ;
            is.close() ;
        }catch (Exception e){
            Log.d(TAG,"안녕?");
        }


        // 해당 position 에 해당하는 데이터 결합
        //holder.mImage.setImageResource(R.drawable.seoul_workcenter);
        holder.mName.setText(mItems.get(position).name);
        holder.mScore.setText(Float.toString(mItems.get(position).score));

        // 이벤트처리 : 생성된 List 중 선택된 목록번호를 Toast로 출력
        holder.mZzim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCursor = mDbOpenHelper.view_click(mItems.get(position).name);
                mCursor.moveToFirst();
                if(mCursor!=null) {
                    do {
                        String name = mCursor.getString(mCursor.getColumnIndex("name"));
                        int zzim = mCursor.getInt(mCursor.getColumnIndex("zzim"));
                        String term_s = mCursor.getString(mCursor.getColumnIndex("term1_s"));
                        String term_e = mCursor.getString(mCursor.getColumnIndex("term1_e"));
                        Log.d(TAG,"찜 :: "+String.valueOf(mCursor.getInt(mCursor.getColumnIndex("zzim"))));
                        ImageButton b1 = (ImageButton) v.findViewById(v.getId());

                        if(zzim==1){
                            mDbOpenHelper.UpdateZzim(name,0);
                            b1.setSelected(false);
                        }
                        else{
                            mDbOpenHelper.UpdateZzim(name,1);
                            b1.setSelected(true);
                            //여기용
                            Intent intent = new Intent(mContext,scheduling.class);
                            intent.putExtra("name",name);
                            intent.putExtra("period",term_s+"~"+term_e);
                            mContext.startActivity(intent);
                        }
                        Log.d(TAG,"찜이 변경되었습니다 :: "+String.valueOf(mCursor.getInt(mCursor.getColumnIndex("zzim"))));
                    } while (mCursor.moveToNext());
                }
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, String.format("%d 선택", position + 1), Toast.LENGTH_SHORT).show();
                mCursor = mDbOpenHelper.view_click(mItems.get(position).name);
                mCursor.moveToFirst();
                if(mCursor!=null) {
                    do {
                        String url = mCursor.getString(mCursor.getColumnIndex("address"));
                        String title = mCursor.getString(mCursor.getColumnIndex("name"));
                        Log.d(TAG, "url : " +url);
                        //Intent i = new Intent(Intent.ACTION_VIEW);

                        //Uri u = Uri.parse(url);
                        //i.setData(u);
                        Intent intent = new Intent(mContext,WebviewActivity.class);
                        intent.putExtra("url",url);
                        intent.putExtra("title",title);
                        mContext.startActivity(intent);

                    } while (mCursor.moveToNext());
                }

            }
        });

    }

    // 필수 오버라이드 : 데이터 갯수 반환
    @Override
    public int getItemCount() {
        return mItems.size();
    }

}
