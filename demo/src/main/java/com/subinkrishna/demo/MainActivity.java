package com.subinkrishna.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.subinkrishna.aspect.AspectRatioFrameLayout;
import com.subinkrishna.aspect.AspectRatioLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final AspectRatioFrameLayout layout = (AspectRatioFrameLayout) findViewById(R.id.layout1);
        layout.postDelayed(new Runnable() {
            @Override public void run() {
                layout.ratio(.5f).lock(AspectRatioLayout.HEIGHT);
            }
        }, 2000);
    }
}
