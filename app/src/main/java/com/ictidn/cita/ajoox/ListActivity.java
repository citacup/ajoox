package com.ictidn.cita.ajoox;

/**
 * Created by ajou on 7/14/2016.
 */
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ListActivity extends Activity {

    private ListView lv;
    // Listview Adapter
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_layout);

        TextView title = (TextView) findViewById(R.id.toolbar_title);
        Typeface faceDea = Typeface.createFromAsset(getAssets(), "fonts/deathstar.otf");
        title.setTypeface(faceDea);

        // Listview Data
        String products[] = {"Backstreet Boys",
                "Carly Rae Jepsen",
                "Eminem",
                "Girls Generation",
                "Calvin Harris",
                "Kesha" };

        lv = (ListView) findViewById(R.id.list_view);

        // Adding items to listview
        adapter = new ArrayAdapter<String>(this, R.layout.list_item, R.id.product_name, products);
        lv.setAdapter(adapter);
    }

    @Override
    public void onBackPressed()
    {
        Intent i = new Intent(ListActivity.this, ScrollingActivity.class);
        startActivity(i);
        finish();
    }
}

