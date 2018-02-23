package com.example.mavericks.marderapp.Controller;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.io.InputStream;
import java.net.URL;

/**
 * Created by Mavericks on 24.02.2018.
 */

public class ImageLoader {

    public ImageLoader(ImageView imageView, String url, ProgressBar progressBar){
        new DownLoadImageTask(imageView, progressBar).execute(url);
    }
    private class DownLoadImageTask extends AsyncTask<String,Void,Bitmap> {
        ImageView imageView;
        ProgressBar progressBar;
        public DownLoadImageTask(ImageView imageView, ProgressBar progressBar){
            this.imageView = imageView;
            this.progressBar = progressBar;
        }
        protected Bitmap doInBackground(String...urls){
            String urlOfImage = urls[0];
            Bitmap logo = null;
            try{
                InputStream is = new URL(urlOfImage).openStream();
                logo = BitmapFactory.decodeStream(is);
            }catch(Exception e){ // Catch the download exception
                e.printStackTrace();
            }
            return logo;
        }
        protected void onPostExecute(Bitmap result){
            progressBar.invalidate();
            ViewGroup viewGroup = (ViewGroup) progressBar.getParent();
            viewGroup.removeView(progressBar);
            imageView.setImageBitmap(result);
        }
    }


}

