package com.example.asus.internship.controller;

import android.graphics.drawable.AnimationDrawable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.asus.internship.R;

public class DetailRepos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_repos);
        getIncomingIntent();
        ConstraintLayout constraintLayout =findViewById(R.id.layout);
        AnimationDrawable animationDrawable =(AnimationDrawable) constraintLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //show the activity in full screen
    }

    private void getIncomingIntent() {


        if (getIntent().hasExtra("description")&&getIntent().hasExtra("title")) {


            String description = getIntent().getStringExtra("description");
            String title = getIntent().getStringExtra("title");


            setText(title, description);
        }
    }

    private void setText(String title, String description) {


        TextView name = findViewById(R.id.image_description);
        name.setText(description);
        TextView desc = findViewById(R.id.image_title);
        desc.setText(title);


    }
}