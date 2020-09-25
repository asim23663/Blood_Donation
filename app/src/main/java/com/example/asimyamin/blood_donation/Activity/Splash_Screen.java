package com.example.asimyamin.blood_donation.Activity;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.asimyamin.blood_donation.R;

public class Splash_Screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash__sccreen);

        Thread thread=new Thread(){
            @Override
            public void run() {
                try {
                    sleep(5000);
                    startActivity(new Intent(Splash_Screen.this,MainActivity.class));
                    finish();

                } catch (InterruptedException e) {
                    Toast.makeText(Splash_Screen.this,"Unkown error is occured!!",Toast.LENGTH_SHORT).show();
                }

            }
        };
        thread.start();
    }
}
