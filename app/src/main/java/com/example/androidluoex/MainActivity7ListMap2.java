package com.example.androidluoex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.List;
import java.util.regex.Pattern;

public class MainActivity7ListMap2 extends AppCompatActivity{

    private static final String TAG = "Activity7_2";

    float rate;
    float inputCNY;
    float result;
    String moneyName;

    TextView MoneyName;
    TextView Transfer;
    EditText InputCNY;

    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity7_list_map2);

        final Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        final DecimalFormat decimalFormat = new DecimalFormat(".00");
        //format 返回的是字符串

        moneyName = bundle.getString("title2");
        rate = 100/Float.parseFloat(bundle.getString("detail2"));

        MoneyName = findViewById(R.id.textView);
        InputCNY = findViewById(R.id.hint);
        Transfer = findViewById(R.id.textView2);

        MoneyName.setText(moneyName);

        //https://blog.csdn.net/a249130/article/details/79958023
        //输入框实时监听
        InputCNY.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Log.e(TAG, "输入文字中的状态，count是输入字符数");
                Log.e(TAG, String.valueOf(InputCNY.getText()));
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if ( !String.valueOf(InputCNY.getText()).equals("")
                        && isDouble(String.valueOf(InputCNY.getText()))) {
                    inputCNY = Float.parseFloat(String.valueOf(InputCNY.getText()));
                    result = inputCNY * rate;
                    Transfer.setText(decimalFormat.format(result)+"");
                } else {
                    Transfer.setText("");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!String.valueOf(InputCNY.getText()).equals("")) {
                    inputCNY = Float.parseFloat(String.valueOf(InputCNY.getText()));
                    result = inputCNY * rate;
                    Transfer.setText(decimalFormat.format(result)+"");
                } else {
                    Transfer.setText("");
                }
            }
        });

    }

    public boolean isDouble(String str) {
        if (null == str || "".equals(str)) {
            return false;
        }
        Pattern pattern = Pattern.compile("^[-\\+]?[.\\d]*$");
        return pattern.matcher(str).matches();
    }

}