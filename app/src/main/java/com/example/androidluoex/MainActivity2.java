package com.example.androidluoex;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener{

    private final int scoreArray[]={3,2,1};
    private TextView tvResult;
    private Button btn1;          //定义按钮
    private Button btn2;
    private Button btn3;
    private Button reset;
    private ImageButton i1;
    private ImageButton i2;
    private int Score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //通过finViewById方法实例化以上控件类
        tvResult = (TextView)findViewById(R.id.tv_result);
        btn1 = (Button)findViewById(R.id.button1);
        btn2 = (Button)findViewById(R.id.button2);
        btn3 = (Button)findViewById(R.id.button3);
        reset = (Button)findViewById(R.id.button4);
        i1 = (ImageButton)findViewById(R.id.i1);
        i2 = (ImageButton)findViewById(R.id.i2);

        //设置按钮点击监听
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        reset.setOnClickListener(this);
        i1.setOnClickListener(this);
        i2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.button1:
                scoreAdd(scoreArray[0]);
                break;
            case R.id.button2:
                scoreAdd(scoreArray[1]);
                break;
            case R.id.button3:
                scoreAdd(scoreArray[2]);
                break;
            case R.id.button4:
                reset();
                break;
            case R.id.i1:
                Intent intent = new Intent();
                intent.setClass(MainActivity2.this,MainActivity.class);
                startActivity(intent);
                break;
            case R.id.i2:
                Intent intent2 = new Intent();
                intent2.setClass(MainActivity2.this,MainActivity3.class);
                startActivity(intent2);
                break;
            default:
        }
    }
    private void reset(){
        Score=0;
        tvResult.setText(Integer.toString(Score));
    }
    private void scoreAdd(int score) {
        Score+=score;
        ShowText();
    }
    private void ShowText(){
        tvResult.setText(Integer.toString(Score));
    }


}