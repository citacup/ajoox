package com.ictidn.cita.ajoox;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by CITACUP on 3/31/2015.
 */
public class Player extends Activity {

    Intent svc = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //sound background
        svc = new Intent(this, BackgroundSoundService.class);

        //set view
        setContentView(R.layout.player);

        TextView title = (TextView) findViewById(R.id.toolbar_title);
        Typeface faceDeaa = Typeface.createFromAsset(getAssets(), "fonts/deathstar.otf");
        title.setTypeface(faceDeaa);
    }

    @Override
    protected void onDestroy() {
        //stop background
        stopService(svc);
        super.onDestroy();
    }

    @Override
    public void onBackPressed()
    {
        Intent i = new Intent(Player.this, ScrollingActivity.class);
        startActivity(i);
        finish();
    }

    public void media(View v) {
        switch (v.getId()) {
            case (R.id.play):
                startService(svc);
            case (R.id.pause):
                stopService(svc);
        }
    }

}