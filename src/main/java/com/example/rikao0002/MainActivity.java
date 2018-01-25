package com.example.rikao0002;

import android.content.Intent;
import android.os.Environment;
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

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private EditText et;
    private EditText et1;
    private Button login;
    private Button zc;
    private Button sc;
    private Button xz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et = findViewById(R.id.et);
        et1 = findViewById(R.id.et1);
        login = findViewById(R.id.login);
        //登录
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mobile = et.getText().toString();
                String password = et1.getText().toString();
                Dao dao = new Dao();
                dao.post1("http://120.27.23.105/user/login", mobile, password);
                dao.setOk(new Dao.OkHttpSet() {
                    @Override
                    public void shibai(String s) {
                        Toast.makeText(MainActivity.this, s, Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void cg(String ss) {
                        Gson gson = new Gson();
                        Bean bean = gson.fromJson(ss, Bean.class);
                        String code = bean.getCode();
                        Log.d("咕咕咕咕过", "cg: " + code);
                        Toast.makeText(MainActivity.this, ss + "", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
        zc = findViewById(R.id.zc);
        //注册
        zc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, zcActivity.class);
                startActivity(intent);
            }
        });
        //上传
        sc = findViewById(R.id.sc);
        sc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.zhaoapi.cn/file/upload";
                String uid = "11972";
                String file = Environment.getExternalStorageDirectory().getParent() + "/" + "q.ipg";
                File file1 = new File(file);
                Dao dao = new Dao();
                dao.Up(file1, url, uid);
                dao.setOk(new Dao.OkHttpSet() {
                    @Override
                    public void shibai(String s) {
                        Toast.makeText(MainActivity.this, "上传失败", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void cg(String ss) {
                        Toast.makeText(MainActivity.this, "上传成功", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
        //下载
        xz = findViewById(R.id.xz);
        xz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url="http://img.tuku.cn/file_big/201504/772d5eb2a5ce45b59fa4998c41e51c99.jpg";
                String sdk=Environment.getExternalStorageDirectory().getPath();
                String sdkname="772d5eb2a5ce45b59fa4998c41e51c99.jpg";
                Dao dao = new Dao();
                dao.xz(sdk,sdkname,url);
                dao.setOk(new Dao.OkHttpSet() {
                    @Override
                    public void shibai(String s) {
                        Toast.makeText(MainActivity.this,"下载"+s,Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void cg(String ss) {
                       Toast.makeText(MainActivity.this,"下载"+ss,Toast.LENGTH_LONG).show();
                    }
                });

            }
        });
    }


}
