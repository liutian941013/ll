package com.example.rikao0002;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rikao0002.bean.Bean;
import com.example.rikao0002.bean.Bean1;
import com.example.rikao0002.dao.Dao;
import com.google.gson.Gson;

public class zcActivity extends AppCompatActivity {
    private EditText et;
    private EditText et1;
    private Button zc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zc);
        et = findViewById(R.id.et);
        et1 = findViewById(R.id.et1);
        zc = findViewById(R.id.zc);
        zc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mobile = et.getText().toString();
                String password = et1.getText().toString();
                Dao dao = new Dao();
                dao.post1("http://120.27.23.105/user/reg", mobile, password);
                dao.setOk(new Dao.OkHttpSet() {
                    @Override
                    public void shibai(String s) {

                    }

                    @Override
                    public void cg(String ss) {
                        Gson gson = new Gson();
                        Bean1 bean1 = gson.fromJson(ss, Bean1.class);
                        String code = bean1.getCode();
                        Integer integer = Integer.valueOf(code);
                        if (integer == 0) {
                            Toast.makeText(zcActivity.this, "注册成功", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(zcActivity.this, MainActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(zcActivity.this, "注册失败,手机号已注册", Toast.LENGTH_LONG).show();
                        }
                    }
                });

            }
        });
    }
}
