package com.example.p2p.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.p2p.R;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {
    private TextView tv_version_code;
    private ImageView iv_app_icon;
    private AlphaAnimation aa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initView();
        initData();
        initListener();

    }

    private void initListener() {

        //设置动画播放的监听
        aa.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                
            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    private void initData() {
        aa = new AlphaAnimation(0,1);
        aa.setDuration(666);
        iv_app_icon.startAnimation(aa);

        //在软件欢迎界面停留两秒后跳转进入主界面或者登陆界面
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                if(isLogin()) {
                    startActivity(new Intent(SplashActivity.this,MainActivity.class));
                    finish();
                }else {
                    startActivity(new Intent(SplashActivity.this,LoginActivity.class));
                    finish();
                }
            }
        }, 2000);
        
    }

    //判断是否已经登陆过
    private boolean isLogin() {
        return true;
    }

    private void initView() {
        iv_app_icon = (ImageView)findViewById(R.id.iv_app_icon);
        tv_version_code = (TextView)findViewById(R.id.tv_version_code);
        tv_version_code.setText(getVersionCode());
    }

    private String getVersionCode() {
        try {
            PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            int versionCode = packageInfo.versionCode;
            String versionName = packageInfo.versionName;
            return versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return "1.0";
    }


}
