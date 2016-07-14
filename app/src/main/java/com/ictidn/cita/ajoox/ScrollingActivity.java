package com.ictidn.cita.ajoox;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;
import static com.ictidn.cita.ajoox.Constant.*;

public class ScrollingActivity extends AppCompatActivity {

    private static String[] FROM = { _ID, TITLE, GENRE, );
    private static String ORDER_BY = TITLE + " ASC";

    private AjooxData data;
    private ArrayList<Song> song;
    private ArrayList<Artist> artist;
    private ArrayList<Album> album;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Typeface faceDea = Typeface.createFromAsset(getAssets(), "fonts/deathstar.otf");
        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        collapsingToolbar.setCollapsedTitleTypeface(faceDea);
        collapsingToolbar.setExpandedTitleTypeface(faceDea);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Playing Music", Snackbar.LENGTH_LONG)
                //        .setAction("Action", null).show();
                Intent i = new Intent(ScrollingActivity.this, Player.class);
                startActivity(i);
                finish();
            }
        });

        LinearLayout artist = (LinearLayout) findViewById(R.id.layoutArtist);
        artist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ScrollingActivity.this, GridActivity.class);
                startActivity(i);
                finish();
            }
        });

        LinearLayout album = (LinearLayout) findViewById(R.id.layoutAlbum);
        album.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ScrollingActivity.this, GridActivity.class);
                startActivity(i);
                finish();
            }
        });

        LinearLayout genre = (LinearLayout) findViewById(R.id.layoutGenre);
        genre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ScrollingActivity.this, GridActivity.class);
                startActivity(i);
                finish();
            }
        });

        LinearLayout country = (LinearLayout) findViewById(R.id.layoutCountry);
        country.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ScrollingActivity.this, GridActivity.class);
                startActivity(i);
                finish();
            }
        });

        Button allsong = (Button) findViewById(R.id.allsongs);
        allsong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ScrollingActivity.this, ListActivity.class);
                startActivity(i);
                finish();
            }
        });

        GridView gridview = (GridView) findViewById(R.id.gridView);
        gridview.setAdapter(new ImageAdapter(this));

        data = new AjooxData(this);

        try{
            Cursor cursor = getSongsList)();
            showEvents(cursor);
        } finally {
            data.close();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void searchBy(View v) {
        switch (v.getId()) {
            case (R.id.imageView1):
                Intent i1 = new Intent(ScrollingActivity.this, GridActivity.class);
                startActivity(i1);
                finish();
                break;
            case (R.id.imageView2):
                Intent i2 = new Intent(ScrollingActivity.this, GridActivity.class);
                startActivity(i2);
                finish();
                break;
            case (R.id.imageView3):
                Intent i3 = new Intent(ScrollingActivity.this, GridActivity.class);
                startActivity(i3);
                finish();
                break;
            case (R.id.imageView4):
                Intent i4 = new Intent(ScrollingActivity.this, GridActivity.class);
                startActivity(i4);
                finish();
                break;
        }
    }

    private void addSong(ArrayList<Song> song){
        SQLiteDatabase db = data.getWritableDatabase();
        ContentValues values = new ContentValues();

        for(Song s: song){
            values.put(TITLE, s.getTitle());
            values.put(GENRE, s.getGenre());
            values.put(PATH, s.getPath());
            values.put(ID_ARTIST, s.getId_artist());
            values.put(ID_ALBUM, s.getId_album());

            db.insertOrThrow(SONGS, null, values);
        }
    }

    private Cursor getSongsList(){
        SQLiteDatabase db = data.getReadableDatabase();
        Cursor cursor = db.query(SONGS, FROM, null, null, null, null, ORDER_BY);
        startManagingCursor(cursor);
        return cursor;

    }

    private void showEvents(Cursor cursor){
        StringBuilder builder = new StringBuilder("Song List:\n");

        while (cursor.moveToNext()){
            String title = cursor.getString(1);
            String album = cursor.getString(2);

            builder.append(title).append("- ");
            builder.append(album).append("\n");
        }



    }

    private

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

    private ArrayList<Album> albumSeeder(){
        album = new ArrayList<Album>();
        album.add(new Album("With Love, J", "2016", "Coridell Entertaiment", 1));
        album.add(new Album("Why", "2016", "SM Entertaimentt", 2));
        album.add(new Album("Lion Heart", "2016", "SM Entertaiment", 3));
        album.add(new Album("Young Forever", "2016", "Big Hitt", 4));

        return album;

    public void drop(){
        data.getReadableDatabase().execSQL("DROP TABLE IF EXISTS " + SONGS);
    }
}
