package com.ictidn.cita.ajoox;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import static com.ictidn.cita.ajoox.Constant.*;

import static android.provider.BaseColumns._ID;

/**
 * Created by ajou on 7/14/2016.
 */
public class AjooxData extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "ajoox.db";
    private static final int DATABASE_VERSION = 1;

    private ArrayList<Song> song;
    private ArrayList<Artist> artist;
    private ArrayList<Album> album;

    /**
     * Create a helper object for the Events database
     */
    public AjooxData(Context ctx) {
        super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i("CREATED", "    CREATED");

        db.execSQL("CREATE TABLE IF NOT EXISTS " + ARTIST + " ("
                + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + ARTIST_NAME + " TEXT,"
                + SEX + " TEXT);"
        );

        db.execSQL("CREATE TABLE IF NOT EXISTS " + SONGS + " ("
                + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + TITLE + " TEXT,"
                + GENRE + " TEXT,"
                + PATH + " TEXT,"
                + ID_ARTIST + " INTEGER,"
                + ID_ALBUM + " INTEGER,"
                + "FOREIGN KEY(" + ID_ARTIST + ") REFERENCES " + ARTIST + "(" + _ID + "));"
        );

        db.execSQL("CREATE TABLE IF NOT EXISTS " + ALBUMS + " ("
                + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + ALBUMNAME + " TEXT,"
                + RELEASE_DATE + " DATE,"
                + LABEL + " TEXT,"
                + ID_ALBUM_ARTIST + " INTEGER,"
                + "FOREIGN KEY(" + ID_ALBUM_ARTIST + ") REFERENCES " + ARTIST + "(" + _ID + "));"
        );

        insertSong(songSeeder(), db);
        insertArtist(artistSeeder(), db);
        insertAlbum(albumSeeder(), db);
    }

    //-------------------------------PREPARE DATA AND INSERT---------------------------------

    private ArrayList<Song> songSeeder(){
        song = new ArrayList<Song>();
        song.add(new Song("Fly", "Ballad", "path", 1, 1));
        song.add(new Song("Big Mini World", "Ballad", "path", 1, 1));
        song.add(new Song("Why", "Dance", "path", 2, 2));
        song.add(new Song("Starlight", "Ballad", "path", 2, 2));
        song.add(new Song("Lion Heart", "Dance", "path", 3, 3));
        song.add(new Song("You Think" , "Dance", "path", 3, 3));
        song.add(new Song("PARTY" , "Dance", "path", 3, 3));
        song.add(new Song("RUN" , "Dance", "path", 4, 4));
        song.add(new Song("FIRE" , "Dance", "path", 4, 4));
        song.add(new Song("DOPE" , "Dance", "path", 4, 4));
        return song;
    }

    private ArrayList<Artist> artistSeeder(){
        artist = new ArrayList<Artist>();
        artist.add(new Artist("Jessica","Female"));
        artist.add(new Artist("Taeyeon", "Female"));
        artist.add(new Artist("SNSD", "Female"));
        artist.add(new Artist("BTS", "Male"));
        return artist;
    }

    private ArrayList<Album> albumSeeder() {
        album = new ArrayList<Album>();
        album.add(new Album("With Love, J", "2016", "Coridell Entertaiment", 1));
        album.add(new Album("Why", "2016", "SM Entertaimentt", 2));
        album.add(new Album("Lion Heart", "2016", "SM Entertaiment", 3));
        album.add(new Album("Young Forever", "2016", "Big Hitt", 4));

        return album;
    }

    private void insertSong(ArrayList<Song> song, SQLiteDatabase db){
        ContentValues values = new ContentValues();

        for(Song s: song){
            values.put(TITLE, s.getTitle());
            values.put(GENRE, s.getGenre());
            values.put(PATH, s.getPath());
            values.put(ID_ARTIST, s.getId_artist());
            values.put(ID_ALBUM, s.getId_album());

            db.insert(SONGS, null, values);
        }
    }

    private void insertArtist(ArrayList<Artist> artist, SQLiteDatabase db){
        ContentValues values = new ContentValues();

        for(Artist a: artist){
            values.put(ARTIST_NAME, a.getArtist_name());
            values.put(SEX, a.getSex());

            db.insert(ARTIST, null, values);
        }
    }

    private void insertAlbum(ArrayList<Album> album, SQLiteDatabase db){
        ContentValues values = new ContentValues();

        for(Album a: album){
            values.put(ALBUMNAME, a.getAlbum_name());
            values.put(RELEASE_DATE, a.getRelease_year());
            values.put(LABEL, a.getLabel());
            values.put(ID_ALBUM_ARTIST, a.getId_artist());

            db.insert(ALBUMS, null, values);
        }
    }

    public void drop(){
        this.getReadableDatabase().execSQL("DROP TABLE IF EXISTS " + SONGS);
    }

    //--------------------------------QUERY START HERE-------------------------------------

    public ArrayList<String> getSong(String name) {
        ArrayList<String> listSong = new ArrayList<String>();

        String fetchdata = "select * from " + SONGS + " where " + TITLE + "='" + name + "'";
        if (name.equals("")) {
            fetchdata = "select * from " + SONGS;
        }

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(fetchdata, null);
        if (cursor.moveToFirst()) {
            //Log.d("cursor song", "tidak null");
            do {
                listSong.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }

        return listSong;
    }

    public ArrayList<String> getArtist(String name) {
        ArrayList<String> listArtist = new ArrayList<String>();

        String fetchdata = "select * from " + ARTIST + " where " + ARTIST_NAME + "='" + name + "'";
        if (name.equals("")) {
            fetchdata = "select from " + ARTIST;
        }

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(fetchdata, null);
        if (cursor.moveToFirst()) {
            //Log.d("cursor song", "tidak null");
            do {
                listArtist.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }

        return listArtist;
    }

    public ArrayList<String> getAlbum(String name) {
        ArrayList<String> listAlbum = new ArrayList<String>();

        String fetchdata = "select * from " + ALBUMS + " where " + ALBUMNAME + "='" + name + "'";
        if (name.equals("")) {
            fetchdata = "select * from " + ALBUMS;
        }

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(fetchdata, null);
        if (cursor.moveToFirst()) {
            //Log.d("cursor song", "tidak null");
            do {
                listAlbum.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }

        return listAlbum;
    }

    public ArrayList<String> getGenre(String name){
        ArrayList<String> listGenre = new ArrayList<String>();

        String fetchdata = "select * from " + SONGS + " where " + GENRE + "='" + name + "'";
        if (name.equals("")) {
            fetchdata = "select distinct "+ GENRE +" from " + SONGS;
        }

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(fetchdata, null);
        if (cursor.moveToFirst()) {
            //Log.d("cursor song", "tidak null");
            do {
                listGenre.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        return listGenre;
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS " + SONGS);
        onCreate(db);
    }

}
