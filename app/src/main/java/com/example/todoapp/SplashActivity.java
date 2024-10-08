package com.example.todoapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    private ImageView logo;
    private static final int SPLASH_DISPLAY_LENGTH = 2000; // Duration of the splash screen in milliseconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        logo = findViewById(R.id.splash_logo);

        // Load and start the animations
        Animation translateAnimation = AnimationUtils.loadAnimation(this, R.anim.translate);
        Animation scaleAnimation = AnimationUtils.loadAnimation(this, R.anim.scale);

        // Combine both animations to play together
        logo.startAnimation(translateAnimation);
        logo.startAnimation(scaleAnimation);

        // Use a Handler to delay the transition to the MainActivity after animations
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
            finish(); // Close the SplashActivity so the user can't return to it
        }, SPLASH_DISPLAY_LENGTH);
    }
}
