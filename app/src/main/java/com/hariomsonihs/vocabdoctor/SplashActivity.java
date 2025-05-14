package com.hariomsonihs.vocabdoctor;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_DURATION = 2000; // 3 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);

        // Fullscreen immersive mode
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );

        // Load animations
        ImageView logo = findViewById(R.id.splash_logo);
        TextView appName = findViewById(R.id.splash_app_name);
        TextView tagline = findViewById(R.id.splash_tagline);

        logo.startAnimation(AnimationUtils.loadAnimation(this, R.anim.zoom_in));
        appName.startAnimation(AnimationUtils.loadAnimation(this, R.anim.fade_in_slow));
        tagline.startAnimation(AnimationUtils.loadAnimation(this, R.anim.fade_in_slower));

        // After delay, go to MainActivity
        new Handler().postDelayed(() -> {
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
            overridePendingTransition(R.anim.fade_in_slow, R.anim.fade_out);
            finish();
        }, SPLASH_DURATION);
    }
}
