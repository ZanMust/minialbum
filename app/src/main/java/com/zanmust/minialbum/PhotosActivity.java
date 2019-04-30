package com.zanmust.minialbum;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.zanmust.minialbum.adapters.PhotoAdapter;

import java.util.ArrayList;

import utils.DataHelper;
import utils.ImageUtils;

public class PhotosActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;

    public static final String EXTRA_URL = "URLFORIMG";
    public static final String EXTRA_TITLE = "TITLEIMG";

    int userID;
    int albumID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photos);

        userID = getIntent().getExtras().getInt(UsersActivity.EXTRA_USER);
        albumID = getIntent().getExtras().getInt(AlbumsActivity.EXTRA_ALBUM);



        if(getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getString(R.string.Photo)+" "+albumID);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }


        mRecyclerView = (RecyclerView) findViewById(R.id.recycler);

        mRecyclerView.setLayoutManager(new GridLayoutManager(this, ImageUtils.getNumberOfColumns(this)));

        final ArrayList<DataHelper.Photo> photos = DataHelper.getInstance().getPicturesFromUserAlbum(userID,albumID);
        if(photos!=null){
            PhotoAdapter mPhotoAdapter = new PhotoAdapter(this, photos, new PhotoAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    detailed(photos.get(position).url,photos.get(position).title);
                }
            });
            mRecyclerView.setAdapter(mPhotoAdapter);
        }



    }
    private void detailed(String url, String title){
        Intent mIntent = new Intent(this, DetailedPhotoActivity.class);
        mIntent.putExtra(EXTRA_URL,url);
        mIntent.putExtra(EXTRA_TITLE,title );
        mIntent.putExtra(UsersActivity.EXTRA_USER, userID);
        mIntent.putExtra(AlbumsActivity.EXTRA_ALBUM, albumID);
        startActivity(mIntent);
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
