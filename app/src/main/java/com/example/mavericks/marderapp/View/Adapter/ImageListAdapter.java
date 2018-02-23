package com.example.mavericks.marderapp.View.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.mavericks.marderapp.Controller.ImageLoader;
import com.example.mavericks.marderapp.Model.AlbumListItem;
import com.example.mavericks.marderapp.Model.ImageListItem;
import com.example.mavericks.marderapp.R;
import com.example.mavericks.marderapp.View.Fragment.ImageFragment;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Mavericks on 24.02.2018.
 */

public class ImageListAdapter extends ArrayAdapter<ImageListItem> {
    private final Context context;
    private final ArrayList<ImageListItem> images;

    public ImageListAdapter(Context context, ArrayList<ImageListItem> images) {
        super(context, -1, images);
        this.context = context;
        this.images = images;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView != null) return convertView;
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.image_list, parent, false);
        TextView imageId = rowView.findViewById(R.id.imageId);
        TextView imageTitle = rowView.findViewById(R.id.imageTitle);
        TextView imageUrl = rowView.findViewById(R.id.imageUrl);
        ProgressBar imageProgress = rowView.findViewById(R.id.imageProgress);
        String idText = String.valueOf(images.get(position).getId());
        imageId.setText(idText);
        imageTitle.setText(images.get(position).getTitle());
        imageUrl.setText(images.get(position).getUrl());

        ImageView imageImage = rowView.findViewById(R.id.imageImage);

        new ImageLoader(imageImage, images.get(position).getThumbnailUrl(), imageProgress);
        return rowView;
    }
}
