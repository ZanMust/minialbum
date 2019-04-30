package com.zanmust.minialbum;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ortiz.touchview.TouchImageView;

import utils.DataHelper;
import utils.ImageUtils;

public class DetailedPhotoActivity extends AppCompatActivity {


    TouchImageView IV;
    TextView TV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_photo);

        if(getSupportActionBar() != null) {
            getSupportActionBar().setTitle("");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        String url = getIntent().getExtras().getString(PhotosActivity.EXTRA_URL);
        String title = getIntent().getExtras().getString(PhotosActivity.EXTRA_TITLE);
        int userid = getIntent().getExtras().getInt(UsersActivity.EXTRA_USER);
        int albumid = getIntent().getExtras().getInt(AlbumsActivity.EXTRA_ALBUM);

        IV = (TouchImageView) findViewById(R.id.fullimage);
        TV = (TextView) findViewById(R.id.details);

        TV.setText(title+"\n"+ DataHelper.getInstance().getUsername(userid)+"\n"+DataHelper.getInstance().getAlbumName(userid,albumid));

        ImageUtils.setImage(url,IV);
        IV.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                refreshImage();
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshImage();
                    }
                }, 1000);

                return false;
            }
        });


    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    public void refreshImage(){
        if(IV.isZoomed()){
            TV.setVisibility(View.INVISIBLE);
        } else {
            TV.setVisibility(View.VISIBLE);
        }
    }
}
