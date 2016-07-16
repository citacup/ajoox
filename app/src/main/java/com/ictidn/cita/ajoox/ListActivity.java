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
    AjooxData data;
    String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_layout);

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

        // Listview Data
        String products[] = {"Backstreet Boys",
                "Carly Rae Jepsen",
                "Eminem",
                "Girls Generation",
                "Calvin Harris",
                "Kesha" };

        lv = (ListView) findViewById(R.id.list_view);
        TextView title_listview = (TextView) findViewById(R.id.title_listview);
        switch (type) {
            case "allsongs":
                title_listview.setText("All Songs");
                adapter = new ArrayAdapter<String>(this, R.layout.list_item, R.id.product_name, data.getSong(""));
                break;
        }
        // Adding items to listview
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

