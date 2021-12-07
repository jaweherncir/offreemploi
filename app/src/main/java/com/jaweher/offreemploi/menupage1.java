package com.jaweher.offreemploi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

import java.util.logging.LogRecord;

public class menupage1 extends AppCompatActivity {
ImageView eljem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menupage1);
        eljem=findViewById(R.id.imgEljem);
      //final   Loding loding=new Loding(menupage1.this);



        eljem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            // loding.Startloading();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent=new Intent(menupage1.this,MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                },5000);

            }

        });
    }


}