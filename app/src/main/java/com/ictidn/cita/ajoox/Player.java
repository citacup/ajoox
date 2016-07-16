package com.ictidn.cita.ajoox;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * Created by CITACUP on 3/31/2015.
 */
public class Player extends Activity {

    //Intent svc = null;
    AjooxData data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //sound background
        //svc = new Intent(this, BackgroundSoundService.class);

        //set view
        setContentView(R.layout.player);

        data = new AjooxData(getApplicationContext());
        Bundle extras = getIntent().getExtras();

        ((TextView) findViewById(R.id.textArtist)).setText(extras.getString("artist"));
        ((TextView) findViewById(R.id.textTitle)).setText(extras.getString("title"));
        ((TextView) findViewById(R.id.textViewAlbum)).setText(extras.getString("album"));
        ((TextView) findViewById(R.id.textPath)).setText(extras.getString("path"));

        TextView title = (TextView) findViewById(R.id.toolbar_title);
        Typeface faceDea = Typeface.createFromAsset(getAssets(), "fonts/deathstar.otf");
        title.setTypeface(faceDea);
    }

    @Override
    protected void onDestroy() {
        //stop background
        //stopService(svc);
        super.onDestroy();
    }

    @Override
    public void onBackPressed()
    {
        Intent i = new Intent(Player.this, ScrollingActivity.class);
        startActivity(i);
        finish();
    }

    /**
    public void media(View v) {
        switch (v.getId()) {
            case (R.id.play):
                startService(svc);
            case (R.id.pause):
                stopService(svc);
        }
    }**/

}