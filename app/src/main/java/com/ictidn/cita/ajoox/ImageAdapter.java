package com.ictidn.cita.ajoox;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ImageAdapter extends BaseAdapter {
    private Context context;
    private final ArrayList<String> txt;
    private final ArrayList<String> img;

    public ImageAdapter(Context context, ArrayList<String> txt, ArrayList<String> img) {
        this.context = context;
        this.txt = txt;
        this.img = img;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;

        if (convertView == null) {

            gridView = new View(context);

            // get layout from mobile.xml
            gridView = inflater.inflate(R.layout.grid_item, null);

            // set value into textview
            TextView textView = (TextView) gridView
                    .findViewById(R.id.txt_grid);
            textView.setText(txt.get(position));

            // set image based on selected text
            ImageView imageView = (ImageView) gridView
                    .findViewById(R.id.image_grid);

        } else {
            gridView = (View) convertView;
        }

        return gridView;
    }

    @Override
    public int getCount() {
        return txt.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

}

