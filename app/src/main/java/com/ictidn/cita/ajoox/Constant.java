package com.ictidn.cita.ajoox;

import android.provider.BaseColumns;

/**
 * Created by ajou on 7/14/2016.
 */
public class Constant implements BaseColumns {
    public static final String SONGS = "song";
    public static final String ALBUMS = "album";
    public static final String ARTIST = "artist";

    //Columns in the Events table
    public static final String TIME = "time";

    // Columns in Songs class.
    public static final String TITLE = "title";
    public static final String GENRE = "genre";
    public static final String PATH = "path";
    public static final String ID_ARTIST = "id_artist";
    public static final String ID_ALBUM = "id_album";

    // Columns in Albums class.
    public static final String ALBUMNAME = "album";
    public static final String RELEASE_DATE = "release_date";
    public static final String LABEL = "label";
    public static final String ID_ALBUM_ARTIST = "id_artist";


    // Columns in Artist class
    public static final String ARTIST_NAME = "artist";
    public static final String SEX = "sex";
}
