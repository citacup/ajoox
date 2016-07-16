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
import android.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;
import static com.ictidn.cita.ajoox.Constant.*;

public class ScrollingActivity extends AppCompatActivity {

    private AjooxData data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        data = new AjooxData(getApplicationContext());

        final SearchView searchView = (SearchView) findViewById(R.id.searchView);
        searchView.setQueryHint("Search Song");

        searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                Toast.makeText(getBaseContext(), searchView.getQuery().toString(),
                        Toast.LENGTH_SHORT).show();
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
              //  Toast.makeText(getBaseContext(), searchView.getQuery().toString(),
              //          Toast.LENGTH_SHORT).show();
                Intent i = new Intent(ScrollingActivity.this, ListActivity.class);
                i.putExtra("button", "query");
                i.putExtra("keyword", searchView.getQuery().toString());
                startActivity(i);
                finish();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
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
                i.putExtra("button","artist");
                startActivity(i);
                finish();
            }
        });

        LinearLayout album = (LinearLayout) findViewById(R.id.layoutAlbum);
        album.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ScrollingActivity.this, GridActivity.class);
                i.putExtra("button","album");
                startActivity(i);
                finish();
            }
        });

        LinearLayout genre = (LinearLayout) findViewById(R.id.layoutGenre);
        genre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ScrollingActivity.this, GridActivity.class);
                i.putExtra("button","genre");
                startActivity(i);
                finish();
            }
        });

        LinearLayout country = (LinearLayout) findViewById(R.id.layoutCountry);
        country.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ScrollingActivity.this, GridActivity.class);
                i.putExtra("button","year");
                startActivity(i);
                finish();
            }
        });

        final Button all_song_button = (Button) findViewById(R.id.allsongs);
        all_song_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ScrollingActivity.this, ListActivity.class);
                i.putExtra("button","allsongs");
                ScrollingActivity.this.startActivity(i);
                finish();
            }
        });

        GridView gridview = (GridView) findViewById(R.id.gridView);
        gridview.setAdapter(new ImageAdapter(this, data.getSong(""), R.drawable.sound_icon));

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

}
