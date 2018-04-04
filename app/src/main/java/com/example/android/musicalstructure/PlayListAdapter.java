package com.example.android.musicalstructure;

import android.app.Activity;
        import android.support.annotation.NonNull;
        import android.support.annotation.Nullable;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ArrayAdapter;
        import android.widget.ImageView;
        import android.widget.TextView;

        import java.util.ArrayList;

/**
 * Created by fernando on 7/03/18.
 */

public class PlayListAdapter extends ArrayAdapter<PlayList> {
    PlayListAdapter(Activity context, ArrayList<PlayList> playLists) {
        super(context, 0, playLists);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.playlist_item, parent, false);
        }

        // Get the {@link AndroidFlavor} object located at this position in the list
        PlayList currentPlayList = getItem(position);

        // Find the TextView in the playlist_item.xml layout with the playlist title
        TextView titleTextView = (TextView) listItemView.findViewById(R.id.title_text_view);
        // Set this text on the titleTextView
        titleTextView.setText(currentPlayList.getPlayListTitle());
        // Set the image background
        ImageView backgroundImage = (ImageView) listItemView.findViewById(R.id.playlist_image);
        backgroundImage.setImageResource(currentPlayList.getPlayListImage());

        // Return the whole list item layout
        return listItemView;
    }
}
