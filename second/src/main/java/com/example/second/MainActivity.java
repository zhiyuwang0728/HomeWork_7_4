package com.example.second;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mClick;
    private ImageView mImg;
    private TextView mTime;
    int count = 5;
    boolean isFirst = true;
    private Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mClick = (Button) findViewById(R.id.mClick);
        mImg = (ImageView) findViewById(R.id.mImg);
        mTime = (TextView) findViewById(R.id.mTime);

        mClick.setOnClickListener(this);

        ObjectAnimator alpha = ObjectAnimator.ofFloat(mImg, "Alpha", 0, 1, 0, 1, 0, 1, 0, 1);
        ObjectAnimator translationX = ObjectAnimator.ofFloat(mImg, "translationX", 0, 100, 200, 300);
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(mImg, "scaleX", 0, 1, 0, 1, 0, 1);
        ObjectAnimator rotationX = ObjectAnimator.ofFloat(mImg, "rotationX", 0, 360, 720, 1080, 1440);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playSequentially(alpha, translationX, scaleX, rotationX);
        animatorSet.setDuration(5000);
        animatorSet.start();

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        count--;
                        mTime.setText(count+"s");
                        if(count==0&&isFirst==true){
                            startActivity(new Intent(MainActivity.this, HomeActivity.class));
                            finish();
                        }
                    }
                });
            }
        }, 0, 1000);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mClick:
                isFirst = false;
                startActivity(new Intent(MainActivity.this, HomeActivity.class));
                finish();
                break;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        timer.cancel();
        timer=null;
    }
}
