package com.example.mavericks.marderapp.Controller;

import android.os.PersistableBundle;
import android.support.v4.app.FragmentManager;
import android.app.ListActivity;
import android.app.ListFragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import com.example.mavericks.marderapp.Model.API;
import com.example.mavericks.marderapp.Model.AlbumListItem;
import com.example.mavericks.marderapp.Model.RetroSettings;
import com.example.mavericks.marderapp.R;
import com.example.mavericks.marderapp.View.Adapter.AlbumListAdapter;
import com.example.mavericks.marderapp.View.Fragment.AlbumFragment;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AlbumActivity extends AppCompatActivity {
    AlbumFragment albumFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.album_main);

        if(savedInstanceState!=null) {
            albumFragment = (AlbumFragment) getSupportFragmentManager().getFragment(savedInstanceState, "albumFragment");
        }else{
            RetroSettings.create();
            final Call<List<AlbumListItem>> getAlbumList = RetroSettings.getService().listAlbum();
            getAlbumList.enqueue(new Callback<List<AlbumListItem>>() {
                @Override
                public void onResponse(Call<List<AlbumListItem>> call, Response<List<AlbumListItem>> response) {
                    System.out.println(response.body());
                    if (response.code() == 200) {
                        AlbumListItem.albums = (ArrayList) response.body();
                        albumFragment = new AlbumFragment();
                        FragmentManager manager = getSupportFragmentManager();
                        manager.beginTransaction().replace(R.id.mainLayout, albumFragment).commit();
                    }
                }

                @Override
                public void onFailure(Call<List<AlbumListItem>> call, Throwable t) {
                    t.printStackTrace();
                }
            });
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if(albumFragment!=null) {
            getSupportFragmentManager().putFragment(outState, "albumFragment", albumFragment);
        }
    }
}
