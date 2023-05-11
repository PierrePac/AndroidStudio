package com.example.greenvillage.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.greenvillage.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Splash fade_out animation
            // Get the ImageView form the Layout
            ImageView splashImage = findViewById(R.id.splash_image);

            // Load the fade_out animation from the anim directory
            Animation fadeOut = AnimationUtils.loadAnimation(this, R.anim.fade_out);

            //Set the animation listener to remove the ImageView when the animation end
            fadeOut.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    splashImage.setVisibility(View.GONE);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                }
            });

            //Start the animation
            splashImage.startAnimation(fadeOut);


    }
}