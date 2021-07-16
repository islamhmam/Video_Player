package com.example.sgplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.halilibo.bvpkotlin.BetterVideoPlayer;

public class DisplayActivity extends AppCompatActivity {
BetterVideoPlayer betterVideoPlayer;


    String filePath;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_display);
        betterVideoPlayer=findViewById(R.id.videoPlayer);

        filePath=getIntent().getStringExtra("videoPath");

        Uri videoUri=Uri.parse(filePath);

        betterVideoPlayer.setSource(videoUri);

    }

    @Override
    protected void onPause() {
        super.onPause();
        betterVideoPlayer.pause();
    }
}