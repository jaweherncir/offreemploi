package com.jaweher.offreemploi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashSreen extends AppCompatActivity {
//variable
    private static int SPLASH_SCREEN=6000;
    Animation topAnim,BottomAnim;
    ImageView imge;
    TextView logo,slogo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_sreen);
//Animations
        topAnim= AnimationUtils.loadAnimation(this,R.anim.top_animation);

        BottomAnim= AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

//find
        imge=findViewById(R.id.logoSplash);
        logo=findViewById(R.id.txtLogo);
        slogo=findViewById(R.id.txtSlogo);
        imge.setAnimation(topAnim);
        logo.setAnimation(BottomAnim);
        slogo.setAnimation(BottomAnim);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(SplashSreen.this,menupage1.class);
                startActivity(intent);
                finish();
            }
        },SPLASH_SCREEN);
    }
}