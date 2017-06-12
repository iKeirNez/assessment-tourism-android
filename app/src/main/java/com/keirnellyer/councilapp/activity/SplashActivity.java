package com.keirnellyer.councilapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.keirnellyer.councilapp.R;

public class SplashActivity extends AppCompatActivity implements Animation.AnimationListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        final Animation animation = AnimationUtils.loadAnimation(this, R.anim.splash_icon_spinner);
        animation.setAnimationListener(this);
        findViewById(R.id.splash_icon).startAnimation(animation);
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);

        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        supportFinishAfterTransition();
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
