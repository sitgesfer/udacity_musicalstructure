package com.example.android.musicalstructure;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class NowPlayingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_now_playing);
        Bundle extras = getIntent().getExtras();
        Song chosenSong = (Song) getIntent().getSerializableExtra("chosenSong");
        // Set the song details on each text view
        TextView songTitle = (TextView) findViewById(R.id.song_title);
        songTitle.setText(chosenSong.getSongTitle());
        TextView songArtist = (TextView) findViewById(R.id.song_artist);
        songArtist.setText(chosenSong.getSongArtist());
        TextView songAlbum = (TextView) findViewById(R.id.song_album);
        songAlbum.setText(chosenSong.getSongAlbum());
        TextView songDuration = (TextView) findViewById(R.id.song_duration);
        songDuration.setText(chosenSong.getSongDuration());

        // Choose a cover randomly
        ImageView songCover = (ImageView) findViewById(R.id.song_cover);
        Context context = songCover.getContext();
        Random r = new Random();
        int coverId = r.nextInt(10) + 1;
        int sCoverId = getResources().getIdentifier("portada" + coverId, "drawable", context.getPackageName());
        songCover.setImageResource(sCoverId);
    }
}
