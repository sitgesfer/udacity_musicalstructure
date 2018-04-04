package com.example.android.musicalstructure;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class SongListActivity extends AppCompatActivity {
    private static ArrayList<Song> allSongs;
    private static String thisTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        // Set button active and set others inactive
        ImageButton songsButton = findViewById(R.id.songs_button);
        songsButton.setImageResource(R.drawable.ic_queue_music_active);
        ImageButton playListButton = findViewById(R.id.playlists_button);
        playListButton.setImageResource(R.drawable.ic_playlist_play_black);
        ImageButton searchButton = findViewById(R.id.search_button);
        searchButton.setImageResource(R.drawable.ic_search_black);

        Bundle extras = getIntent().getExtras();
        if (extras != null && getIntent().hasExtra("playlist_title")) {
            thisTitle = extras.getString("playlist_title");
            allSongs = (ArrayList<Song>) getIntent().getSerializableExtra("theSongs");
        } else if (savedInstanceState != null) {
            thisTitle = savedInstanceState.getString("thisTitle");
            allSongs = (ArrayList<Song>) savedInstanceState.getSerializable("theSongs");
        }

        setTitle(thisTitle);

        if (allSongs.size() > 0) {
            SongAdapter adapter = new SongAdapter(this, allSongs);

            final ListView listView = (ListView) findViewById(R.id.list);
            listView.setAdapter(adapter);

            // Initializes playlist item listener
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                    Song chosenSong = (Song) listView.getItemAtPosition(position);

                    // Create a new intent to open the {@link SongListActivity}
                    Intent songsIntent = new Intent(SongListActivity.this, NowPlayingActivity.class);
                    Bundle songData = new Bundle();
                    songData.putSerializable("chosenSong", chosenSong);
                    songsIntent.putExtras(songData);
                    songsIntent.putExtra("chosenSong", chosenSong);
                    startActivity(songsIntent);
                }
            });
        } else {
            Context context = getApplicationContext();
            CharSequence text = getString(R.string.empty_playlist);
            int duration = Toast.LENGTH_LONG;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }

        playListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link SongListActivity}
                Intent searchIntent = new Intent(SongListActivity.this, SearchActivity.class);
                startActivity(searchIntent);
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle savingState) {
        savingState.putSerializable("theSongs", allSongs);
        savingState.putString("thisTitle", thisTitle);
        super.onSaveInstanceState(savingState);
    }
}
