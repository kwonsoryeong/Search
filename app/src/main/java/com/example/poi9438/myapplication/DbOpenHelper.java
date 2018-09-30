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

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static android.content.ContentValues.TAG;

/* *
 * DbOpenHelper Class
 * Created by TonyChoi on 2016. 3. 29..
 */
public class DbOpenHelper {

    private static final String DATABASE_NAME = "Seoulem.db";
    private static final int DATABASE_VERSION = 13;
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
            try {
                JsonReader js = new JsonReader(c);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            db.execSQL("create table Seoulem ("
                    + "_id integer primary key autoincrement, "
                    + "name text not null ,"
                    + "image text not null ,"
                    + "address text not null , "
                    + "term1 text not null , "
                    + "term1_s text not null , "
                    + "term1_e text not null , "
                    + "term2 text not null , "
                    + "term2_s text not null , "
                    + "term2_e text not null , "
                    + "term3 text not null , "
                    + "term3_s text not null , "
                    + "term3_e text not null , "
                    + "site text not null , "
                    + "score real not null , "
                    + "zzim integer not null , "
                    + "tag1 integer not null , "
                    + "tag2 integer not null , "
                    + "tag3 integer not null , "
                    + "tag4 integer not null , "
                    + "tag5 integer not null , "
                    + "tag6 integer not null , "
                    + "tag7 integer not null , "
                    + "tag8 integer not null , "
                    + "tag9 integer not null , "
                    + "tag10 integer not null , "
                    + "tag11 integer not null , "
                    + "tag12 integer not null , "
                    + "tag13 integer not null , "
                    + "tag14 integer not null , "
                    + "tag15 integer not null , "
                    + "tag16 integer not null , "
                    + "tag17 integer not null ); ");



            /*for(int i=0 ; i<(((ProjectApplication)c.getApplicationContext()).jobcafelesson_text).size() ; i++){
                String aa = (((ProjectApplication)c.getApplicationContext()).jobcafelesson_text).get(i).get(0); //장소
                String bb = (((ProjectApplication)c.getApplicationContext()).jobcafelesson_text).get(i).get(1);//제목
                String cc = (((ProjectApplication)c.getApplicationContext()).jobcafelesson_text).get(i).get(2); //지역
                String dd= (((ProjectApplication)c.getApplicationContext()).jobcafelesson_text).get(i).get(3); //내용
                String ee=(((ProjectApplication)c.getApplicationContext()).jobcafelesson_text).get(i).get(4); //date
                String ff = (((ProjectApplication)c.getApplicationContext()).jobcafelesson_text).get(i).get(5);//마감
                db.execSQL("INSERT INTO Seoulem VALUES (null,'"+bb+"','jobcafe','http://job.seoul.go.kr/jobcafe/index.jsp','기간',"+ee+",0,'',0,0,'0',0,0,"+cc+",0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);");

            }
            for(int i=0 ; i<(((ProjectApplication)c.getApplicationContext()).bag_text).size() ; i++){
                String[] words = (((ProjectApplication)c.getApplicationContext()).bag_text).get(i).split("/");
                db.execSQL("INSERT INTO Seoulem VALUES (null,'"+words[0]+"','bag','https://have.seoul.go.kr/jsonDetailView2.action?m=list&service_id=1284','기간',"+words[1]+",0,'',0,0,'0',0,0,"+words[2]+",0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);");


            }*/
            db.execSQL("INSERT INTO Seoulem VALUES (null,'2018년 9월 19일 5060 취업준비교육(재진입지원) : 안성맞춤 내 일 찾기','seoul_workcenter','http://job.seoul.go.kr/www/training/center_training/Www_center_edc.do?method=getWww_center_edc&edcSn=1000001757','모집기간',20180814,20180917,'교육기간',20180919,20180919,'0',0,0,'서울특별시 중구 삼일대로 363 장교빌딩 1층',2,0,0,1,1,0,0,0,0,0,0,0,0,1,0,0,1,0,0);");
            db.execSQL("INSERT INTO Seoulem VALUES (null,'2018년 서울시 잡콘서트','seoul_workcenter','http://job.seoul.go.kr/www/training/center_training/Www_center_edc.do?method=getWww_center_edc&edcSn=1000001764&searchCondition=&searchKeyword=&pageIndex=1','모집기간',20180821,20180828,'교육기간',20180828,20180828,'0',0,0,'서울특별시 중구 삼일대로 363 장교빌딩 1층',3,0,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0);");
            db.execSQL("INSERT INTO Seoulem VALUES (null,'2017년 lalala','seoul_workcenter','http://job.seoul.go.kr/www/training/center_training/Www_center_edc.do?method=getWww_center_edc&edcSn=1000001764&searchCondition=&searchKeyword=&pageIndex=1','모집기간',20180821,20180828,'교육기간',20180828,20180828,'0',0,0,'서울특별시 중구 삼일대로 363 장교빌딩 1층',1,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0);");
            db.execSQL("INSERT INTO Seoulem VALUES (null,'20166년 lalala','seoul_workcenter','http://job.seoul.go.kr/www/training/center_training/Www_center_edc.do?method=getWww_center_edc&edcSn=1000001764&searchCondition=&searchKeyword=&pageIndex=1','모집기간',20180821,20180828,'교육기간',20180828,20180828,'0',0,0,'서울특별시 중구 삼일대로 363 장교빌딩 1층',5,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0);");

        }

