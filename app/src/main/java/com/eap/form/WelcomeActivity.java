package com.eap.form;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class WelcomeActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 5000;
    Animation animZoomIn;
    TextView txtZoomIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        txtZoomIn=(TextView)findViewById(R.id.welcome_title);
        animZoomIn = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.zoom_in);
        txtZoomIn.startAnimation(animZoomIn);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Start home activity
                Intent intent = new Intent(WelcomeActivity.this, HomeActivity.class);
                startActivity(intent);
                // close splash activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
