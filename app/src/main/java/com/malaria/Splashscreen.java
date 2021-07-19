package com.malaria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Splashscreen extends AppCompatActivity {
    TextView wel,thks;
    RelativeLayout rel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);
        wel = findViewById(R.id.wel);
        thks = findViewById(R.id.thks);
        rel = findViewById(R.id.rel);

        Animation welanim = AnimationUtils.loadAnimation(this, R.anim.splashtransition);
        Animation relanim = AnimationUtils.loadAnimation(this, R.anim.rel_anim);
        wel.startAnimation(welanim);
        thks.startAnimation(welanim);
        rel.startAnimation(relanim);
        final Intent intent = new Intent(Splashscreen.this, RegisterActivity.class);
        Thread timer = new Thread() {
            public void run() {
                try {
                    sleep(2500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    startActivity(intent);
                    finish();
                }
            }
        };
        timer.start();

        // Making notification bar transparent
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }

        // making notification bar transparent
        changeStatusBarColor();


    }

    //Making Transparent notification bar
    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }
}
