package com.zanmust.minialbum;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.zanmust.minialbum.adapters.UserAdapter;

import org.json.JSONArray;

import utils.DataHelper;
import utils.RequestManager;

public class UsersActivity extends AppCompatActivity {

    public static final String EXTRA_USER = "EXTRA_USERID";

    ProgressBar mProgressBar;
    ListView mListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        mListView = (ListView) findViewById(R.id.List);

        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.INTERNET)
                != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.INTERNET},1);

        } else {
            getUsersFromWeb();
        }

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showUserAlbums(DataHelper.getInstance().getUserID(position));
            }
        });


    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getUsersFromWeb();
                }

                break;
        }
    }
    private void getUsersFromWeb(){
        RequestManager.getAllUsers(this,new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                usersLoaded(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("FETCHING ERROR", error.networkResponse.toString());
            }
        });
    }
    private void usersLoaded(JSONArray users){
        DataHelper.getInstance().setUsers(users);

        mProgressBar.setVisibility(View.GONE);

        UserAdapter mUserAdapter = new UserAdapter(this,DataHelper.getInstance().getUsers());
        mListView.setAdapter(mUserAdapter);
        mListView.deferNotifyDataSetChanged();
        mListView.setVisibility(View.VISIBLE);
    }

    private void showUserAlbums(int id){
        Intent mIntent = new Intent(this,AlbumsActivity.class);
        mIntent.putExtra(EXTRA_USER,id);
        startActivity(mIntent);
    }
}
