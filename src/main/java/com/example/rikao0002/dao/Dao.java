package com.example.rikao0002.dao;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by liufan on 2018/1/24.
 */

public class Dao {
    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int what = msg.what;
            switch (what) {
                case 0:
                    String ss = (String) msg.obj;
                    v.shibai(ss);
                    Log.d("萨达萨达萨达", "handleMessage: " + ss);
                    break;
                case 1:
                    String ss1 = (String) msg.obj;
                    v.cg(ss1);
                    Log.d("萨达萨达萨达试试", "handleMessage: " + ss1);
                    break;
            }
        }
    };
    private OkHttpSet v;

    //登录 注册的post方法
    public void post1(String url, String mobile, String password) {
        OkHttpClient okHttpClient = new OkHttpClient();
        FormBody.Builder ff = new FormBody.Builder()
                .add("mobile", mobile + "")
                .add("password", password + "");
        Request build = new Request.Builder().post(ff.build()).url(url).build();
        Call call = okHttpClient.newCall(build);
        call.enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                Message message = new Message();
                message.obj = "失败";
                message.what = 1;
                handler.sendMessage(message);
                Log.d("哈哈哈哈", "onFailure: 失败");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                Message message = new Message();
                message.obj = string;
                message.what = 0;
                handler.sendMessage(message);
            }
        });

    }

    //登录 注册的Get方法
    public void get(String url, String mobile, String password) {
        OkHttpClient okHttpClient = new OkHttpClient();
        FormBody.Builder ff = new FormBody.Builder()
                .add("mobile", mobile + "")
                .add("password", password + "");
        Request build = new Request.Builder().url(url + "?mobile=" + mobile + "&password=" + password).build();
        Call call = okHttpClient.newCall(build);
        call.enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                Message message = new Message();
                message.obj = "失败";
                message.what = 1;
                handler.sendMessage(message);
                Log.d("哈哈哈哈", "onFailure: 失败");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                Message message = new Message();
                message.obj = string;
                message.what = 0;
                handler.sendMessage(message);
            }
        });

    }

    //定义一个接口
    public interface OkHttpSet {
        //定义一个失败的方法
        void shibai(String s);

        //定义一个成功的方法
        void cg(String ss);
    }

    //定义一个外部调用的方法
    public void setOk(OkHttpSet v) {
        this.v = v;
    }

    //上传头像
    public void Up(File file, String url, String uid) {
        OkHttpClient okHttpClient = new OkHttpClient();
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM);
        builder.addFormDataPart("uid", "" + uid);
        builder.addFormDataPart("file", file.getName(), MultipartBody.create(MultipartBody.FORM, file));
        Request builder1 = new Request.Builder().post(builder.build()).url(url).build();
        Call call = okHttpClient.newCall(builder1);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message message = new Message();
                message.obj = "失败";
                message.what = 1;
                handler.sendMessage(message);
                Log.d("哈哈哈哈", "onFailure: 失败");

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String string = response.body().string();
                    Message message = new Message();
                    message.obj = string;
                    message.what = 0;
                    handler.sendMessage(message);
                }
            }
        });

    }

    //下载文件和图片
    public void xz(String sdk, String sdkname, String u) {
        final File file = new File(sdk + "/" + sdkname);
        OkHttpClient okHttpClient = new OkHttpClient();
        Request build = new Request.Builder().url(u).build();
        Call call = okHttpClient.newCall(build);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    InputStream inputStream = response.body().byteStream();
                    OutputStream OutputStream = new FileOutputStream(file);
                    byte[] bytes = new byte[1024];
                    int len=0;
                    int i = 0;
                    while ((len = inputStream.read(bytes)) != -1) {
                        OutputStream.write(bytes, 0, len);
                        i += len;
                        Log.d("咕咕咕咕", "下载: " + i);
                    }
                    OutputStream.flush();
                    OutputStream.close();
                    inputStream.close();
                    if (file.exists()) {
                        Log.d("尴尬", "文件已存在 下载完成");
                        Message message = new Message();
                        message.obj = "完成";
                        message.what = 0;
                        handler.sendMessage(message);
                    } else {
                        Log.d("方法", "文件不存在 ");
                        Message message = new Message();
                        message.obj = "失败";
                        message.what = 1;
                        handler.sendMessage(message);
                    }
                }
            }
        });

    }
}
