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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grid_layout);

        TextView title = (TextView) findViewById(R.id.toolbar_title);
        Typeface faceDea1 = Typeface.createFromAsset(getAssets(), "fonts/deathstar.otf");
        title.setTypeface(faceDea1);

        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(this));
    }

    @Override
    public void onBackPressed()
    {
        Intent i = new Intent(GridActivity.this, ScrollingActivity.class);
        startActivity(i);
        finish();
    }
}