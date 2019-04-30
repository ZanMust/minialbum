package com.zanmust.minialbum;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.zanmust.minialbum.adapters.PhotoAdapter;

import java.util.ArrayList;

import utils.DataHelper;

public class PhotosActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;

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

        ArrayList<DataHelper.Photo> photos = DataHelper.getInstance().getPicturesFromUserAlbum(userID,albumID);
        if(photos!=null){
            PhotoAdapter mPhotoAdapter = new PhotoAdapter(this, photos);
            mRecyclerView.setAdapter(mPhotoAdapter);
        }

    }
}
