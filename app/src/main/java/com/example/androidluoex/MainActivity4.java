package com.example.androidluoex;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
    private float dollarRate = 6.8043f;
    private float euroRate = 7.9971f;
    private float wonRate = 0.005846f;
    private static String Hel;
    private static final String TAG = Hel;

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
                //float m1 = getDOLLAR(rmb());
                float m1 = dollarRate;
                float m2 = euroRate;
                float m3 = wonRate;
                String mo1 = Float.toString(m1);
                String mo2 = Float.toString(m2);
                String mo3 = Float.toString(m3);
                config.putExtra("DOLLAR",mo1);
                config.putExtra("EURO",mo2);
                config.putExtra("WON",mo3);
                Log.i(TAG, "onActivityResult: dollarRate=" + dollarRate);
                Log.i(TAG, "onActivityResult: euroRate=" + euroRate);
                Log.i(TAG, "onActivityResult: wonRate=" + wonRate);
                //startActivity(config);
                startActivityForResult(config,666);

                break;
            default:
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if(requestCode==666 && resultCode==666){
            Bundle bundle = data.getExtras();
            dollarRate = bundle.getFloat("DOLLAR",0.1f);
            euroRate = bundle.getFloat("EURO",0.1f);
            wonRate = bundle.getFloat("WON",0.1f);
            Log.i(TAG, "onActivityResult: dollarRate=" + dollarRate);
            Log.i(TAG, "onActivityResult: euroRate=" + euroRate);
            Log.i(TAG, "onActivityResult: wonRate=" + wonRate);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private float rmb(){
        String text = hint.getText().toString();
        float r = Float.valueOf(text);
        return r;
    }

    private float getDOLLAR(float d){return(d/dollarRate); }
    private float getEURO(float e){return(e/euroRate); }
    private float getWON(float w){return(w/wonRate); }

    private void setTvResult(){
        tvResult.setText(Float.toString(money));
    }

}


