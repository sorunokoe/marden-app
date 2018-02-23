package com.example.mavericks.marderapp.Controller;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.example.mavericks.marderapp.Model.AlbumListItem;
import com.example.mavericks.marderapp.Model.ImageListItem;
import com.example.mavericks.marderapp.Model.RetroSettings;
import com.example.mavericks.marderapp.R;
import com.example.mavericks.marderapp.View.Fragment.AlbumFragment;
import com.example.mavericks.marderapp.View.Fragment.ImageFragment;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ImageActivity extends AppCompatActivity {
    ImageFragment imageFragment;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_main);

        if(savedInstanceState!=null) {
            imageFragment = (ImageFragment) getSupportFragmentManager().getFragment(savedInstanceState, "imageFragment");
        }else{
            int album_pk = getIntent().getIntExtra("album_pk", -1);
            if (album_pk != -1) {
                final Call<List<ImageListItem>> getAlbumList = RetroSettings.getService().listImage(album_pk);
                getAlbumList.enqueue(new Callback<List<ImageListItem>>() {
                    @Override
                    public void onResponse(Call<List<ImageListItem>> call, Response<List<ImageListItem>> response) {
                        System.out.println(response.body());
                        if (response.code() == 200) {
                            ImageListItem.images = (ArrayList) response.body();
                            imageFragment = new ImageFragment();
                            FragmentManager manager = getSupportFragmentManager();
                            manager.beginTransaction().replace(R.id.mainLayout, imageFragment).commit();
                        }
                    }
                    @Override
                    public void onFailure(Call<List<ImageListItem>> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if(imageFragment!=null) {
            getSupportFragmentManager().putFragment(outState, "imageFragment", imageFragment);
        }
    }

}
