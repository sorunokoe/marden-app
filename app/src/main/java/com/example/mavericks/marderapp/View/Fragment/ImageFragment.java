package com.example.mavericks.marderapp.View.Fragment;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.mavericks.marderapp.Controller.ImageActivity;
import com.example.mavericks.marderapp.Model.AlbumListItem;
import com.example.mavericks.marderapp.Model.ImageListItem;
import com.example.mavericks.marderapp.R;
import com.example.mavericks.marderapp.View.Adapter.AlbumListAdapter;
import com.example.mavericks.marderapp.View.Adapter.ImageListAdapter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;


public class ImageFragment extends ListFragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setRetainInstance(true);
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.image_fragment, container, false);
        ImageListAdapter adapter = new ImageListAdapter(getActivity(), ImageListItem.images);
        setListAdapter(adapter);
        return rootView;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        System.out.println(ImageListItem.images.get(position).getUrl());
        String url = ImageListItem.images.get(position).getUrl();
        if (!url.startsWith("http://") && !url.startsWith("https://"))
            url = "http://" + url;
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(browserIntent);
    }

    @Override
    public void onSaveInstanceState(final Bundle outState) {
        super.onSaveInstanceState(outState);
    }

}

