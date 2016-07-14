package com.ictidn.cita.ajoox;

/**
 * Created by ajou on 7/14/2016.
 */
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.TextView;

public class GridActivity extends Activity {

    private AjooxData data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grid_layout);

        data = new AjooxData(getApplicationContext());

        TextView title = (TextView) findViewById(R.id.toolbar_title);
        Typeface faceDea = Typeface.createFromAsset(getAssets(), "fonts/deathstar.otf");
        title.setTypeface(faceDea);

        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(this, data.getArtist(""), null));
    }

    @Override
    public void onBackPressed()
    {
        Intent i = new Intent(GridActivity.this, ScrollingActivity.class);
        startActivity(i);
        finish();
    }

}