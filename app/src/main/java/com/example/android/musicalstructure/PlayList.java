package com.example.android.musicalstructure;

public class PlayList {
    private int pUniqueId;
    private String pTitle;
    private int pImageId;

    public PlayList(String title, int id, int imageId) {
        pTitle = title;
        pUniqueId = id;
        pImageId = imageId;
    }

    /**
     * Get the playlist title
     */
    public String getPlayListTitle() {
        return pTitle;
    }

    /**
     * Get playlist id
     */
    public int getPlayListId() {
        return pUniqueId;
    }

    /**
     * Get playlist image
     */
    public int getPlayListImage() {
        return pImageId;
    }
}
