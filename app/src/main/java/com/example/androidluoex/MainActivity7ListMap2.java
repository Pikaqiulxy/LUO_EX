package com.example.androidluoex;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity7ListMap2 extends AppCompatActivity {

    private TextView textView,textView2;
    private EditText hint;     //定义文本框

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity7_list_map2);

        hint = (EditText) findViewById(R.id.hint);
        textView = (TextView)findViewById(R.id.textView);
        textView2 = (TextView)findViewById(R.id.textView2);

        final Bundle bundle=getIntent().getExtras();    //接收Extras
        String h=bundle.getString("title2");
        String count=bundle.getString("detail2");
        hint.setText(h);  //显示传递值

    }
}