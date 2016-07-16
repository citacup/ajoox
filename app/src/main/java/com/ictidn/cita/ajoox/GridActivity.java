package com.ictidn.cita.ajoox;

/**
 * Created by ajou on 7/14/2016.
 */
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class GridActivity extends Activity {

    AjooxData data;
    String type;
    String state;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grid_layout);

        data = new AjooxData(getApplicationContext());
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            if(extras.getString("button")!=null){
                type = extras.getString("button");
                //The key argument here must match that used in the other activity
            }
        }

        TextView title = (TextView) findViewById(R.id.toolbar_title);
        Typeface faceDea = Typeface.createFromAsset(getAssets(), "fonts/deathstar.otf");
        title.setTypeface(faceDea);

        GridView gridview = (GridView) findViewById(R.id.gridview);
        TextView typeText = (TextView) findViewById(R.id.type);
        if(type!=null) {
            switch (type) {
                case "artist":
                    typeText.setText("All Artist");
                    gridview.setAdapter(new ImageAdapter(this, data.getArtist(""), R.drawable.artist));
                    state = "Artist";
                    break;
                case "album":
                    typeText.setText("All Album");
                    gridview.setAdapter(new ImageAdapter(this, data.getAlbum(""), R.drawable.folder));
                    state = "Album";
                    break;
                case "genre":
                    typeText.setText("All Genre");
                    gridview.setAdapter(new ImageAdapter(this, data.getGenre(""), R.drawable.sound_icon));
                    state = "Genre";
                    break;
                case "year":
                    typeText.setText("All Year");
                    gridview.setAdapter(new ImageAdapter(this, data.getReleaseYear(""), R.drawable.sound_icon));
                    state = "Year";
                    break;
                case "query":
                    String search_state = extras.getString("state");
                    String keyword = extras.getString("keyword");
                    typeText.setText("Search " + search_state + " by Keyword "+keyword);
                    ArrayList<String> data_search = new ArrayList<String>();
                    if(search_state.equalsIgnoreCase("Artist")){
                        data_search = data.searchByArtist(keyword);
                        gridview.setAdapter(new ImageAdapter(this, data_search, R.drawable.artist));
                        state = "Artist";
                        type = "artist";
                    }
                    else if(search_state.equalsIgnoreCase("Album")){
                        data_search = data.searchByAlbum(keyword);
                        gridview.setAdapter(new ImageAdapter(this, data_search, R.drawable.folder));
                        state = "Album";
                        type = "album";
                    }
                    else if(search_state.equalsIgnoreCase("Genre")){
                        data_search = data.searchByGenre(keyword);
                        gridview.setAdapter(new ImageAdapter(this, data_search, R.drawable.sound_icon));
                        state = "Genre";
                        type = "genre";
                    }
                    else{
                        data_search = data.searchByYear(keyword);
                        gridview.setAdapter(new ImageAdapter(this, data_search, R.drawable.sound_icon));
                        state = "Year";
                        type = "year";
                    }
                    break;
            }
        }
        else{
            Toast.makeText(getApplicationContext(),"NULL",Toast.LENGTH_SHORT).show();
        }
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
              //  String item = adapterView.getItemAtPosition(i).toString();
                TextView t = (TextView) adapterView.findViewById(R.id.txt_grid);
                //Toast.makeText(getApplicationContext(),""+data.getArtistById(i),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(GridActivity.this, ListActivity.class);
                intent.putExtra("id",i);
                switch (type){
                    case "artist":
                        intent.putExtra("button", "artist");
                        intent.putExtra("name", data.getArtistById(i));
                        break;

                    case "album":
                        intent.putExtra("button", "album");
                        intent.putExtra("name", data.getAlbumById(i));
                        break;

                    case "genre":
                        intent.putExtra("button", "genre");
                        intent.putExtra("name", data.getGenre("").get(i));
                        break;

                    case "year":
                        intent.putExtra("button", "year");
                        intent.putExtra("name", data.getReleaseYear("").get(i));
                        break;

                }

                GridActivity.this.startActivity(intent);
                finish();
            }
        });

        final SearchView searchView = (SearchView) findViewById(R.id.searchView);
        searchView.setQueryHint("Search Song");

        searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                //  Toast.makeText(getBaseContext(), searchView.getQuery().toString(),
                //          Toast.LENGTH_SHORT).show();
                Intent i = new Intent(GridActivity.this, GridActivity.class);
                i.putExtra("button", "query");
                i.putExtra("keyword", searchView.getQuery().toString());
                i.putExtra("state", state);
                startActivity(i);
                finish();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

    }

    @Override
    public void onBackPressed()
    {
        Intent i = new Intent(GridActivity.this, ScrollingActivity.class);
        startActivity(i);
        finish();
    }

}