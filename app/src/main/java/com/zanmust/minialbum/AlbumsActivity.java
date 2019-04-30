package com.zanmust.minialbum;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.zanmust.minialbum.adapters.AlbumsAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import utils.DataHelper;
import utils.RequestManager;

public class AlbumsActivity extends AppCompatActivity {

    private static final String EXTRA_ALBUM = "EXTRA_ALBUMID";
    ProgressBar mProgressBar;
    ListView mListView;

    ArrayList<DataHelper.Album> userAlbums = new ArrayList<>();
    int UserID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        mListView = (ListView) findViewById(R.id.List);

        UserID = getIntent().getExtras().getInt(UsersActivity.EXTRA_USER);

        getAlbumsFromWeb();

    }

    private void getAlbumsFromWeb(){
        RequestManager.getUserAlbums(this,UserID,new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                getPicturesFromAlbums(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("FETCHING ERROR", error.networkResponse.toString());
            }
        });
    }

    private void getPicturesFromAlbums(JSONArray albums){


        for( int i = 0; i < albums.length(); i++){

            try {
            final JSONObject x = (JSONObject) albums.getJSONObject(i);

                RequestManager.getAlbumPictures(this, x.getInt("id") , new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        ArrayList<DataHelper.Photo> photosOfEachAlbum = new ArrayList<>();
                        for(int i1 = 0; i1<response.length();i1++){
                            try {
                                JSONObject x1 = (JSONObject) response.getJSONObject(i1);
                                DataHelper.Photo mPhoto = new DataHelper.Photo(x1.getString("title"),x1.getString("url"),x1.getString("thumbnailUrl"));
                                photosOfEachAlbum.add(mPhoto);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        DataHelper.Album mAlbum = null;
                        try {
                            mAlbum = new DataHelper.Album(x.getString("title"),x.getInt("id"), photosOfEachAlbum);
                            userAlbums.add(mAlbum);
                            DataHelper.getInstance().addAlbumToUser(UserID,userAlbums);
                            refreshList();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }



                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("FETCHING ERROR", error.networkResponse.toString());
                    }
                });





            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        albumsLoaded();


    }



    private void albumsLoaded(){
        mProgressBar.setVisibility(View.GONE);
        refreshList();
        mListView.setVisibility(View.VISIBLE);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showAlbumPhotos(userAlbums.get(position).id);
            }
        });

    }

    private void refreshList(){
        AlbumsAdapter mAlbumsAdapter = new AlbumsAdapter(this, userAlbums);
        mListView.setAdapter(mAlbumsAdapter);
        mListView.deferNotifyDataSetChanged();
    }

    private void showAlbumPhotos(int id){
        Intent mIntent = new Intent(this,AlbumsActivity.class);
        mIntent.putExtra(EXTRA_ALBUM,id);
        startActivity(mIntent);
    }
}

