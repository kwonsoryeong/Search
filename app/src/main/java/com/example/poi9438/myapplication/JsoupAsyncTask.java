package com.example.poi9438.myapplication;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class JsoupAsyncTask extends AsyncTask<Void, Void, Void> {
    private String htmlPageUrl = "http://job.seoul.go.kr/www/jobCafe/jobCafe.do?method=getCafeMain"; //파싱할 홈페이지의 URL주소
    Context c;
    private String htmlContentInStringFormat;
    private String htmlContentInLinkFormat;
    ArrayList<String> program = new ArrayList<String>();
    ArrayList<String> programlnik = new ArrayList<String>();
    ArrayList<ArrayList<String>> programs = new ArrayList<ArrayList<String>>();
    public JsoupAsyncTask(Context context) {
        c = context;
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(Void... params) {
        try {

            Document doc = Jsoup.connect(htmlPageUrl).get();

            Log.d("tag","url webcrowling : "+doc.toString());


            Elements links = doc.select("td.al a[href]"); //url
            //Log.d("tag","url webcrowling : "+ links.toString());
            System.out.println("-------------------------------------------------------------");

            for(Element link: links){
                System.out.println("link: " + link.text());
                htmlContentInLinkFormat = (link.attr("abs:href"));
                System.out.println("url : "+htmlContentInLinkFormat);
                programlnik.add(htmlContentInLinkFormat);
            }
            for (Element scripts : doc.getElementsByTag("script")) {
                // get data from <script>
                System.out.println("똑똑똑 저기욤 : " + scripts);

                for (DataNode dataNode : scripts.dataNodes()) {

                    //System.out.println("뭬 : "+ dataNode.getWholeData());
                }
            }
            //System.out.println(doc.toString());


            //테스트1
            Elements titles= doc.select("td");
            //Elements links = doc.select("td.al a[href]");
            System.out.println("-------------------------------------------------------------");
            int i = 0;
            int j = 0;
            int breakk = 1000;
            for(Element e: titles){

                if (i == 8)
                {
                    //String url = programlnik.get(j);
                    //program.add(url+"\n");
                    //String url = programlnik.get(j);
                    //program.add(url+"\n");
                    //program.add(htmlContentInStringFormat);
                    programs.add(program);
                    program = new ArrayList<String>();
                    i=0;
                }
                System.out.println(Integer.toString(i)+"번째 문자: " + e.text());
                htmlContentInStringFormat = e.text();
                if (i == 0)
                {
                    if(htmlContentInStringFormat.equals(""))break;
                    //Log.d("TAG","순서 ; "+ e.text());
                }
                    /*else if (i == 2)
                    {
                        program.add(htmlContentInStringFormat);
                        int idx = htmlContentInStringFormat.indexOf("~");
                        String htmltime = htmlContentInStringFormat.substring(idx+1);
                        Date date2 = new Date();
/*                        {
                            try {
                                date2 = sdf.parse(htmltime);
                            }
                            catch (ParseException b) {
                                b.printStackTrace();
                            }
                        }
                        System.out.println("출력2------------------------");
                        System.out.println(date1+"출력\n");
                        System.out.println(date2+"출력");
                        System.out.println(date1.after(date2)+"출력");
                        if (date1.after(date2) != false)
                        {
                            break;
                        }
                        i++;
                    }*/
                    /*else if (i == 7)
                    {
                        String url = programlnik.get(j);
                        program.add(url+"\n");
                        program.add(htmlContentInStringFormat);
                        programs.add(program);
                        i=0;
                        j++;
                        program = new ArrayList<String>();
                    }*/
                else if (i < 8)
                {
                    program.add(htmlContentInStringFormat);

                }
                i++;
            }



        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {

        ((ProjectApplication)c.getApplicationContext()).jobcafelesson_text = programs;
        System.out.println("또령 : "+((ProjectApplication)c.getApplicationContext()).jobcafelesson_text);

        for(int i=0 ; i<(((ProjectApplication)c.getApplicationContext()).jobcafelesson_text).size() ; i++){
            Log.d("TAG","이게 뭘까//장소 "+(((ProjectApplication)c.getApplicationContext()).jobcafelesson_text).get(i).get(0));
            Log.d("TAG","이건? 제목"+(((ProjectApplication)c.getApplicationContext()).jobcafelesson_text).get(i).get(1));
            Log.d("TAG","요곤? 지역"+(((ProjectApplication)c.getApplicationContext()).jobcafelesson_text).get(i).get(2));
            Log.d("TAG","이게 뭘까???내용 "+(((ProjectApplication)c.getApplicationContext()).jobcafelesson_text).get(i).get(3));
            Log.d("TAG","이건 date"+(((ProjectApplication)c.getApplicationContext()).jobcafelesson_text).get(i).get(4));
            Log.d("TAG","이건 마감 여부"+(((ProjectApplication)c.getApplicationContext()).jobcafelesson_text).get(i).get(5));
        }

        Log.d("TAG","----------------------------------------------------------");
        for(int i=0 ; i<(((ProjectApplication)c.getApplicationContext()).bag_text).size() ; i++){
            String[] words = (((ProjectApplication)c.getApplicationContext()).bag_text).get(i).split("/");

            Log.d("TAG","이게 뭘까 제목"+words[0]);
            Log.d("TAG","이건? 날짜"+words[1]);
            Log.d("TAG","요곤? 장소"+words[2]);
        }
    }
}
