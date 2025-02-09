package com.media.picker;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.BaseAdapter;

import androidx.camera.view.PreviewView;
import androidx.core.content.FileProvider;
import com.bumptech.glide.Glide;
import java.io.File;
import java.util.List;

public class MediaGridAdapter extends BaseAdapter {
    private Context context;
    private List<Uri> mediaList;
    private static final int CAMERA_REQUEST_CODE = 1001;



    public MediaGridAdapter(Context context, List<Uri> mediaList) {
        this.context = context;
        this.mediaList = mediaList;
    }

    @Override
    public int getCount() {
        return mediaList.size();
    }

    @Override
    public Object getItem(int position) {
        return mediaList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.grid_item, parent, false);
        }

        ImageView imageView = convertView.findViewById(R.id.imageView);


            Glide.with(context).load(mediaList.get(position)).into(imageView);


        return convertView;
    }

}
