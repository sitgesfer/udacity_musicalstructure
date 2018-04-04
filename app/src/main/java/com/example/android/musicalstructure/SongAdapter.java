package com.example.android.musicalstructure;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by fernando on 7/03/18.
 */

public class SongAdapter extends ArrayAdapter<Song> {
    SongAdapter(Activity context, ArrayList<Song> songs) {
        super(context, 0, songs);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.song_item, parent, false);
        }

        // Get the {@link AndroidFlavor} object located at this position in the list
        Song currentSong = getItem(position);

        // Find the TextView in the song_item.xml layout with the song title
        TextView titleTextView = (TextView) listItemView.findViewById(R.id.title_text_view);
        titleTextView.setText(currentSong.getSongTitle());

        // Find the TextView in the song_item.xml layout with the artist
        TextView defaultTextView = (TextView) listItemView.findViewById(R.id.artist_text_view);
        defaultTextView.setText(currentSong.getSongArtist());

        ImageView imageView = (ImageView) listItemView.findViewById(R.id.song_cover);
        Context context = imageView.getContext();

        // Choose a cover randomly
        Random r = new Random();
        int coverId = r.nextInt(10) + 1;
        int id = context.getResources().getIdentifier(
                "portada" + coverId,
                "drawable",
                context.getPackageName()
        );
        imageView.setImageResource(id);

        return listItemView;
    }
}
