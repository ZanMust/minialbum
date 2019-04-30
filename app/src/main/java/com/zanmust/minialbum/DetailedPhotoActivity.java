package com.zanmust.minialbum;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import utils.ImageUtils;

public class DetailedPhotoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_photo);
        String url = getIntent().getExtras().getString(PhotosActivity.EXTRA_URL);

        ImageView IV = (ImageView) findViewById(R.id.fullimage);


        ImageUtils.setImage(url,IV);
    }
}
