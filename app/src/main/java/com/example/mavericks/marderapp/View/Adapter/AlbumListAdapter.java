package com.example.mavericks.marderapp.View.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.example.mavericks.marderapp.Model.AlbumListItem;
import com.example.mavericks.marderapp.R;

import java.util.ArrayList;


public class AlbumListAdapter extends ArrayAdapter<AlbumListItem> {
    private final Context context;
    private final ArrayList<AlbumListItem> albums;

    public AlbumListAdapter(Context context, ArrayList<AlbumListItem> albums) {
        super(context, -1, albums);
        this.context = context;
        this.albums = albums;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView != null) return convertView;
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.album_list, parent, false);
        TextView albumId = rowView.findViewById(R.id.albumId);
        TextView albumTitle = rowView.findViewById(R.id.albumTitle);

        String idText = String.valueOf(albums.get(position).getId());
        albumId.setText(idText);
        albumTitle.setText(albums.get(position).getTitle());
        return rowView;
    }
}