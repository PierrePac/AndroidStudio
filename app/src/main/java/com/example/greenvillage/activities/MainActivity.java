package com.example.greenvillage.activities;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.greenvillage.R;


public class MainActivity extends AppCompatActivity {

    private Handler handler = new Handler();
    private int delayMillis = 3000; // 3 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Perform the navigation to another activity
                startActivity(new Intent(MainActivity.this, CategoryActivity.class));
                finish();
            }
        }, delayMillis);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }


}

