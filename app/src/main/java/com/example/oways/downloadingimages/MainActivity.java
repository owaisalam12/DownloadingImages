package com.example.oways.downloadingimages;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    ImageView downloadImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        downloadImageView=(ImageView)findViewById(R.id.imageView);
    }

    public void downloadImage(View view) {
        ImageDownloader task=new ImageDownloader();
        Bitmap myImage;
        try {
            myImage=task.execute("https://vignette.wikia.nocookie.net/dragonball/images/8/84/Ultra_instinct_goku_by_hazeelart-dbr20h3.png/revision/latest?cb=20171128185345").get();
            downloadImageView.setImageBitmap(myImage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        }

    }

    class ImageDownloader extends AsyncTask<String,Void,Bitmap>{

    @Override
    protected Bitmap doInBackground(String... urls) {
        try {
            URL url=new URL(urls[0]);
            HttpURLConnection connection=(HttpURLConnection)url.openConnection();
            connection.connect();
            InputStream inputStream=connection.getInputStream();
            Bitmap bitmap= BitmapFactory.decodeStream(inputStream);
            return bitmap;


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}