        //버전이 업데이트 되었을 경우 DB를 다시 만들어주는 메소드
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            //업데이트를 했는데 DB가 존재할 경우 onCreate를 다시 불러온다
            db.delete("Seoulem",null,null);
            db.execSQL("DROP TABLE IF EXISTS Seoulem");
            onCreate(db);

        }
    }

    //DbOpenHelper 생성자
    public DbOpenHelper(Context context) {
        this.mCtx = context;
    }

    //Db를 여는 메소드
    public DbOpenHelper open() throws SQLException {
        mDBHelper = new DataBaseHelper(mCtx, DATABASE_NAME, null, DATABASE_VERSION);
        mDB = mDBHelper.getWritableDatabase();

        return this;
    }

    //Db를 다 사용한 후 닫는 메소드
    public void close() {


        //mDB.close();
    }

    //커서 전체를 선택하는 메소드
    public Cursor getAllColumns() {
        return mDB.query("bible", null, null, null, null, null, null);
    }
    /*
    public boolean updateColumn(long id, String address, int jang, int jeol, String content) {
        ContentValues values = new ContentValues();
        values.put(DataBases.CreateDB.ADDRESS, address);
        values.put(DataBases.CreateDB.JANG, jang);
        values.put(DataBases.CreateDB.JEOL, jeol);
        values.put(DataBases.CreateDB.CONTENT, content);
        return mDB.update(DataBases.CreateDB._TABLENAME, values, "_id="+id, null) > 0;
    }
    */
    //이름으로 검색하기 (rawQuery)
    public Cursor getMatchAddress(String address,int jang, int jeol) {
        Log.d(TAG,String.format("Select * from bible where address=" + "'" + address + "' and jang="+jang+" and jeol="+jeol));
        Cursor c = mDB.rawQuery( "Select * from bible where address=" + "'" + address + "' and jang="+jang+" and jeol="+jeol, null);
        if (c != null && c.getCount() != 0)
            c.moveToFirst();
        return c;
    }
    public Cursor search_code(String code) {
        int cnt = 0;
        String q = "Select * from Seoulem where ";
        for(int i=1; i<=17 ; i++){
            if(code.charAt(i-1)=='1'){
                if(cnt==0){
                    q+="tag"+i+"=1";
                    cnt++;
                }
                else {
                    q += " and tag" + i + "=1";
                }
            }
        }
        Log.d(TAG,"query = "+q);
        Cursor c = mDB.rawQuery( q, null);
        if (c != null && c.getCount() != 0)
            c.moveToFirst();
        return c;
    }


    public boolean UpdateZzim(String name, int zzim) {
        ContentValues values = new ContentValues();
        values.put("zzim", zzim);
        return mDB.update("Seoulem", values, "name='"+name+"'", null) > 0;
    }

    public Cursor search_like() {
        int cnt = 0;
        String q = "Select * from Seoulem where zzim=1";
        Cursor c = mDB.rawQuery( q, null);
        if (c != null && c.getCount() != 0)
            c.moveToFirst();
        return c;
    }

    public Cursor ScoreSort() {
        Cursor c = mDB.rawQuery( "select * from Seoulem order by score desc", null);
        if (c != null && c.getCount() != 0)
            c.moveToFirst();
        return c;
    }
    public Cursor view_click(String title) {
        Log.d(TAG,"Select * from Seoulem where name='"+title +"'");
        Cursor c = mDB.rawQuery( "Select * from Seoulem where name='"+title +"'", null);
        if (c != null && c.getCount() != 0)
            c.moveToFirst();
        return c;
    }
}