package com.example.mavericks.marderapp.View.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.mavericks.marderapp.Controller.ImageActivity;
import com.example.mavericks.marderapp.Model.AlbumListItem;
import com.example.mavericks.marderapp.R;
import com.example.mavericks.marderapp.View.Adapter.AlbumListAdapter;

import java.io.Serializable;
import java.util.ArrayList;


public class AlbumFragment extends ListFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setRetainInstance(true);
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.album_fragment, container, false);
        AlbumListAdapter adapter = new AlbumListAdapter(getActivity(), AlbumListItem.albums);
        setListAdapter(adapter);
        return rootView;
    }
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        int pk = AlbumListItem.albums.get(position).getId();
        Intent intent = new Intent(getActivity(), ImageActivity.class);
        intent.putExtra("album_pk", pk);
        getActivity().startActivity(intent);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
