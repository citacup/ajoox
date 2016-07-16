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
import android.widget.TextView;
import android.widget.Toast;

public class ListActivity extends Activity {

    private ListView lv;
    // Listview Adapter
    ArrayAdapter<String> adapter;
    AjooxData data;
    String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_layout);

        data = new AjooxData(getApplicationContext());
        Bundle extras = getIntent().getExtras();
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

        /** Listview Data
        String products[] = {"Backstreet Boys",
                "Carly Rae Jepsen",
                "Eminem",
                "Girls Generation",
                "Calvin Harris",
                "Kesha" };**/

        lv = (ListView) findViewById(R.id.list_view);
        TextView title_listview = (TextView) findViewById(R.id.title_listview);
        switch (type) {
            case "artist":
                title_listview.setText("BY ARTIST: "+extras.getString("name"));
                adapter = new ArrayAdapter<String>(this, R.layout.list_item, R.id.product_name, data.getSongByArtist(extras.getString("name")));
                break;
            case "album":
                title_listview.setText("BY ALBUM: "+extras.getString("name"));
                adapter = new ArrayAdapter<String>(this, R.layout.list_item, R.id.product_name, data.getSongByAlbum(extras.getString("name")));
                break;
            case "genre":
                title_listview.setText("BY GENRE: "+extras.getString("name"));
                adapter = new ArrayAdapter<String>(this, R.layout.list_item, R.id.product_name, data.getSongByGenre(extras.getString("name")));
                break;

            case "year":
                title_listview.setText("YEAR RELEASED: "+extras.getString("name"));
                adapter = new ArrayAdapter<String>(this, R.layout.list_item, R.id.product_name, data.getSongByYear(extras.getString("name")));
                break;

            case "allsongs":
                title_listview.setText("All Songs");
                adapter = new ArrayAdapter<String>(this, R.layout.list_item, R.id.product_name, data.getSong(""));
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
    }

    @Override
    public void onBackPressed()
    {
        Intent i = new Intent(ListActivity.this, ScrollingActivity.class);
        startActivity(i);
        finish();
    }
}

