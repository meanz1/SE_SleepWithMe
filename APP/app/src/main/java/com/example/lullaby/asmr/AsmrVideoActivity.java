package com.example.lullaby.asmr;
import com.example.lullaby.R;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

public class AsmrVideoActivity extends YouTubeBaseActivity {
    String videoId = "";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asmr_video);

        Intent intent = getIntent();
        videoId = intent.getStringExtra("videoId");

        YouTubePlayerView ypv = findViewById(R.id.youtube_player_view);
        ypv.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(YouTubePlayer youTubePlayer) {
                youTubePlayer.loadVideo(videoId, 0);
            }
        });
    }
}

