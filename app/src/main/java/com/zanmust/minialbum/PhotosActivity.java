package com.zanmust.minialbum;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zanmust.minialbum.adapters.PhotoAdapter;

import java.util.ArrayList;

import utils.DataHelper;

public class PhotosActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;

    public static final String EXTRA_URL = "URLFORIMG";

    int userID;
    int albumID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photos);
        userID = getIntent().getExtras().getInt(UsersActivity.EXTRA_USER);
        albumID = getIntent().getExtras().getInt(AlbumsActivity.EXTRA_ALBUM);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler);

        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));

        final ArrayList<DataHelper.Photo> photos = DataHelper.getInstance().getPicturesFromUserAlbum(userID,albumID);
        if(photos!=null){
            PhotoAdapter mPhotoAdapter = new PhotoAdapter(this, photos, new PhotoAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    detailed(photos.get(position).url);
                }
            });
            mRecyclerView.setAdapter(mPhotoAdapter);
        }



    }
    private void detailed(String url){
        Intent mIntent = new Intent(this, DetailedPhotoActivity.class);
        mIntent.putExtra(EXTRA_URL,url);
        startActivity(mIntent);
    }
}
