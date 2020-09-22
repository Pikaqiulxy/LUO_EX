package com.example.androidluoex;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity5 extends AppCompatActivity implements View.OnClickListener{

    private EditText DOLLARtext,EUROtext,WONtext;
    private Button SAVE;          //定义按钮

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        //通过finViewById方法实例化以上控件类
        DOLLARtext = (EditText) findViewById(R.id.DOLLARtext);
        EUROtext = (EditText) findViewById(R.id.EUROtext);
        WONtext = (EditText) findViewById(R.id.WONtext);
        SAVE = (Button)findViewById(R.id.SAVE);

        //设置按钮点击监听
        SAVE.setOnClickListener(this);

        //显示
        Intent intent = getIntent();
        TextView text_str1 = findViewById(R.id.DOLLARtext);
        TextView text_str2 = findViewById(R.id.EUROtext);
        TextView text_str3 = findViewById(R.id.WONtext);
        DOLLARtext.setText(intent.getStringExtra("DOLLAR"));
        EUROtext.setText(intent.getStringExtra("EURO"));
        WONtext.setText(intent.getStringExtra("WON"));
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.SAVE:
                Intent back4 = new Intent();
                back4.setClass(MainActivity5.this,MainActivity4.class);
                //startActivityForResult(intent.code);
                break;
        }
    }

}