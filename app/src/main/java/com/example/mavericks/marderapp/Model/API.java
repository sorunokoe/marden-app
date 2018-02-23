package com.example.mavericks.marderapp.Model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface API {
        @GET("/albums")
        Call<List<AlbumListItem>> listAlbum();

        @GET("/photos")
        Call<List<ImageListItem>> listImage(@Query("albumId") Integer id);
}
