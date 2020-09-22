package com.example.androidluoex;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.tabs.TabLayout;

public class MainActivity4 extends AppCompatActivity implements View.OnClickListener{

    private EditText hint;     //定义文本框
    private TextView tvResult;
    private Button DOLLAR,EURO,WON,CONFIG;          //定义按钮
    private float money=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        //通过finViewById方法实例化以上控件类
        hint = (EditText) findViewById(R.id.hint);
        tvResult = (TextView)findViewById(R.id.tv_result);
        DOLLAR = (Button) findViewById(R.id.DOLLAR);
        EURO = (Button)findViewById(R.id.EURO);
        WON = (Button)findViewById(R.id.WON);
        CONFIG = (Button)findViewById(R.id.CONFIG);

        //设置按钮点击监听
        DOLLAR.setOnClickListener(this);
        EURO.setOnClickListener(this);
        WON.setOnClickListener(this);
        CONFIG.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.DOLLAR:
                money=getDOLLAR(rmb());
                setTvResult();
                break;
            case R.id.EURO:
                money=getEURO(rmb());
                setTvResult();
                break;
            case R.id.WON:
                money=getWON(rmb());
                setTvResult();
                break;
            case R.id.CONFIG:

                Intent config = new Intent(MainActivity4.this,MainActivity5.class);
                float m1 = getDOLLAR(rmb());
                float m2 = getEURO(rmb());
                float m3 = getWON(rmb());
                String mo1 = Float.toString(m1);
                String mo2 = Float.toString(m2);
                String mo3 = Float.toString(m3);
                config.putExtra("DOLLAR",mo1);
                config.putExtra("EURO",mo2);
                config.putExtra("WON",mo3);
                startActivity(config);

                break;
            default:
        }
    }

    private float rmb(){
        String text = hint.getText().toString();
        float r = Float.valueOf(text);
        return r;
    }

    private float getDOLLAR(float d){return(d/6.8043f); }
    private float getEURO(float e){return(e/7.9971f); }
    private float getWON(float w){return(w/0.005846f); }

    private void setTvResult(){
        tvResult.setText(Float.toString(money));
    }

}


