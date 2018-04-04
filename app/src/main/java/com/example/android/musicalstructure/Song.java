package com.example.android.musicalstructure;

import java.io.Serializable;

/**
 * {@link} Song
 * A song item that the user will play, get details of, include in a playlist or mark as favorite
 */
public class Song implements Serializable {
    private String sTitle;
    private String sArtist;
    private String sAlbum;
    private String sDuration;
    private int sPlayListId;

    Song(String title, String artist, String album, String duration, int playListId) {
        sTitle = title;
        sArtist = artist;
        sAlbum = album;
        sDuration = duration;
        sPlayListId = playListId;
    }

    /**
     * Get the song title
     */
    public String getSongTitle() {
        return sTitle;
    }

    /**
     * Get the song artist
     */
    public String getSongArtist() {
        return sArtist;
    }

    /**
     * Get the song album
     */
    public String getSongAlbum() {
        return sAlbum;
    }

    /**
     * @return {int}
     * Get the playlist id that song belongs to
     */
    public int getSongPlayList() {
        return sPlayListId;
    }

    /**
     * Get the song duration
     * @return String
     */
    public String getSongDuration() {
        return sDuration;
    }
}
