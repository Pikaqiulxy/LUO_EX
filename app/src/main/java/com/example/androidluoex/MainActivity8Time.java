package com.example.androidluoex;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;


public class MainActivity8Time extends AppCompatActivity implements Runnable{

    private static final String TAG = "ha" ;
    double dollar = 0.1474;
    double euro = 0.1255;
    double won = 171.3606;
    TextView tv2;
    SharedPreferences sharedPreferences;
    String todayString,updateDate;

    Handler handler = new Handler(){
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                String str = (String) msg.obj;
                TextView tv4 = findViewById(R.id.func4_text_4);
                tv4.setText(str);
            }
        }
    };

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity8_time);
        tv2 = findViewById(R.id.func4_text_2);

        //用于存取数据
        sharedPreferences = getSharedPreferences("myrate", Activity.MODE_PRIVATE);


        //从xml文件中取出上次更新的日期,若日期相同则不启动更新
        Date today = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        todayString = format.format(today);
        updateDate = sharedPreferences.getString("update_date", "");
        Log.i(TAG, "onCreate: 不需要更新, 上次更新日期" + updateDate);

        if (!updateDate.equals(todayString)) {
            Log.i(TAG, "onCreate: 需要更新, 上次更新日期" + updateDate);
            Thread t = new Thread(MainActivity8Time.this);
            t.start();
        }
    }

    public void run(){
        Log.i("func4","run:");
        URL url = null;
        try{
            url = new URL("http://www.usd-cny.com/bankofchina.htm");
            HttpURLConnection http = (HttpURLConnection)url.openConnection();
            InputStream in = http.getInputStream();

            String html = inputStream2String(in);

            useJsoup(html);
        }catch (MalformedURLException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private String inputStream2String(InputStream inputStream) throws IOException {
        final int bufferSize = 1024;
        final char[] buffer = new char[bufferSize];
        final StringBuilder out = new StringBuilder();
        Reader in = new InputStreamReader(inputStream,"gb2312");
        while (true){
            int rsz = in.read(buffer,0,buffer.length);
            if(rsz < 0)
                break;
            out.append(buffer,0,rsz);
        }
        return out.toString();
    }

    private void useJsoup(String html){
        Document doc = Jsoup.parse(html);
        Elements trs = doc.select("table").get(0).select("tr");
        //欧元
        Elements tds1 = trs.get(7).select("td");
        String text1 = tds1.get(5).text();
        euro = 100/Double.parseDouble(text1);
        String n1 = String.format("%.4f", euro);
        //韩元
        Elements tds2 = trs.get(13).select("td");
        String text2 = tds2.get(5).text();
        won = 100/Double.parseDouble(text2);
        String n2 = String.format("%.4f", won);
        //美元
        Elements tds3 = trs.get(26).select("td");
        String text3 = tds3.get(5).text();
        dollar = 100/Double.parseDouble(text3);
        String n3 = String.format("%.4f", dollar);

        Log.i("Func4","useJsoup:dollar "+n3+",euro "+n1+",won "+n2);

        Message msg = handler.obtainMessage(1);
        msg.obj = "Current exchange rate: dollar "+n3+" ,euro "+n1+" ,won "+n2;
        handler.sendMessage(msg);
    }

    public void count(View btn){
        TextView tv3 = findViewById(R.id.func4_text_3);
        String tv3str = "Please input RMB!";
        double n2 = 0;
        if(TextUtils.isEmpty(tv2.getText())){
            Toast t = Toast.makeText(this,"Please input RMB!",Toast.LENGTH_SHORT);
            t.show();
            tv3.setText(tv3str);
        }else{
            String str = tv2.getText().toString();
            double n1 =  Double.parseDouble(str);
            if(btn.getId()==R.id.func4_btn_1){
                n2 = n1*dollar;
            } else if(btn.getId()==R.id.func4_btn_2){
                n2 = n1*euro;
            } else if(btn.getId()==R.id.func4_btn_3){
                n2 = n1*won;
            }
            tv3str = String.format("%.4f", n2);
            tv3.setText(tv3str);
        }
    }
}

