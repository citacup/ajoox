package com.ictidn.cita.ajoox;

/**
 * Created by ajou on 7/14/2016.
 */
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListActivity extends Activity {

    private ListView lv;
    // Listview Adapter
    ArrayAdapter<String> adapter;
    AjooxData data;
    String type;
    String state;
    String state_value;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_layout);

        data = new AjooxData(getApplicationContext());
        final Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Log.w("Artist: ",""+extras.getString("artist_name"));
            if(extras.getString("button")==null){
            }
            else
            {
                String value = extras.getString("button");
                //The key argument here must match that used in the other activity
                type = value;
            }

        }

        TextView title = (TextView) findViewById(R.id.toolbar_title);
        Typeface faceDea = Typeface.createFromAsset(getAssets(), "fonts/deathstar.otf");
        title.setTypeface(faceDea);


        lv = (ListView) findViewById(R.id.list_view);
        TextView title_listview = (TextView) findViewById(R.id.title_listview);
        ArrayList<String> list_data_on_view = new ArrayList<String>();
        switch (type) {
            case "artist":
                title_listview.setText("BY ARTIST: "+extras.getString("name"));
                list_data_on_view = data.getSongByArtist(extras.getString("name"));
                adapter = new ArrayAdapter<String>(this, R.layout.list_item, R.id.product_name, list_data_on_view);
                state = "artist";
                state_value = extras.getString("name");
                break;
            case "album":
                title_listview.setText("BY ALBUM: "+extras.getString("name"));
                list_data_on_view = data.getSongByArtist(extras.getString("name"));
                adapter = new ArrayAdapter<String>(this, R.layout.list_item, R.id.product_name, data.getSongByAlbum(extras.getString("name")));
                state = "album";
                state_value = extras.getString("name");
                break;
            case "genre":
                title_listview.setText("BY GENRE: "+extras.getString("name"));
                adapter = new ArrayAdapter<String>(this, R.layout.list_item, R.id.product_name, data.getSongByGenre(extras.getString("name")));
                state = "genre";
                state_value = extras.getString("name");
                break;
            case "year":
                title_listview.setText("YEAR RELEASED: "+extras.getString("name"));
                adapter = new ArrayAdapter<String>(this, R.layout.list_item, R.id.product_name, data.getSongByYear(extras.getString("name")));
                state = "year";
                state_value = extras.getString("name");
                break;

            case "allsongs":
                title_listview.setText("All Songs");
                adapter = new ArrayAdapter<String>(this, R.layout.list_item, R.id.product_name, data.getSong(""));
                break;
            case "query":
                title_listview.setText("All songs with keywords " + extras.getString("keyword") );
                ArrayList<String> data_search = new ArrayList<String>();
                if(extras.getString("state")!=null){
                    title_listview.setText("All "+extras.getString("state_value")+"'s songs with keywords " + extras.getString("keyword") );
                    if(extras.getString("state").equalsIgnoreCase("artist")){
                        data_search = data.searchByArtist(extras.getString("keyword"),extras.getString("state_value"));
                    }
                    else if(extras.getString("state").equalsIgnoreCase("album")){
                        data_search = data.searchByAlbum(extras.getString("keyword"),extras.getString("state_value"));
                    }
                    else if(extras.getString("state").equalsIgnoreCase("genre")){
                        data_search = data.searchByGenre(extras.getString("keyword"),extras.getString("state_value"));
                    }
                    else{
                        data_search = data.searchByYear(extras.getString("keyword"),extras.getString("state_value"));
                    }
                }
                else
                {
                    data_search = data.searchBySong(extras.getString("keyword"));
                }
                adapter= new ArrayAdapter<String>(this, R.layout.list_item, R.id.product_name, data_search);
                break;
        }
        // Adding items to listview
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Toast.makeText(getApplicationContext(),adapter.getItem(i),Toast.LENGTH_SHORT).show();
                Intent j = new Intent(ListActivity.this, Player.class);
                j.putExtra("title",adapter.getItem(i));
                j.putExtra("album", data.getAlbumBySong(adapter.getItem(i)).get(0));
                j.putExtra("artist", data.getArtistBySong(adapter.getItem(i)).get(0));
                j.putExtra("path", data.getPathBySong(adapter.getItem(i)).get(0));
                startActivity(j);
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
                Intent i = new Intent(ListActivity.this, ListActivity.class);
                i.putExtra("button", "query");
                i.putExtra("keyword", searchView.getQuery().toString());
                if(extras.getString("name")!=null){
                    i.putExtra("state_value", state_value);
                    i.putExtra("state", state);
                }
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
        Intent i = new Intent(ListActivity.this, ScrollingActivity.class);
        startActivity(i);
        finish();
    }
}

