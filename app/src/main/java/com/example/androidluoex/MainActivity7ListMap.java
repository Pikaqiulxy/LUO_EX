package com.example.androidluoex;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.arch.core.internal.FastSafeIterableMap;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

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
import java.util.HashMap;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity7ListMap extends AppCompatActivity implements Runnable, AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

    private static final String TAG = "MainActivity7ListMap";

    Handler handler;
    List<HashMap<String, String>> listItems;
    List<HashMap<String, String>> listData;
    ListView listView;

    MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity7_list_map);

        listView =  findViewById(R.id.mylist);
        listItems = new ArrayList<HashMap<String, String>>();

        Thread t = new Thread(MainActivity7ListMap.this);
        t.start();

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 7) {
                    listData = (ArrayList<HashMap<String, String>>) msg.obj;
                    MyAdapter myAdapter = new MyAdapter(MainActivity7ListMap.this,
                            R.layout.list_item,
                            (ArrayList<HashMap<String, String>>) listData);
                    listView.setAdapter(myAdapter);
                    listView.setOnItemClickListener(MainActivity7ListMap.this);
                    listView.setOnItemLongClickListener(MainActivity7ListMap.this);
                }
                super.handleMessage(msg);
            }
        };

    }



    @Override
    public void run() {
        URL url;
        String urlString = "https://www.usd-cny.com";
        String html = "";
        List<HashMap<String, String>> listData = new ArrayList<HashMap<String, String>>();

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
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("ItemTitle", row[0]);
            map.put("ItemDetail", row[1]);
            Log.i(TAG, "run: " + row[0] + "==>" + row[1]);
            listData.add(map);
        }

        Message msg = handler.obtainMessage(7);
        msg.obj = listData;
        handler.sendMessage(msg);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int position, long l) {
        //长按按钮出现提示框,点击确定,确认删除当前选中的数据
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity7ListMap.this);
        builder.setTitle("提示")
                .setMessage("请确认是否删除当前数据")
                .setPositiveButton("是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        Log.i(TAG, "onClick: 点击了确认按钮");
                        myAdapter.remove(listView.getItemAtPosition(position));
                    }
                }).setNegativeButton("否",null);
        builder.create().show();
        return true;
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
        int i;
        Elements trs = document.select("table").select("tr");
        //第一行元素没有td元素
        Elements tds = trs.get(1).select("td");

        String[][] storages = new String[trs.size()-1][2];

        for (i = 0; i < trs.size()-1; i++){
            tds = trs.get(i+1).select("td");
            storages[i][0] = tds.get(0).text();
            storages[i][1] = tds.get(4).text();
            Log.i(TAG, "getTable: " + storages[i][0] + "" + storages[i][1]);
        }
        return storages;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        TextView title = (TextView) view.findViewById(R.id.itemTitle);
        TextView detail = (TextView) view.findViewById(R.id.itemDetail);
        String title2 = String.valueOf(title.getText());
        String detail2 = String.valueOf(detail.getText());
        Log.i(TAG, "onItemClick: title2=" + title2);
        Log.i(TAG, "onItemClick: detail2=" + detail2);

        //显式1
        Intent intent = new Intent();
        intent.setClass(MainActivity7ListMap.this,MainActivity7ListMap2.class);
        Bundle bundle = new Bundle();           //为bundle分配
        bundle.putString("title2",title2);     //转入bundle
        bundle.putString("detail2",detail2);     //转入bundle
        intent.putExtras(bundle);
        startActivity(intent);
    }

}