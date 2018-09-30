package com.example.poi9438.myapplication;


import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import static android.content.ContentValues.TAG;

public class JsonReader{
    private String htmlPageUrl = "http://job.seoul.go.kr/www/jobCafe/jobCafe.do?method=getCafeMain"; //파싱할 홈페이지의 URL주소
    private TextView textviewHtmlDocument;
    private String htmlContentInStringFormat;
    private String htmlContentInLinkFormat;
    long now = System.currentTimeMillis();
    Date date = new Date(now);
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String getTime = sdf.format(date);
    Date date1;
    {
        try {
            date1 = sdf.parse(getTime);
        }
        catch (ParseException a) {
            a.printStackTrace();
        }
    }

    int cnt=0;
    String u,u2;
Context c;
    public JsonReader(Context context) throws IOException, JSONException {
c=context;
        ((ProjectApplication)c.getApplicationContext()).bag_text = new ArrayList<String>();
        u = "http://openapi.seoul.go.kr:8088/66764e5648706f693539566259534c/json/JobFairInfo/1/5/2018";
        u2 = "http://openapi.seoul.go.kr:8088/6157506b65706f6936366650704b6c/json/MgisJobCafe/1/5/";
        JsoupAsyncTask jsoupAsyncTask = new JsoupAsyncTask(context);
        jsoupAsyncTask.execute();

        new Thread() {
            public void run() {

                JSONObject json = null;
                try {
                    json = readJsonFromUrl(u);

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                try {
                    JSONObject ob = (JSONObject) json.get("JobFairInfo");
                    try{
                        JSONArray array = (JSONArray) ob.get("row");
                        for(int i=0 ; i<array.length() ; i++){
                            JSONObject result = null;
                            try {
                                result = (JSONObject) array.get(i);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            String fair_name = null;
                            try {
                                fair_name = result.getString("JOBFAIR_NAME");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            String fair_turn = null;
                            try {
                                fair_turn = result.getString("JOBFAIR_TURN");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            String fair_date = null;
                            try {
                                fair_date = result.getString("JOBFAIR_DATE");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            String fair_stime = null;
                            try {
                                fair_stime = result.getString("JOBFAIR_FRTIME");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            String fair_etime = null;
                            try {
                                fair_etime = result.getString("JOBFAIR_EDTIME");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            String fair_site = null;
                            try {
                                fair_site = result.getString("JOBFAIR_LOCATION");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            String fair_url = null;
                            try {
                                fair_url = result.getString("JOBFAIR_URL");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                            ((ProjectApplication)c.getApplicationContext()).bag_text.add(fair_name.toString() +"("+ fair_turn.toString() + ")/" + fair_date.toString()+" "+fair_stime +" ~ "+fair_date.toString() +" "+ fair_etime.toString()+"/"+ fair_site.toString());
                            System.out.println("소령 : "+((ProjectApplication)c.getApplicationContext()).bag_text);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

               // Log.d(TAG,"bun1 : " + json.toString());
                //String fair_name = null;
                                //edtHtml.setText(naverHtml);
                //Log.d(TAG,"먀먀먐");
                /*json = null;
                try {
                    json = readJsonFromUrl(u2);
                    Log.d(TAG,"u2 = "+ json.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
/*
                try {
                    JSONObject ob = (JSONObject) json.get("JobFairInfo");
                    try{
                        JSONArray array = (JSONArray) ob.get("row");
                        for(int i=0 ; i<array.length() ; i++){
                            JSONObject result = null;
                            try {
                                result = (JSONObject) array.get(i);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            String fair_name = null;
                            try {
                                fair_name = result.getString("JOBFAIR_NAME");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            String fair_turn = null;
                            try {
                                fair_turn = result.getString("JOBFAIR_TURN");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            String fair_date = null;
                            try {
                                fair_date = result.getString("JOBFAIR_DATE");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            String fair_stime = null;
                            try {
                                fair_stime = result.getString("JOBFAIR_FRTIME");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            String fair_etime = null;
                            try {
                                fair_etime = result.getString("JOBFAIR_EDTIME");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            String fair_site = null;
                            try {
                                fair_site = result.getString("JOBFAIR_LOCATION");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            String fair_url = null;
                            try {
                                fair_url = result.getString("JOBFAIR_URL");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                            Log.d(TAG,"박람회 : " + fair_name.toString() + fair_turn.toString() + "/" + fair_date.toString()+"-"+fair_stime +"/"+fair_date.toString() +"-"+ fair_etime.toString()+"/"+ fair_site.toString());

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }*/

                //Log.d(TAG,"bun1 : " + json.toString());

            }
        }.start();
        //System.out.println(json.get("id"));
    }

    //???
    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            Bundle bun = msg.getData();
            Log.d(TAG,"먀먀먐");
            String fair_name = bun.getString("JOBFAIR_NAME");
            String fair_turn = bun.getString("JOBFAIR_TURN");
            String fair_date = bun.getString("JOBFAIR_DATE");
            String fair_stime = bun.getString("JOBFAIR_FRTIME");
            String fair_etime = bun.getString("JOBFAIR_EDTIME");
            String fair_site = bun.getString("JOBFAIR_LOCATION");
            String fair_url = bun.getString("JOBFAIR_URL");



            Log.d(TAG,"박람회 : " + fair_name.toString() + fair_turn.toString() + "/" + fair_date.toString()+"-"+fair_stime +"/"+fair_date.toString() +"-"+ fair_etime.toString()+"/"+ fair_site.toString() +"/"+ fair_url.toString());
            //edtHtml.setText(naverHtml);
        }
    };

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            is.close();
        }
    }


}
