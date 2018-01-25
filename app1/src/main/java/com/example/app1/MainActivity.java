package com.example.app1;

import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
List<Integer> list = new ArrayList<>();
    private ViewPager vp;
    /*Handler handler = new Handler(){
        int i = 0;
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            vp.setCurrentItem(i);
            i++;
        }
    };*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       add();
        vp = findViewById(R.id.vp);
        vp.setAdapter(new Myacticity());
      /* new Thread(){
           @Override
           public void run() {
               super.run();
              handler.sendEmptyMessageDelayed(1,1000);

           }
       }.start();*/
    }

    public void add() {
        list.add(R.drawable.e);
        list.add(R.drawable.a);
        list.add(R.drawable.b);
        list.add(R.drawable.c);
        list.add(R.drawable.d);
        list.add(R.drawable.f);
        list.add(R.drawable.g);
        list.add(R.drawable.h);
        list.add(R.drawable.i);
        list.add(R.drawable.j);
        list.add(R.drawable.k);
        list.add(R.drawable.aa);
        list.add(R.drawable.bb);
        list.add(R.drawable.cc);
        list.add(R.drawable.dd);
        list.add(R.drawable.q);
        list.add(R.drawable.qq);
        list.add(R.drawable.qqq);
        list.add(R.drawable.qw);
        list.add(R.drawable.w);
        list.add(R.drawable.ww);
        list.add(R.drawable.ew);
        list.add(R.drawable.re);
        list.add(R.drawable.rr);
        list.add(R.drawable.rt);
        list.add(R.drawable.er);
        list.add(R.drawable.tt);
        list.add(R.drawable.aaa);
        list.add(R.drawable.s);
        list.add(R.drawable.ss);
        list.add(R.drawable.sss);
        list.add(R.drawable.z);
        list.add(R.drawable.x);
        list.add(R.drawable.nn);
        list.add(R.drawable.mm);
        list.add(R.drawable.mmm);
        list.add(R.drawable.o);
        list.add(R.drawable.p);
        list.add(R.drawable.ii);
        list.add(R.drawable.jj);
    }

    class Myacticity extends PagerAdapter{


        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = new ImageView(MainActivity.this);
           imageView.setImageResource(list.get(position%list.size()));
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
