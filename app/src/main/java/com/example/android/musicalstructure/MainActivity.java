package com.example.android.musicalstructure;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "PlayLists";
    private final ArrayList<PlayList> playLists = new ArrayList<PlayList>();
    private final ArrayList<Song> allSongs = new ArrayList<Song>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set button active and set others inactive
        ImageButton songsButton = findViewById(R.id.songs_button);
        songsButton.setImageResource(R.drawable.ic_queue_music_black);
        ImageButton playListButton = findViewById(R.id.playlists_button);
        playListButton.setImageResource(R.drawable.ic_playlist_play_active);
        ImageButton searchButton = findViewById(R.id.search_button);
        searchButton.setImageResource(R.drawable.ic_search_black);

        initPlayLists();
        initAllSongs();

        // Initialize the playlist adapter (main screen)
        PlayListAdapter adapter =
                new PlayListAdapter(this, playLists);

        final ListView listView = findViewById(R.id.list);
        listView.setAdapter(adapter);

        // Initializes playlist item listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                PlayList thisPlayList = (PlayList) listView.getItemAtPosition(position);
                int chosenId = thisPlayList.getPlayListId();
                String chosenList = thisPlayList.getPlayListTitle();
                ArrayList<Song> filteredSongs = filterSongsByPlayList(chosenId);

                // Create a new intent to open the {@link SongListActivity}
                Intent songsIntent = new Intent(MainActivity.this, SongListActivity.class);
                Bundle songData = new Bundle();
                songData.putSerializable("theSongs", filteredSongs);
                songsIntent.putExtras(songData);
                songsIntent.putExtra("playlist_title", chosenList);
                startActivity(songsIntent);
            }
        });

        // Initializes the all songs button which opens the all songs view
        songsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link SongListActivity}
                Intent songsIntent = new Intent(MainActivity.this, SongListActivity.class);
                Bundle songData = new Bundle();
                songData.putSerializable("theSongs", allSongs);
                songsIntent.putExtras(songData);
                songsIntent.putExtra("playlist_title", getResources().getString(R.string.all_songs));
                startActivity(songsIntent);
            }
        });

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link SongListActivity}
                Intent searchIntent = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(searchIntent);
            }
        });
    }

    /**
     * Initializes the list of playlists
     */
    private void initPlayLists() {
        playLists.add(new PlayList("Ambient", 1, R.drawable.ambient));
        playLists.add(new PlayList("Blues", 2, R.drawable.blues));
        playLists.add(new PlayList("Dance", 3, R.drawable.dance));
        playLists.add(new PlayList("Electronic", 4, R.drawable.electronic));
        playLists.add(new PlayList("Decades 1970", 5, R.drawable.seventies));
        playLists.add(new PlayList("Decades 1980", 6, R.drawable.eighties));
        playLists.add(new PlayList("Hip-hop", 7, R.drawable.hiphop));
        playLists.add(new PlayList("Indie", 8, R.drawable.indie));
    }

    /**
     * Initializes the list of all songs
     */
    private void initAllSongs() {
        allSongs.add(new Song("Black Sands", "Bonobo", "Black Sands", "2:33", 1));
        allSongs.add(new Song("Guimar", "Aim", "Hinterland", "2:56", 1));
        allSongs.add(new Song("Golden Braid", "Joe Corrales", "Eighty one", "3:10", 1));
        allSongs.add(new Song("Sweet Tides", "Thievery Corporation", "Radio Retaliation", "3:42", 1));
        allSongs.add(new Song("Fast Asleep", "Al Stylus", "Maps from the Wilderness", "4:22", 1));
        allSongs.add(new Song("Black Lake", "Empancipator", "Remixes", "3:56", 1));
        allSongs.add(new Song("You wish", "Nightmares on Was", "In a Space Outta Sound", "4:10", 1));
        allSongs.add(new Song("Recurring", "Bonobo", "Days To Come", "2:45", 2));
        allSongs.add(new Song("Sand People", "Jon Kennedy", "Useless Wooden Toys", "3:39", 2));
        allSongs.add(new Song("Breezes travel with amnesty", "Pirate", "Cave", "4:2", 2));
        allSongs.add(new Song("Carnivores Unite", "Blockhead", "Music by Cavelight", "5:6", 3));
        allSongs.add(new Song("Chipmunk", "Mr. Scruff", "Keep It Unreal", "3:21", 3));
        allSongs.add(new Song("Draw The Stars", "Andreya Triana", "Lost Where I Belong", "5:11", 3));
        allSongs.add(new Song("Rondo Acapricio", "Tosca", "JAC", "4:1", 3));
        allSongs.add(new Song("Polaris", "Zero 7", "Simple Things", "4:33", 3));
        allSongs.add(new Song("Past Is Prologue", "Tycho", "Past Is Prologue", "3:28", 4));
        allSongs.add(new Song("The Secret", "Metaform", "Standing on the Shoulders of Giants", "2:33", 4));
        allSongs.add(new Song("Eyesdown", "Bonobo", "Black Sands", "6:2", 4));
        allSongs.add(new Song("Roygbiv", "Boards Of Canada", "Music Has The Right To Children", "5:3", 4));
        allSongs.add(new Song("Plucker", "Hint", "Portakabin Fever", "3:4", 5));
        allSongs.add(new Song("Myth", "Tor", "Blue Book", "4:5", 5));
        allSongs.add(new Song("Beachy Head", "Mechanical Me", "I Like Mixes", "4:13", 5));
        allSongs.add(new Song("Walking Home Through the Park", "Aim", "Flight 602", "3:44", 5));
        allSongs.add(new Song("Bee's On Mars", "Al Stylus", "Forward", "4:55", 5));
        allSongs.add(new Song("A Bright Day", "Gramatik", "S82", "3:44", 5));
    }

    /**
     * Filters all songs returning just the ones that belong to playListId playlist.
     * @param playListId The playlist id for filtering
     * @return ArrayList<Song>
     */
    private ArrayList<Song> filterSongsByPlayList(int playListId) {
        ArrayList<Song> filteredSongs = new ArrayList<Song>();
        for(int i = 0; i < allSongs.size(); i++) {
            Song currentSong = allSongs.get(i);
            if (currentSong.getSongPlayList() == playListId) {
                filteredSongs.add(currentSong);
            }
        }
        return filteredSongs;
    }
}
