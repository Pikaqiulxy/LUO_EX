package com.example.androidluoex;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Window;

public class MainActivity0 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main0);
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
    }
}