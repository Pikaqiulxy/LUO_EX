package com.example.androidluoex;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity3 extends AppCompatActivity implements View.OnClickListener{

    private final int scoreArray[]={3,2,1,3,2,1};
    private TextView tvResult1;
    private TextView tvResult2;
    private Button btn1,btn2,btn3,btn4,btn5,btn6;          //定义按钮
    private Button reset;
    private ImageButton i1;
    private ImageButton i2;
    private int Score1 = 0,Score2 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        //通过finViewById方法实例化以上控件类
        tvResult1 = (TextView)findViewById(R.id.tv_result1);
        tvResult2 = (TextView)findViewById(R.id.tv_result2);
        btn1 = (Button)findViewById(R.id.L3);
        btn2 = (Button)findViewById(R.id.L2);
        btn3 = (Button)findViewById(R.id.L1);
        btn4 = (Button)findViewById(R.id.R3);
        btn5 = (Button)findViewById(R.id.R2);
        btn6 = (Button)findViewById(R.id.R1);
        reset = (Button)findViewById(R.id.reset);
        i1 = (ImageButton)findViewById(R.id.i1);
        i2 = (ImageButton)findViewById(R.id.i2);

        //设置按钮点击监听
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        reset.setOnClickListener(this);
        i1.setOnClickListener(this);
        i2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.L3:
                score1Add(scoreArray[0]);
                break;
            case R.id.L2:
                score1Add(scoreArray[1]);
                break;
            case R.id.L1:
                score1Add(scoreArray[2]);
                break;
            case R.id.R3:
                score2Add(scoreArray[3]);
                break;
            case R.id.R2:
                score2Add(scoreArray[4]);
                break;
            case R.id.R1:
                score2Add(scoreArray[5]);
                break;
            case R.id.reset:
                reset();
                break;
            case R.id.i1:
                Intent intent = new Intent();
                intent.setClass(MainActivity3.this,MainActivity2.class);
                startActivity(intent);
                break;
            case R.id.i2:
                Intent intent2 = new Intent();
                intent2.setClass(MainActivity3.this,MainActivity4.class);
                startActivity(intent2);
                break;
            default:
        }
    }

    private void reset(){
        Score1=0;
        Score2=0;
        tvResult1.setText(Integer.toString(Score1));
        tvResult2.setText(Integer.toString(Score2));
    }

    private void score1Add(int score1) {
        Score1+=score1;
        ShowText1();
    }

    private void score2Add(int score2) {
        Score2+=score2;
        ShowText2();
    }
    private void ShowText1(){

        tvResult1.setText(Integer.toString(Score1));
    }

    private void ShowText2(){

        tvResult2.setText(Integer.toString(Score2));
    }


}