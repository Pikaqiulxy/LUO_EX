package com.example.androidluoex;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity5 extends AppCompatActivity implements View.OnClickListener{

    private static String Hello;
    private static final String TAG = Hello;
    private EditText DOLLARtext,EUROtext,WONtext;
    private Button SAVE;
    private  TextView text_str1, text_str2, text_str3;//定义按钮
    private float mo1,mo2,mo3;

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
        text_str1 = findViewById(R.id.DOLLARtext);
        text_str2 = findViewById(R.id.EUROtext);
        text_str3 = findViewById(R.id.WONtext);
        DOLLARtext.setText(intent.getStringExtra("DOLLAR"));
        EUROtext.setText(intent.getStringExtra("EURO"));
        WONtext.setText(intent.getStringExtra("WON"));
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.SAVE:
                //Intent back4 = new Intent();
                //back4.setClass(MainActivity5.this,MainActivity4.class);

                //到finish为止是setRsult的传值部分
                //保存到Bundle或放入到Extra
                Intent back4 = getIntent();
                Bundle bdl = new Bundle();

                mo1 = Float.parseFloat(text_str1.getText().toString());
                mo2 = Float.parseFloat(text_str2.getText().toString());
                mo3 = Float.parseFloat(text_str3.getText().toString());

                back4.putExtra("DOLLAR",mo1);
                back4.putExtra("EURO",mo2);
                back4.putExtra("WON",mo3);

                back4.putExtras(bdl);
                setResult(666,back4);//设置resultCode及带回的数据
                //返回到调用页面

                finish();

                break;
            default:
        }
    }

}