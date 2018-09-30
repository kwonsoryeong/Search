package com.example.poi9438.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static android.content.ContentValues.TAG;

/* *
 * DbOpenHelper Class
 * Created by TonyChoi on 2016. 3. 29..
 */
public class CalDbOpenHelper {

    private static final String DATABASE_NAME = "CalDb.db";
    private static final int DATABASE_VERSION = 4;
    public static SQLiteDatabase mDB;
    private DataBaseHelper mDBHelper;
    private Context mCtx;
    static Resources res = null;
    Context c;
    InputStream is;
    BufferedReader reader;
    static String ver;
    private class DataBaseHelper extends SQLiteOpenHelper {
        /**
         * 데이터베이스 헬퍼 생성자
         * @param context   context
         * @param name      Db Name
         * @param factory   CursorFactory
         * @param version   Db Version
         */
        public DataBaseHelper(Context context, String name,
                              SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
            c = context;
            res = context.getResources();
        }

        //최초 DB를 만들 때 한번만 호출
        @Override
        public void onCreate(SQLiteDatabase db) {
            /*db.execSQL("create table CalDb ("
                    + "_id integer primary key autoincrement, "
                    + "name text not null ,"
                    + "sy integer not null , "
                    + "sm integer not null , "
                    + "sd integer not null , "
                    + "sh integer not null , "
                    + "smi text not null ,"
                    + "ey integer not null , "
                    + "em integer not null , "
                    + "ed integer not null , "
                    + "eh integer not null , "
                    + "emi integer not null );");*/
            db.execSQL("create table CalDb ("
                    + "_id integer primary key autoincrement, "
                    + "name text not null ,"
                    + "y integer not null , "
                    + "m integer not null , "
                    + "d integer not null , "
                    + "sh integer not null , "
                    + "smi integer not null , "
                    + "eh integer not null , "
                    + "emi text not null  );");
        }

        //버전이 업데이트 되었을 경우 DB를 다시 만들어주는 메소드
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            //업데이트를 했는데 DB가 존재할 경우 onCreate를 다시 불러온다
            db.delete("CalDb",null,null);
            db.execSQL("DROP TABLE IF EXISTS CalDb");
            onCreate(db);
        }
    }

    //DbOpenHelper 생성자
    public CalDbOpenHelper(Context context) {
        this.mCtx = context;
    }

    //Db를 여는 메소드
    public CalDbOpenHelper open() throws SQLException {
        mDBHelper = new DataBaseHelper(mCtx, DATABASE_NAME, null, DATABASE_VERSION);
        mDB = mDBHelper.getWritableDatabase();
        return this;
    }
    public Cursor getall() {
        Cursor c = mDB.rawQuery( "Select * from CalDb",null);
        if (c != null && c.getCount() != 0)
            c.moveToFirst();
        return c;
    }

    public Cursor IFexsist(int y, int m, int d) {
        Cursor c = mDB.rawQuery( "Select * from CalDb where y="+y+" and m="+m+" and d="+d,null);
        if (c != null && c.getCount() != 0)
            c.moveToFirst();
        return c;
    }

    //Db를 다 사용한 후 닫는 메소드
    public void close() {


        //mDB.close();
    }

    public void updateCal(String name, int sy, int sm, int sd, int sh, int smi,int ey, int em, int ed, int eh, int emi) {
        int y=sy,m=sm,d=sd;

        while((y<ey)||(y==ey && m<em)||(y==ey && m==em && d<=ed)) {
            System.out.println("날쨔 : "+ y+" "+(m+1)+" "+d);
            mDB.execSQL("INSERT INTO CalDb VALUES (null,'" + name + "'," + y + "," + (m + 1) + "," + d + "," + sh + ","+ smi + ","+ eh + "," + emi + ");");
            d++;
            if(d==32){
                m++;d=1;
            }
            if(m==13){
                y++;m=1;
            }
        }
    }

}