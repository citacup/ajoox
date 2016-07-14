package com.ictidn.cita.ajoox;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;

/**
 * Created by ajou on 7/14/2016.
 */
public class ImageAdapter extends BaseAdapter {
    private Context mContext;

    public ImageAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //get the item corresponding to your position
        LinearLayout row = (LinearLayout) (convertView == null ? LayoutInflater.from(mContext)
                .inflate(R.layout.grid_item, parent, false) : convertView);
        return row;
    }

    // references to our images
    private Integer[] mThumbIds = {
            android.R.mipmap.sym_def_app_icon, android.R.mipmap.sym_def_app_icon,
            android.R.mipmap.sym_def_app_icon, android.R.mipmap.sym_def_app_icon
    };
}

