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
import android.widget.TextView;
import android.widget.Toast;

public class GridActivity extends Activity {

    AjooxData data;
    String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grid_layout);

        data = new AjooxData(getApplicationContext());
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String value = extras.getString("button");
            //The key argument here must match that used in the other activity
            type = value;
        }

        TextView title = (TextView) findViewById(R.id.toolbar_title);
        Typeface faceDea = Typeface.createFromAsset(getAssets(), "fonts/deathstar.otf");
        title.setTypeface(faceDea);

        GridView gridview = (GridView) findViewById(R.id.gridview);
        TextView typeText = (TextView) findViewById(R.id.type);
        switch (type) {
            case "artist":
                typeText.setText("All Artist");
                gridview.setAdapter(new ImageAdapter(this, data.getArtist(""), R.drawable.artist));
                break;
            case "album":
                typeText.setText("All Album");
                gridview.setAdapter(new ImageAdapter(this, data.getAlbum(""), R.drawable.folder));
                break;
            case "genre":
                typeText.setText("All Genre");
                gridview.setAdapter(new ImageAdapter(this, data.getGenre(""), R.drawable.sound_icon));
                break;
            case "country":
                typeText.setText("All Year");
                gridview.setAdapter(new ImageAdapter(this, data.getReleaseYear(""), R.drawable.sound_icon));
                break;
        }
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
              //  String item = adapterView.getItemAtPosition(i).toString();
                TextView t = (TextView) adapterView.findViewById(R.id.txt_grid);
                //Toast.makeText(getApplicationContext(),""+t.getText().toString(),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(GridActivity.this, ListActivity.class);
                intent.putExtra("artist",t.getText().toString());
                GridActivity.this.startActivity(intent);
                finish();
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