package com.example.androidluoex;

import android.app.ListActivity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity6List extends ListActivity implements Runnable {

    private static final String TAG = "RateListActivity";

    Handler handler;
    List<String> list_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        list_data = new ArrayList<String>();

        Thread t = new Thread(MainActivity6List.this);
        t.start();

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 6) {
                    list_data = (List<String>) msg.obj;
                    ListAdapter listAdapter = new ArrayAdapter<String>(
                            MainActivity6List.this,
                            android.R.layout.simple_list_item_1, list_data);
                    setListAdapter(listAdapter);
                }
            }
        };

    }

    @Override
    public void run() {
        URL url;
        String urlString = "https://www.usd-cny.com";
        String html = "";
        List<String> list_data = new ArrayList<String>();

        try {
            url = new URL(urlString);
            HttpsURLConnection http = (HttpsURLConnection) url.openConnection();
            InputStream in = http.getInputStream();

            html = inputStream2String(in);
            Log.i(TAG, "run: html = " + html);
        } catch (MalformedURLException e) {
            Log.i(TAG, "run: MalformedURLException " + e);
            e.printStackTrace();
        } catch (IOException e) {
            Log.i(TAG, "run: IOException " + e);
            e.printStackTrace();
        }


        Document document = Jsoup.parse(html);
        String[][] storage = getTable(document);

        for (String[] row: storage) {
            list_data.add(row[0] + "==>" + row[1]);
            Log.i(TAG, "run: " + row[0] + "==>" + row[1]);
        }

        Message msg = handler.obtainMessage(6);
        msg.obj = list_data;
        handler.sendMessage(msg);
    }

    public static String inputStream2String (InputStream inputStream) throws IOException {
        final int bufferSize = 1024;
        final char[] buffer = new char[bufferSize];
        final StringBuilder out = new StringBuilder();
        Reader in = new InputStreamReader(inputStream, "gb2312");
        while (true) {
            int rsz = in.read(buffer, 0, buffer.length);
            if (rsz < 0) break;
            out.append(buffer, 0, rsz);
        }
        return out.toString();
    }


    public static String[][] getTable(Document document){
        //由于第一行没有相应的元素所以长度进行了手动调整
        Elements trs = document.select("table").select("tr");
        //第一行元素没有td元素
        Elements tds;

        String[][] storages = new String[trs.size()-1][2];

        for (int i = 0; i < trs.size()-1; i++){
            tds = trs.get(i+1).select("td");
            storages[i][0] = tds.get(0).text();
            storages[i][1] = tds.get(4).text();
            Log.i(TAG, "getTable: " + storages[i][0] + "" + storages[i][1]);
        }
        return storages;
    }

}