package com.zanmust.minialbum.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.zanmust.minialbum.R;

import java.util.ArrayList;

import utils.DataHelper;
import utils.ImageUtils;

public class AlbumsAdapter extends ArrayAdapter<DataHelper.Album> {
    public AlbumsAdapter(Context context, ArrayList<DataHelper.Album> Album) {
        super(context, 0, Album);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        DataHelper.Album Album = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.album_item, parent, false);
        }
        TextView tvName = (TextView) convertView.findViewById(R.id.tvName);
        ImageView mCover = (ImageView) convertView.findViewById(R.id.cover);


        tvName.setText(Album.name);
        if(Album.cover!=null){
            ImageUtils.setImage(Album.cover.thumbnailUrl,mCover);
            mCover.setVisibility(View.VISIBLE);

        }


        return convertView;
    }
}
