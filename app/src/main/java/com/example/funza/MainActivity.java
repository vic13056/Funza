package com.example.funza;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Thread splash = new Thread(){

            public void run(){
                try {
                    sleep(3000);
                    //write an intent to go to the main activity
                    Intent go = new Intent(getApplicationContext(),SignUpActivity.class);
                    startActivity(go);
                    finish();

                }catch (InterruptedException e){

                    e.printStackTrace();
                }
            }
        };
        splash.start();
    }
}
